package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("2DMinecraft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Hide the mouse cursor
        frame.setCursor(frame.getToolkit().createCustomCursor(
                new ImageIcon("").getImage(),
                new java.awt.Point(),
                "null"));
    }
}