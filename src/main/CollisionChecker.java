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


        try {
            switch (entity.directionX) {

                case "left":
                    entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                    blockNum1 = gp.blockM.mapBlockNum[entityLeftCol][entityTopRow];
                    blockNum2 = gp.blockM.mapBlockNum[entityLeftCol][entityBottomRow];

                    if (gp.blockM.block[blockNum1].collision == true || gp.blockM.block[blockNum2].collision == true) {
                        entity.collisionOnX = true;
                    }
                    break;
                case "right":
                    entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                    blockNum1 = gp.blockM.mapBlockNum[entityRightCol][entityTopRow];
                    blockNum2 = gp.blockM.mapBlockNum[entityRightCol][entityBottomRow];

                    if (gp.blockM.block[blockNum1].collision == true || gp.blockM.block[blockNum2].collision == true) {
                        entity.collisionOnX = true;
                    }
                    break;
            }
            switch (entity.directionY) {

                case "up":
                    entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                    blockNum1 = gp.blockM.mapBlockNum[entityLeftCol][entityTopRow];
                    blockNum2 = gp.blockM.mapBlockNum[entityRightCol][entityTopRow];

                    if (gp.blockM.block[blockNum1].collision == true || gp.blockM.block[blockNum2].collision == true) {
                        entity.collisionOnY = true;
                    }
                    break;
                case "down":
                    entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                    blockNum1 = gp.blockM.mapBlockNum[entityLeftCol][entityBottomRow];
                    blockNum2 = gp.blockM.mapBlockNum[entityRightCol][entityBottomRow];

                    if (gp.blockM.block[blockNum1].collision == true && gp.blockM.block[blockNum2].collision == true) {
                        entity.downCollisionOnY = true;
                    }
                    break;
            }
        }catch (Exception e) {
            entity.worldX = 89 * gp.tileSize;
            entity.worldY = 193 * gp.tileSize;
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
            }
        }

        return index;
    }

}
