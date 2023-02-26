package main;

import entity.Entity;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener,MouseMotionListener {

    int mouseX, mouseY;

    public boolean mouseLeftPressed = false;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Detect if the mouse left button is pressed
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseLeftPressed = true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Detect if the mouse left button is released
        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseLeftPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
