package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean num1Pressed, num2Pressed, num3Pressed, num4Pressed, num5Pressed, num6Pressed, num7Pressed, num8Pressed, num9Pressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // Movement
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        // HotBar
        if (code == KeyEvent.VK_1) {
            num1Pressed = true;
        }
        if (code == KeyEvent.VK_2) {
            num2Pressed = true;
        }
        if (code == KeyEvent.VK_3) {
            num3Pressed = true;
        }
        if (code == KeyEvent.VK_4) {
            num4Pressed = true;
        }
        if (code == KeyEvent.VK_5) {
            num5Pressed = true;
        }
        if (code == KeyEvent.VK_6) {
            num6Pressed = true;
        }
        if (code == KeyEvent.VK_7) {
            num7Pressed = true;
        }
        if (code == KeyEvent.VK_8) {
            num8Pressed = true;
        }
        if (code == KeyEvent.VK_9) {
            num9Pressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        // Movement
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

        // HotBar
        if (code == KeyEvent.VK_1) {
            num1Pressed = false;
        }
        if (code == KeyEvent.VK_2) {
            num2Pressed = false;
        }
        if (code == KeyEvent.VK_3) {
            num3Pressed = false;
        }
        if (code == KeyEvent.VK_4) {
            num4Pressed = false;
        }
        if (code == KeyEvent.VK_5) {
            num5Pressed = false;
        }
        if (code == KeyEvent.VK_6) {
            num6Pressed = false;
        }
        if (code == KeyEvent.VK_7) {
            num7Pressed = false;
        }
        if (code == KeyEvent.VK_8) {
            num8Pressed = false;
        }
        if (code == KeyEvent.VK_9) {
            num9Pressed = false;
        }

    }

}
