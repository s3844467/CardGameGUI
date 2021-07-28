package view;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.PlayingCard;

public class CardPanel extends JPanel 
{
	CardGameFrame cardGameFrame;
	JPanel cardPanel;
    File[] cardImageFile;


	public CardPanel(CardGameFrame cardGameFrame) 
	{
		this.cardGameFrame = cardGameFrame;
		cardImageFile = new File(String.format("img%scards", File.separator)).listFiles();
	}

	public void ImageCreator(PlayingCard pc)
	{
		String imgFileName = pc.getSuit().toString() + "_" + pc.getValue().toString() + ".PNG";
		for (int i = 0; i < cardImageFile.length; i++) 
		{
            if (cardImageFile[i].toString().contains(imgFileName)) 
            {
                ImageIcon currentCard = new ImageIcon(cardImageFile[i].toString());
                
                Image cardImage = currentCard.getImage();
				Image cardScale = cardImage.getScaledInstance((int) (cardGameFrame.getWidth() / 7), (int) (cardGameFrame.getHeight()  / 3), java.awt.Image.SCALE_SMOOTH);
                
                currentCard = new ImageIcon(cardScale);
                JLabel cardLabel = new JLabel(currentCard);
                
                add(cardLabel);
                //repaint();
            }
        } 
	}
	
	public void ClearCards()
	{
		removeAll();
	}
	
	

}
