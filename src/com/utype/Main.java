package com.utype;

import com.utype.characters.Player;
import com.utype.ui.MainFrame;
import com.utype.ui.UIManager;

public class Main {
    public static void main(String[] args) {
        InputManager inputManager = InputManager.getInstance();
        MainFrame frame = new MainFrame();
        Player player = new Player("John Smith", 100);
        Game game = new Game(player);

        Logger.initialize(frame.getMainTextPane(), frame.getAuxiliaryTextPane(), frame.getInputTextField());
        UIManager.initialize(frame);
        UIManager.setAuxiliaryVisible(false);

        frame.addKeyListener(inputManager);
        inputManager.setListener(game);

        inputManager.start();
        game.start();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
