package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CardGameFrame;
import view.SummaryPanel;

public class ResetButtonListener implements ActionListener 
{

	CardGameFrame cardGameFrame;
	GameEngine gameEngine;
	JComboBox<Player> selectPlayer;
	SummaryPanel summaryPanel;
	
	public ResetButtonListener(CardGameFrame cardGameFrame, GameEngine gameEngine)
	{
		this.cardGameFrame = cardGameFrame;
		this.gameEngine = gameEngine;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		selectPlayer = cardGameFrame.getToolBarPanel().getSelectPlayer();
		summaryPanel = cardGameFrame.getMainPanel().getSummaryPanel();
		
		Player newPlayer = (Player) selectPlayer.getSelectedItem();
		newPlayer.resetBet();
		summaryPanel.updatePlayerBet(newPlayer);
		
		
		
	}

}
