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

        hitBox = new Rectangle();
        hitBox.x = 25;
        hitBox.y = 40;
        hitBox.width = 32;
        hitBox.height = 80;

        legHitBox = new Rectangle();
        legHitBox.x = screenX + 26;
        legHitBox.y = screenY + 119;
        legHitBox.width = 28;
        legHitBox.height = 1;

        rightHitBox = new Rectangle();
        rightHitBox.x = screenX + 56;
        rightHitBox.y = screenY + 40;
        rightHitBox.width = 1;
        rightHitBox.height = 80;

        leftHitBox = new Rectangle();
        leftHitBox.x = screenX + 24;
        leftHitBox.y = screenY + 40;
        leftHitBox.width = 1;
        leftHitBox.height = 80;

        topHitBox = new Rectangle();
        topHitBox.x = screenX + 26;
        topHitBox.y = screenY + 39;
        topHitBox.width = 28;
        topHitBox.height = 1;


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

        if (KeyH.upPressed == true && downCollision == true) {
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

        rightCollision = false;
        leftCollision = false;
        upCollision = false;
        downCollision = false;

        gp.cChecker.checkBlock(this);
        int objIndex = gp.cChecker.checkObject(this, true);

        pickUpItem(objIndex);

        switch (directionX) {
            case "left": if (leftCollision == false) { worldX -= speed; } break;
            case "right": if (rightCollision == false) { worldX += speed; } break;
        }
        switch (directionY) {
            case "up": if (upCollision == false) { jumpFPU = 5;} break;
            case "down": if (downCollision == false) { worldY += speed; } break;
        }
        jump(20);
    }
    public void jump(int jumpDistence) {
        if (jumpFPU > 0 && upCollision == false) {
            worldY -= jumpDistence;
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
        g2d.setColor(Color.WHITE);
        g2d.drawRect(hitBox.x + screenX, hitBox.y + screenY, hitBox.width, hitBox.height);

        g2d.setColor(Color.BLUE);
        g2d.drawRect(legHitBox.x, legHitBox.y, legHitBox.width, legHitBox.height);
        g2d.drawRect(rightHitBox.x, rightHitBox.y, rightHitBox.width, rightHitBox.height);
        g2d.drawRect(leftHitBox.x, leftHitBox.y, leftHitBox.width, leftHitBox.height);
        g2d.drawRect(topHitBox.x, topHitBox.y, topHitBox.width, topHitBox.height);
    }
}









