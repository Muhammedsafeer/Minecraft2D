package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler KeyH;
	
	public Player(GamePanel gp, KeyHandler KeyH) {
		
		this.gp = gp;
		this.KeyH = KeyH;
		
		setDefualtValues();
	}
	
	public void setDefualtValues() {
		
		x = 100;
		y = 100;
		speed = 4;
	}
	
	public void update() {
		if (KeyH.upPressed == true) {
			
			y -= speed;
			
		}else if (KeyH.downPressed == true) {
			
			y += speed;
			
		}else if (KeyH.leftPressed == true) {
			
			x -= speed;
			
		}else if (KeyH.rightPressed == true) {
			
			x += speed;
			
		}
	}
	
	public void draw(Graphics2D g2d) {

		g2d.setColor(Color.white);
		g2d.fillRect(x, y, gp.tileSize, gp.tileSize * 2);
	}
}









