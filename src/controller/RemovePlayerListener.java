package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.BottomPanel;
import view.CardGameFrame;
import view.SummaryPanel;

public class RemovePlayerListener implements ActionListener 
{
	CardGameFrame cardGameFrame;
	GameEngine gameEngine;
	JComboBox<Player> selectPlayer;
	SummaryPanel summaryPanel;
	BottomPanel statusPanel;
	
	public RemovePlayerListener(CardGameFrame cardGameFrame, GameEngine gameEngine)
	{
		this.cardGameFrame = cardGameFrame;
		this.gameEngine = gameEngine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		selectPlayer = cardGameFrame.getToolBarPanel().getSelectPlayer(); // reference to the comboBox of players.
		summaryPanel = cardGameFrame.getMainPanel().getSummaryPanel(); // reference to the summary panel.
		
		Player newPlayer = (Player) selectPlayer.getSelectedItem(); // selects a player from the combobox.
		gameEngine.removePlayer(newPlayer); //removes the player.
		selectPlayer.removeItem(newPlayer); // remove player item from combobox.
		summaryPanel.removePlayerRow(newPlayer); // update summary panel.
		statusPanel = cardGameFrame.getBottomPanel(); //update status panel.
		statusPanel.setGameStatus(String.format("%s has been removed.", newPlayer.getPlayerName()));		
	}
}
