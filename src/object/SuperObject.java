package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public int id;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle hitBox = new Rectangle(14, 14, 20, 20);
    public int hitBoxDefaultX = 14, hitBoxDefaultY = 14;

    public void draw(Graphics2D g2d, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldY + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            g2d.drawImage(image, screenX, screenY, 20, 20, null);
        }
    }
}
