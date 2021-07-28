package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.BottomPanel;
import view.CardGameFrame;
import view.SummaryPanel;

public class DealButtonListener implements ActionListener 
{
	CardGameFrame cardGameFrame;
	GameEngine gameEngine;
	JComboBox<Player> selectPlayer;
	SummaryPanel summaryPanel;
	BottomPanel statusPanel;
	Collection<Player> dealPlayers = new LinkedList<>();
	Player cardDeal = null;
	
	public DealButtonListener(CardGameFrame cardGameFrame, GameEngine gameEngine)
	{
		this.cardGameFrame = cardGameFrame;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		cardGameFrame.getMainPanel().getCardPanel().ClearCards();
		final int delay = 400;
		selectPlayer = cardGameFrame.getToolBarPanel().getSelectPlayer();
		summaryPanel = cardGameFrame.getMainPanel().getSummaryPanel();
		statusPanel = cardGameFrame.getBottomPanel();
		Player newPlayer = (Player) selectPlayer.getSelectedItem();
		
		if (newPlayer != null)
		{
			if (newPlayer.getPlayerId().equals("house") && dealPlayers.containsAll(gameEngine.getAllPlayers()) && 
					!dealPlayers.isEmpty()) 
			{
				new Thread()
				{
					@Override
					public void run()
					{
						statusPanel.setGameStatus("Dealing to ... House");
						gameEngine.dealHouse(delay);
					}

				}.start();
			} else {
				new Thread()
				{
					@Override
					public void run() 
					{
						statusPanel.setGameStatus(String.format("Dealing to ... %s", newPlayer.getPlayerName()));
						gameEngine.dealPlayer(newPlayer, delay);
						addToDealPlayer(newPlayer);
						summaryPanel.updatePlayerResult(newPlayer);
					}
				}.start();
			}
		}
	}
		
	
	private void addToDealPlayer(Player player)
	{
		dealPlayers.add(player);
	}

}
