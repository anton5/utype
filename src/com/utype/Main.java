package com.utype;

import com.utype.ui.MainDialog;

public class Main {


    public static void main(String[] args) {


        InputManager inputManager = new InputManager();
        MainDialog dialog = new MainDialog();
        Player player = new Player("John Smith");
        Game game = new Game(player);

        Logger.initialize(dialog.getMainTextArea());

        dialog.addKeyListener(inputManager);
        inputManager.setListener(game);

        inputManager.start();
        game.start();

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
