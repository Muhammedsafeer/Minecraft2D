package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x TEXTURES
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = maxScreenCol * tileSize;
	final int screenHeight = maxScreenRow * tileSize;
	
	KeyHandler KeyH = new KeyHandler();
	Thread gameThread;
	final int FPS_SET = 120;
	final int UPS_SET = 120;
	
	// SET PLAYER'S DEFUALT POSITION
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(KeyH);
		this.setFocusable(true);
		startGameThread();
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;

		
		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
	
		while (gameThread != null) {
			
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			
			if (deltaF >= 1) {
				repaint();
				frames++;
				deltaF--;
			}
	
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	
	}
	
	public void update() {
		if (KeyH.upPressed == true) {
			
			playerY -= playerSpeed;
			
		}else if (KeyH.downPressed == true) {
			
			playerY += playerSpeed;
			
		}else if (KeyH.leftPressed == true) {
			
			playerX -= playerSpeed;
			
		}else if (KeyH.rightPressed == true) {
			
			playerX += playerSpeed;
			
		}
	}
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.white);
		g2d.fillRect(playerX, playerY, tileSize, tileSize * 2);
		
		g2d.dispose();
	}
	
}















