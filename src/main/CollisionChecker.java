package main;

import entity.Entity;

import java.awt.*;

public class CollisionChecker {

    GamePanel gp;
    // down
    Rectangle blockRect1, blockRect2, blockRect3;
    // right
    Rectangle blockRect4, blockRect5;
    // left
    Rectangle blockRect6, blockRect7;
    // up
    Rectangle blockRect8;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkBlock(Entity entity) {

        // Down Collision
        for (int i=0;i<3;i++) {
            // Block Detection
            int blockX = ((gp.player.worldX + gp.tileSize) / gp.tileSize) - 1 + i;
            int blockY = (gp.player.worldY + (3 * gp.tileSize)) / gp.tileSize;

            // Detect blocksPos[blockX][blockY]'s x and y position
            int worldX = blockX * gp.tileSize;
            int worldY = blockY * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            int blockNum = gp.blockM.mapBlockNum[blockX][blockY];

            if (gp.blockM.block[blockNum].collision == true) {
                if (i == 2) { blockRect1 = new Rectangle(screenX, screenY, gp.tileSize, 1); }
                if (i == 1) { blockRect2 = new Rectangle(screenX, screenY, gp.tileSize, 1); }
                if (i == 0) { blockRect3 = new Rectangle(screenX, screenY, gp.tileSize, 1); }
            } else {
                if (i == 2) { blockRect1 = new Rectangle(0, 0, 0, 0); }
                if (i == 1) { blockRect2 = new Rectangle(0, 0, 0, 0); }
                if (i == 0) { blockRect3 = new Rectangle(0, 0, 0, 0); }
            }

        }

        entity.legHitBox.y += 1;
        if (entity.legHitBox.intersects(blockRect1) || entity.legHitBox.intersects(blockRect2) || entity.legHitBox.intersects(blockRect3)) {
            entity.downCollision = true;
        }
        entity.legHitBox.y -= 1;

        // Right Collision
        for (int i=0;i<2;i++) {
            // Block Detection
            int blockX = ((gp.player.worldX + gp.tileSize) / gp.tileSize) + 1;
            int blockY = ((gp.player.worldY + (2 * gp.tileSize)) / gp.tileSize) - i;

            // Detect blocksPos[blockX][blockY]'s x and y position
            int worldX = blockX * gp.tileSize;
            int worldY = blockY * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            int blockNum = gp.blockM.mapBlockNum[blockX][blockY];

            if (gp.blockM.block[blockNum].collision == true) {
                if (i == 1) { blockRect4 = new Rectangle(screenX, screenY, gp.tileSize, gp.tileSize); }
                if (i == 0) { blockRect5 = new Rectangle(screenX, screenY, gp.tileSize, gp.tileSize); }
            } else {
                if (i == 1) { blockRect4 = new Rectangle(0, 0, 0, 0); }
                if (i == 0) { blockRect5 = new Rectangle(0, 0, 0, 0); }
            }
        }
        if (entity.rightHitBox.intersects(blockRect4) || entity.rightHitBox.intersects(blockRect5)) {
            entity.rightCollision = true;
        }

        // Left Collision
        for (int i=0;i<2;i++) {
            // Block Detection
            int blockX = ((gp.player.worldX + gp.tileSize) / gp.tileSize) - 1;
            int blockY = ((gp.player.worldY + (2 * gp.tileSize)) / gp.tileSize) - i;

            // Detect blocksPos[blockX][blockY]'s x and y position
            int worldX = blockX * gp.tileSize;
            int worldY = blockY * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            int blockNum = gp.blockM.mapBlockNum[blockX][blockY];

            if (gp.blockM.block[blockNum].collision == true) {
                if (i == 1) { blockRect6 = new Rectangle(screenX, screenY, gp.tileSize, gp.tileSize); }
                if (i == 0) { blockRect7 = new Rectangle(screenX, screenY, gp.tileSize, gp.tileSize); }
            } else {
                if (i == 1) { blockRect6 = new Rectangle(0, 0, 0, 0); }
                if (i == 0) { blockRect7 = new Rectangle(0, 0, 0, 0); }
            }
        }
        if (entity.leftHitBox.intersects(blockRect6) || entity.leftHitBox.intersects(blockRect7)) {
            entity.leftCollision = true;
        }

        // Up Collision
        for (int i=0;i<1;i++) {
            // Block Detection
            int blockX = ((gp.player.worldX + gp.tileSize) / gp.tileSize) + i;
            int blockY = gp.player.worldY / gp.tileSize;

            // Detect blocksPos[blockX][blockY]'s x and y position
            int worldX = blockX * gp.tileSize;
            int worldY = blockY * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            int blockNum = gp.blockM.mapBlockNum[blockX][blockY];

            if (gp.blockM.block[blockNum].collision == true) {
                if (i == 0) { blockRect8 = new Rectangle(screenX, screenY + gp.tileSize, gp.tileSize, 1); }
            } else {
                if (i == 0) { blockRect8 = new Rectangle(0, 0, 0, 0); }
            }

            if (gp.blockM.block[blockNum].collision == true) {
                if (entity.directionY == "up") {
                    entity.upCollision = true;
                    entity.worldY -= entity.speed;
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i=0;i<gp.obj.length;i++) {

            if (gp.obj[i] != null) {

                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;

                gp.obj[i].hitBox.x = gp.obj[i].worldX + gp.obj[i].hitBox.x;
                gp.obj[i].hitBox.y = gp.obj[i].worldY + gp.obj[i].hitBox.y;

                switch (entity.directionY) {
                    case "up":
                        entity.hitBox.y -= entity.speed;

                        if (entity.hitBox.intersects(gp.obj[i].hitBox)) {

                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitBox.y += entity.speed;

                        if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                switch (entity.directionX) {
                    case "left":
                        entity.hitBox.x -= entity.speed;

                        if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.hitBox.x += entity.speed;

                        if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                gp.obj[i].hitBox.x = gp.obj[i].hitBoxDefaultX;
                gp.obj[i].hitBox.y = gp.obj[i].hitBoxDefaultY;

                // Object Collision
                // Get the block number of the block the object is standing on
                int blockX = ((gp.obj[i].worldX + gp.tileSize) / gp.tileSize) - 1;
                int blockY = ((gp.obj[i].worldY - 20) + gp.tileSize) / gp.tileSize;
                int blockNum = gp.blockM.mapBlockNum[blockX][blockY];

                System.out.println(blockNum);
                int Gravity = 2;
                // If the block is not solid, the object will fall
                if (gp.blockM.block[blockNum].collision != true) {
                    gp.obj[i].worldY += Gravity;
                    Gravity++;
                }else {
                    Gravity = 2;
                }
            }
        }

        return index;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.draw(blockRect1);
        g2d.draw(blockRect2);
        g2d.draw(blockRect3);
        g2d.draw(blockRect4);
        g2d.draw(blockRect5);
        g2d.draw(blockRect6);
        g2d.draw(blockRect7);
        g2d.draw(blockRect8);
    }
}
