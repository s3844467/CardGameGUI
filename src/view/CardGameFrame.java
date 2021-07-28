package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import model.interfaces.GameEngine;

public class CardGameFrame extends JFrame 
{
	private GameEngine gameEngine;
	private SummaryPanel summaryPanel;
	private ToolBarPanel toolBarPanel;
	private BottomPanel bottomPanel;
	private MainPanel mainPanel;
	Dimension dim;
	
	public GameEngine getGameEngine() 
	{
		return gameEngine;
	}

	public SummaryPanel getSummaryPanel() 
	{
		return summaryPanel;
	}
	
	public ToolBarPanel getToolBarPanel()
	{
		return toolBarPanel;
	}
	
	public BottomPanel getBottomPanel() 
	{
		return bottomPanel;
	}

	public MainPanel getMainPanel() 
	{
		return mainPanel;
	}

	public CardGameFrame(GameEngine gameEngine)
	{
		super("CardGameGUI");	
		
		dim = Toolkit.getDefaultToolkit().getScreenSize();
        setMinimumSize(new Dimension((int) dim.getWidth() / 2, (int) dim.getHeight() / 2));
        setBounds((int) (dim.getWidth() / 2 - dim.getWidth() / 4), (int) (dim.getHeight() / 2 - dim.getHeight() / 4),
				(int) (dim.getWidth() / 2), (int) (dim.getHeight() / 2));
        
		this.gameEngine = gameEngine;
		setLayout(new BorderLayout());		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	public void populate()
	{
		setLayout(new BorderLayout());
		
		mainPanel = new MainPanel(this);
		toolBarPanel = new ToolBarPanel(this, gameEngine);
		bottomPanel = new BottomPanel(this);
		
		add(toolBarPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
		revalidate();
	}

}
