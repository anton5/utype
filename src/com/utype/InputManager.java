package com.utype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
    private static final String CARET_INDICATOR = ">";

    private EventListener listener;
    private boolean isRunning;
    private StringBuilder commandBuffer = new StringBuilder();

    private StringBuilder getCommandBuffer() {
        return commandBuffer;
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

        Logger.logToInputField(CARET_INDICATOR + commandBuffer.toString());
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
