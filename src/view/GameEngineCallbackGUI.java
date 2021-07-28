package view;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {

	private CardGameFrame cardGameFrame;
	private GameEngine gameEngine;
	SummaryPanel summaryPanel;
	BottomPanel statusPanel;
	CardPanel cardPanel;

	public GameEngineCallbackGUI(CardGameFrame cardGameFrame, GameEngine gameEngine) 
	{
		this.cardGameFrame = cardGameFrame;
		this.gameEngine = gameEngine;	
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		cardPanel = cardGameFrame.getMainPanel().getCardPanel();
		summaryPanel = cardGameFrame.getMainPanel().getSummaryPanel();
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				cardPanel.ImageCreator(card);
				cardPanel.repaint();
				cardPanel.revalidate();
			}
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		
	}

	@Override
	public void result(Player player, int result, GameEngine engine) 
	{
		statusPanel = cardGameFrame.getBottomPanel();
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{
				summaryPanel.updatePlayerResult(player);
				statusPanel.setGameStatus(String.format("%s scored %d", player.getPlayerName(), result));
			}
		});
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) 
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				cardPanel.ImageCreator(card);
				cardPanel.repaint();
				cardPanel.revalidate();
			}
		});
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void houseResult(int result, GameEngine engine) 
	{
		summaryPanel = cardGameFrame.getMainPanel().getSummaryPanel();
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
		{
			statusPanel.setGameStatus(String.format("House scored %d", result));
			// do GUI update on UI thread
			for (Player gamePlayer : gameEngine.getAllPlayers())
			{				
				if (gamePlayer.getResult() < result) {
					summaryPanel.updateWinLoss(gamePlayer, "Lost!");
				} else if (gamePlayer.getResult() > result) {
					summaryPanel.updateWinLoss(gamePlayer, "Won!");
				} else {
					summaryPanel.updateWinLoss(gamePlayer, "Draw!");
				}
			}	
		}
		});
		System.out.println(result);
	}

}
