package com.utype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
    private static final String CARET_INDICATOR = "> ";

    private static final InputManager sManager = new InputManager();

    private EventListener listener;
    private boolean isRunning;
    private StringBuilder commandBuffer = new StringBuilder();
    private boolean isDirectModeEnabled = false;
    private StringBuilder getCommandBuffer() {
        return commandBuffer;
    }

    public static InputManager getInstance() {
        return sManager;
    }
    private void setCommandBuffer(StringBuilder commandBuffer) {
        this.commandBuffer = commandBuffer;
    }

    public EventListener getListener() {
        return listener;
    }

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public boolean isDirectModeEnabled() {
        return isDirectModeEnabled;
    }

    public void setDirectModeEnabled(boolean directModeEnabled) {
        isDirectModeEnabled = directModeEnabled;
    }

    public void start() {
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (!isRunning) {
            return;
        }

        if (isDirectModeEnabled) {

            notifyListenerWithString(String.valueOf(e.getKeyChar()));

            Logger.logToInputField(CARET_INDICATOR);

            return;
        }

        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
            String command = commandBuffer.toString().trim();

            notifyListenerWithString(command);

            // clears the buffer
            commandBuffer.setLength(0);
        }

        if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE && commandBuffer.length() > 0) {
            commandBuffer.deleteCharAt(commandBuffer.length() - 1);
        }

        if (!e.isActionKey() && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
            commandBuffer.append(e.getKeyChar());
        }

        Logger.logToInputField(CARET_INDICATOR + commandBuffer.toString().trim());
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void notifyListenerWithString(String command) {
        if (getListener() != null) {
            getListener().onReceiveUserInput(command);
        }
    }

    public interface EventListener {
        void onReceiveUserInput(String input);
    }
}
