package object;


import entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Inventory {

    public String item;
    public int count;
    public BufferedImage img;

    GamePanel gp;
    Player player;

    public Inventory(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        item = "empty";
        count = 0;
    }
    public void draw(Graphics2D g2d, int slot) {
        int screenX = (player.hotbarX + 8) + (slot * 20);
        int screenY = (player.hotbarY + 8);

       if (item != "empty") {

           try {

               InputStream is = getClass().getResourceAsStream("/minecraft2d/textures/item/" + item + ".png");
               img = ImageIO.read(is);

           }catch (IOException e) {
               e.printStackTrace();
           }
           g2d.drawImage(img, screenX + (slot * 20), screenY, 28, 28, null);
           g2d.setColor(Color.WHITE);
           g2d.drawString(String.valueOf(count), screenX + (slot * 20) + 20, screenY + 30);
       }
    }
}