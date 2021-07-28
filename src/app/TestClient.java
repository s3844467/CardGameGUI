package app;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.CardGameFrame;
import view.GameEngineCallbackGUI;
import view.interfaces.GameEngineCallback;

public class TestClient {

	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GameEngine gEngine = new GameEngineImpl();
				CardGameFrame cardGameFrame = new CardGameFrame(gEngine);
				cardGameFrame.populate();
				GameEngineCallback gameEngineCallbackGUI = new GameEngineCallbackGUI(cardGameFrame, gEngine);
				gEngine.addGameEngineCallback(gameEngineCallbackGUI);
			}
		});
	}
}
 