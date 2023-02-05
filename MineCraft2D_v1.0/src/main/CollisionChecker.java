package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkBlock(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.hitBox.x;
        int entityRightWorldX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int entityTopWorldY = entity.worldY + entity.hitBox.y;
        int entityBottomWorldY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;


        int blockNum1, blockNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                blockNum1 = gp.blockM.mapBlockNum[entityLeftCol][entityTopRow];
                blockNum2 = gp.blockM.mapBlockNum[entityRightCol][entityTopRow];

                if (gp.blockM.block[blockNum1].collision == true || gp.blockM.block[blockNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                blockNum1 = gp.blockM.mapBlockNum[entityLeftCol][entityBottomRow];
                blockNum2 = gp.blockM.mapBlockNum[entityRightCol][entityBottomRow];

                if (gp.blockM.block[blockNum1].collision == true || gp.blockM.block[blockNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                blockNum1 = gp.blockM.mapBlockNum[entityLeftCol][entityTopRow];
                blockNum2 = gp.blockM.mapBlockNum[entityLeftCol][entityBottomRow];

                if (gp.blockM.block[blockNum1].collision == true || gp.blockM.block[blockNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                blockNum1 = gp.blockM.mapBlockNum[entityRightCol][entityTopRow];
                blockNum2 = gp.blockM.mapBlockNum[entityRightCol][entityBottomRow];

                if (gp.blockM.block[blockNum1].collision == true || gp.blockM.block[blockNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

}
