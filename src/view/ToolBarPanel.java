package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import controller.AddPlayerListener;
import controller.BetButtonListener;
import controller.DealButtonListener;
import controller.PlayerCBListener;
import controller.RemovePlayerListener;
import controller.ResetButtonListener;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class ToolBarPanel extends JPanel 
{
	private CardGameFrame cardGameFrame;
	private JToolBar toolBar;
	private JButton addPlayerButton;
	private JButton dealButton;
	private JButton betButton;
	private JButton resetButton;
	private JButton removePlayerButton;
	private JComboBox<Player> selectPlayer;
	
	public JButton getAddPlayerButton() 
	{
		return addPlayerButton;
	}
	
	public JButton getDealButton()
	{
		return dealButton;
	}

	public JButton getBetButton() 
	{
		return betButton;
	}
	
	public JButton getResetButton() 
	{
		return resetButton;
	}
	
	public JButton getRemovePlayerButton() 
	{
		return removePlayerButton;
	}
	
	public JComboBox<Player> getSelectPlayer()
	{
		return selectPlayer;
	}
	
	public CardGameFrame getCardGameFrame() 
	{
		return cardGameFrame;
	}

	public JToolBar gettBar() 
	{
		return toolBar;
	}

	@SuppressWarnings("serial")
	public ToolBarPanel(CardGameFrame cardGameFrame, GameEngine gameEngine)
	{
		this.cardGameFrame = cardGameFrame; 
		setBorder(BorderFactory.createTitledBorder("ToolBar"));
		setLayout(new BorderLayout());
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.NORTH);
		
		dealButton = new JButton("Deal Cards"); // create button
		dealButton.addActionListener(new DealButtonListener(cardGameFrame, gameEngine)); // add action listener 
		toolBar.add(dealButton); // add button to toolbar
		
		betButton = new JButton("Place Bet"); // create button
		betButton.addActionListener(new BetButtonListener(cardGameFrame, gameEngine));
		toolBar.add(betButton);
		
		resetButton = new JButton("Reset Bet"); // create button
		resetButton.addActionListener(new ResetButtonListener(cardGameFrame, gameEngine));
		toolBar.add(resetButton);
		
		selectPlayer = new JComboBox<Player>();
		selectPlayer.addItemListener(new PlayerCBListener());
		selectPlayer.addItem(new SimplePlayer("house", "House", 0));
		selectPlayer.setRenderer(new DefaultListCellRenderer() {
				public Component getListCellRendererComponent(JList<?> list,
	                    Object value,
	                    int index,
	                    boolean isSelected,
	                    boolean hasFocus) {
					if (value != null)
						setText(((Player) value).getPlayerName());
					return this;
				}
			});

		toolBar.add(selectPlayer);
		
		addPlayerButton = new JButton("Add Player"); // create button
		addPlayerButton.addActionListener(new AddPlayerListener(cardGameFrame, gameEngine));
		toolBar.add(addPlayerButton);
		
		AbstractButton removePlayerButton = new JToggleButton("Remove Player"); // create button
		removePlayerButton.addActionListener(new RemovePlayerListener(cardGameFrame, gameEngine));
		toolBar.add(removePlayerButton);
	}
}
