package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.interfaces.Player;

public class SummaryPanel extends JPanel {
	private JTable summaryTable;
	private DefaultTableModel infoModel;

	public JTable getSummaryTable() {
		return summaryTable;
	}

	public DefaultTableModel getInfoModel() {
		return infoModel;
	}

	public SummaryPanel(CardGameFrame gameFrame) {
		setBorder(BorderFactory.createTitledBorder("Summary Panel"));
		setLayout(new BorderLayout());

		infoModel = new DefaultTableModel();

		infoModel.addColumn("ID");
		infoModel.addColumn("Name");
		infoModel.addColumn("Balance");
		infoModel.addColumn("Bet");
		infoModel.addColumn("Result");
		infoModel.addColumn("Win/Loss");

		summaryTable = new JTable(infoModel);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(summaryTable);
		scrollPane.setPreferredSize(new Dimension(800, 80));
		summaryTable.setFillsViewportHeight(true);

		// Add the scroll pane to this panel.
		add(scrollPane, BorderLayout.CENTER);
	}

	public void PlayerRow(Player player) {
		Object[] addRow = { player.getPlayerId(), 
							player.getPlayerName(), 
							player.getPoints(), 
							player.getBet(),
							player.getResult(), 
							"NA" };
		boolean found = false;
		for (int i = 0; i < infoModel.getRowCount(); i++) 
		{
			if (player.getPlayerId().equals(infoModel.getValueAt(i, 0))) 
			{
				infoModel.removeRow(i);
				infoModel.addRow(addRow);
				found = true;
			}
		}
		if (found == false) 
		{
			infoModel.addRow(addRow);
		}
	}

	public void removePlayerRow(Player player) 
	{
		for (int i = infoModel.getRowCount() - 1; i >= 0; i--) 
		{
			if (player.getPlayerId().equals(infoModel.getValueAt(i, 0))) 
			{
				// i[pdadaondaskjdbhjasdjhas
				infoModel.removeRow(i);
			}
		}
	}
	
	public void updatePlayerPoints(Player player)
	{
		for (int i = 0; i < infoModel.getRowCount(); i++)
		{
			if (player.getPlayerId().equals(infoModel.getValueAt(i, 0)))
			{
				infoModel.setValueAt(player.getPoints(), i, 2);
			}
		}
	}

	public void updatePlayerBet(Player player) 
	{
		for (int i = 0; i < infoModel.getRowCount(); i++) 
		{
			if (player.getPlayerId().equals(infoModel.getValueAt(i, 0))) 
			{
				infoModel.setValueAt(player.getBet(), i, 3);
			}
		}
	}
	
	public void updatePlayerResult(Player player)
	{
		for (int i = 0; i < infoModel.getRowCount(); i++) 
		{
			if (player.getPlayerId().equals(infoModel.getValueAt(i, 0)))
			{
				infoModel.setValueAt(player.getResult(), i, 4);
			}
		}
	}
	
	public void updateWinLoss(Player player, String outcome)
	{
		for (int i = 0; i < infoModel.getRowCount(); i++)
		{
			if (player.getPlayerId().equals(infoModel.getValueAt(i, 0))) 
			{
				infoModel.setValueAt(outcome, i, 5);
			}
		}
	}

}
