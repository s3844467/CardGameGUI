package view;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BottomPanel extends JPanel
{
	CardGameFrame cardGameFrame;
	JLabel gameStatus;
	
	public BottomPanel(CardGameFrame cardGameFrame)
	{
		this.cardGameFrame = cardGameFrame;
		
		setBorder(BorderFactory.createTitledBorder("Game Status"));
		setLayout(new BorderLayout());
		
		gameStatus = new JLabel("Waiting for a player to be added.");
		add(gameStatus, BorderLayout.SOUTH);
	}
	
	public void setGameStatus(String update)
	{
		gameStatus.setText(update);
	}
}

