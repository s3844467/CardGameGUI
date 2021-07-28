package view;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.interfaces.GameEngine;

public class MainPanel extends JPanel 
{
	private CardGameFrame cardGameFrame;
	private CardPanel cardPanel;
	private SummaryPanel summaryPanel;
	GameEngine gameEngine;
	
	public CardGameFrame getCardGameFrame() 
	{
		return cardGameFrame;
	}

	public CardPanel getCardPanel() 
	{
		return cardPanel;
	}

	public SummaryPanel getSummaryPanel() 
	{
		return summaryPanel;
	}

	public MainPanel(CardGameFrame cardGameFrame)
	{
		this.cardGameFrame = cardGameFrame;
		
		setBorder(BorderFactory.createTitledBorder("Card Panel"));
		setLayout(new BorderLayout());
		
		
		this.cardPanel = new CardPanel(cardGameFrame);
		add(cardPanel, BorderLayout.CENTER);
		
		this.summaryPanel = new SummaryPanel(cardGameFrame);
		add(summaryPanel, BorderLayout.SOUTH);
	}
	
	
	
}
