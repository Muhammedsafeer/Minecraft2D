package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler KeyH;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler KeyH) {

        this.gp = gp;
        this.KeyH = KeyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefualtValues();
        ImportImage();
        loadAnimations();
    }

    public void setDefualtValues() {

        worldX = 8 + 64 * gp.tileSize;
        worldY = 195 * gp.tileSize;
        speed = 2;
    }

    BufferedImage[][] animations;
    public void loadAnimations() {
        animations = new BufferedImage[4][6];

        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i * 32, j * 64, 32, 64);
            }
        }

    }

    public void ImportImage() {

        InputStream is = getClass().getResourceAsStream("/player/Main.png");

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateAnimationTick() {

        if (aniCode == 0) { maxAniIndex = 5; }
        if (aniCode == 1) { maxAniIndex = 6; }
        if (aniCode == 2) { maxAniIndex = 5; }
        if (aniCode == 3) { maxAniIndex = 6; }

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= maxAniIndex) {
                aniIndex = 0;
            }
        }

    }

    public void update() {

        updateAnimationTick();

        if (currentAction == "idle_left") {
            aniCode = 0;
        }
        if (currentAction == "move_left") {
            aniCode = 1;
        }
        if (currentAction == "idle_right") {
            aniCode = 2;
        }
        if (currentAction == "move_right") {
            aniCode = 3;
        }

        if (KeyH.upPressed == true) {

            worldY -= speed;

        }
        if (KeyH.downPressed == true) {

            worldY += speed;

        }
        if (KeyH.leftPressed == true) {

            currentAction = "move_right";
            mainAction = "right";
            worldX -= speed;

        }if (KeyH.leftPressed == false && KeyH.rightPressed == false && mainAction == "right") { currentAction = "idle_right"; }
        if (KeyH.rightPressed == true) {

            currentAction = "move_left";
            mainAction = "left";
            worldX += speed;

        }if (KeyH.leftPressed == false && KeyH.rightPressed == false && mainAction == "left") { currentAction = "idle_left"; }

    }

    public void draw(Graphics2D g2d) {

        g2d.drawImage(animations[aniCode][aniIndex], screenX, screenY, (int)83.2, (int)166.4, null);
    }
}









