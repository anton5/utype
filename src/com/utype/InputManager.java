package com.utype;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * U-type
 * <p/>
 * Created by Roman Laitarenko on 1/25/16.
 */
public class InputManager implements KeyListener {

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

        Logger.log(String.valueOf(e.getKeyChar()));

        if (e.getKeyChar() == KeyEvent.VK_ENTER) {

            String command = commandBuffer.toString();

            notifyListenerWithString(command);

            // clears the buffer
            commandBuffer.setLength(0);

        } else  {

            commandBuffer.append(e.getKeyChar());
        }
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
