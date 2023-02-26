package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.MouseHandler;
import object.Inventory;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler KeyH;
    MouseHandler MouseH;
    Inventory inv[];

    BufferedImage widjets;
    BufferedImage hotbarimg;

    public final int screenX;
    public final int screenY;

    public final int hotbarX = 768 / 2 - (378 / 2);
    public final int hotbarY = 534;


    public Player(GamePanel gp, KeyHandler KeyH, MouseHandler MouseH) {

        this.gp = gp;
        this.KeyH = KeyH;
        this.MouseH = MouseH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        hitBox = new Rectangle(25, 40, 32, 80);
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;

        setDefualtValues();
        ImportImage();
        loadAnimations();
    }

    public void setDefualtValues() {
        worldX = 89 * gp.tileSize;
        worldY = 193 * gp.tileSize;
        speed = 2;

        inv = new Inventory[36];
        for (int i=0;i<inv.length;i++) {
            inv[i] = new Inventory(gp, this);
        }
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

        InputStream is = getClass().getResourceAsStream("/minecraft2d/textures/entity/player/steve.png");
        InputStream widjetsIs = getClass().getResourceAsStream("/minecraft2d/textures/gui/widgets.png");

        try {
            image = ImageIO.read(is);
            widjets = ImageIO.read(widjetsIs);
            hotbarimg = widjets.getSubimage(0, 0, 21 * 9, 21);
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
    int jumpFPU = 0;

    public void update() {



        updateAnimationTick();

        directionX = "";
        directionY = "";

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

        if (KeyH.upPressed == true && downCollisionOnY == true) {
            directionY = "up";
        }
        if (directionY != "up") {
            directionY = "down";
        }
        if (KeyH.leftPressed == true) {
            currentAction = "move_right";
            mainAction = "right";
            directionX = "left";
        }
        if (KeyH.rightPressed == true) {
            currentAction = "move_left";
            mainAction = "left";
            directionX = "right";
        }

        if (KeyH.leftPressed == false && KeyH.rightPressed == false && mainAction == "right") { currentAction = "idle_right"; }
        if (KeyH.leftPressed == false && KeyH.rightPressed == false && mainAction == "left") { currentAction = "idle_left"; }

        collisionOnX = false;
        collisionOnY = false;
        downCollisionOnY = false;
        gp.cChecker.checkBlock(this);
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpItem(objIndex);

        if (collisionOnX == false) {

            switch (directionX) {
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
        if (collisionOnY == false) {
            switch (directionY) {
                case "up": jumpFPU = 5; break;
            }
        }

        if (downCollisionOnY == false) {
            switch (directionY) {
                case "down": worldY += speed; break;
            }
        }
        if (jumpFPU > 0 && collisionOnY == false) {
            worldY -= 20;
            jumpFPU--;
        }
    }
    public void pickUpItem(int i) {
        if (i != 999) {
            String item = gp.obj[i].name;
            for (int j=0;j<inv.length;j++) {
                if (inv[j].item == item) {
                    inv[j].count++;
                    inv[j].img = gp.obj[i].image;
                    gp.obj[i] = null;
                    break;
                }
                if (inv[j].item == "empty") {
                    inv[j].item = item;
                    inv[j].count++;
                    gp.obj[i] = null;
                    break;
                }
            }

        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(animations[aniCode][aniIndex], screenX, screenY, (int)83.2, (int)166.4, null);
        g2d.drawImage(hotbarimg, hotbarX, hotbarY, 378, 42, null);

        for (int i=0;i<inv.length;i++) {
            inv[i].draw(g2d, i);
        }
    }
}









