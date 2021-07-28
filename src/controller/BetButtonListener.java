package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.BottomPanel;
import view.CardGameFrame;
import view.SummaryPanel;

public class BetButtonListener implements ActionListener 
{
	CardGameFrame cardGameFrame;
	GameEngine gameEngine;
	JComboBox<Player> selectPlayer;
	SummaryPanel summaryPanel;
	BottomPanel statusPanel;
	int bet;
	JButton betButton;

	public BetButtonListener(CardGameFrame cardGameFrame, GameEngine gameEngine) // constructor that passes through game frame and the game engine.
	{
		this.cardGameFrame = cardGameFrame;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try
		{
			bet = Integer.parseInt(JOptionPane.showInputDialog(cardGameFrame, "Enter Bet Amount ","Bet Amount", // takes in a bet input from player.
																			   JOptionPane.INFORMATION_MESSAGE));
			selectPlayer = cardGameFrame.getToolBarPanel().getSelectPlayer(); // reference to the toolbar.
			summaryPanel = cardGameFrame.getMainPanel().getSummaryPanel(); // reference to the summary panel. 
			statusPanel = cardGameFrame.getBottomPanel(); // reference to the status bar.
			
			Player newPlayer = (Player) selectPlayer.getSelectedItem(); // selects the current player in toolbar
			
			if (newPlayer != null) //if player is not null
			{
				if (!newPlayer.getPlayerId().equals("house"))
				{
					if (bet <= 0 || bet > newPlayer.getPoints()) // checks if placed bet is above 0 and the player has enough points.
					{
						statusPanel.setGameStatus(String.format("%s has insufficient balance.", newPlayer.getPlayerName()));
						JOptionPane.showMessageDialog(cardGameFrame, "Error: Bet Must be greater than 0 and less than Player balance!");
					} 
						else if (selectPlayer.getSelectedItem() == null)
					{
						/*
						 * checks if a player is selected before a bet has been placed.
						 */
						JOptionPane.showMessageDialog(cardGameFrame, "Error: Please select a player before betting");
					} 
						else
					{
						newPlayer.setBet(bet); // sets the new players bet.
						summaryPanel.updatePlayerBet(newPlayer); // updates summary panel with the placed bet. 
						statusPanel.setGameStatus(String.format("%s has placed a bet of %d.", newPlayer.getPlayerName(), bet)); // updates status bar
					}
				} 
						else 
					{
						statusPanel.setGameStatus("House is unable to bet.");
					}
			}
		} 
			catch (NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(cardGameFrame, "Error: Placed an invalid bet!");
		}
		
	}
}
