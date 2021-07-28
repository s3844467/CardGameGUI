package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.BottomPanel;
import view.CardGameFrame;
import view.SummaryPanel;

// Add Players (including name and initial betting points balance).
public class AddPlayerListener implements ActionListener 
{
	CardGameFrame cardGameFrame;	
	JComboBox<Player> selectPlayer;
	SummaryPanel summaryPanel;
	BottomPanel statusPanel;
	GameEngine gameEngine;
	String addPlayerId;
	String addPlayerName;
	String playerBalance;
	
	public AddPlayerListener(CardGameFrame cardGameFrame, GameEngine gameEngine) 
	{
		this.cardGameFrame = cardGameFrame;
		this.gameEngine = gameEngine;
		this.summaryPanel = cardGameFrame.getMainPanel().getSummaryPanel();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try
		{
			addPlayerId = JOptionPane.showInputDialog(cardGameFrame, "Enter ID", "Player ID", // takes input for player ID.
																	  JOptionPane.INFORMATION_MESSAGE);
			addPlayerName = JOptionPane.showInputDialog(cardGameFrame, "Enter Name", "Player Name", // takes input for player name.
																	  JOptionPane.INFORMATION_MESSAGE);
			playerBalance = JOptionPane.showInputDialog(cardGameFrame, "Enter Balance", "Player Balance", // takes input for player balance(points).
																	  JOptionPane.INFORMATION_MESSAGE);
			statusPanel = cardGameFrame.getBottomPanel(); // references the Status Bar.
			Player createPlayer = new SimplePlayer(addPlayerId, addPlayerName, Integer.parseInt(playerBalance)); // creates a new player with the given input. 

			if (!addPlayerId.equals("house")) // if the id = the house ID
			{
				if (Integer.parseInt(playerBalance) == 0 || Integer.parseInt(playerBalance) <  0 ) // checks if players input for points is above 0.
				{
					statusPanel.setGameStatus("Invalid balance entered..");
					JOptionPane.showMessageDialog(cardGameFrame, "Invalid number of points entered! "); 
				}
					else
				{
					gameEngine.addPlayer(createPlayer); // add player
					cardGameFrame.getToolBarPanel().getSelectPlayer().addItem(createPlayer); // update the toolbar with the new player.
					summaryPanel.PlayerRow(createPlayer); // update summary panel with the new player. 
					statusPanel.setGameStatus(String.format("%s has been added.", createPlayer.getPlayerName())); // status description. 
				}
			} 		else
				{
					statusPanel.setGameStatus("Invalid PlayerID"); 
				}
			
		} catch (NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(cardGameFrame, "Make sure field's are entered correctly.");
		}
		
	}
	

}
