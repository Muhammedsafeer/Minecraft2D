package blocks;


import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.ColorConvertOp;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BlockManager {

    GamePanel gp;
    Block[] block;

    // Grass Generation Settings
    int currentCol = 89;
    int currentRow = 195;
    int _currentCol = 88;
    int _currentRow = 195;

    //Tree Generation Settings

    int chunkCol = 16;
    int chunkRow = 256;

    int chunks = 5;
    int negativeChunks = 5;

    int worldCol = chunkCol * ((chunks + negativeChunks) + 1);
    int worldRow = chunkRow;

    public boolean treeGenerated = false;

    String blocksPos[][] = new String[worldCol][worldRow];

    public BlockManager(GamePanel gp) {
        this.gp = gp;

        block = new Block[999];



        getBlockImage();

        generateWorld();

    }

    public void getBlockImage() {
        try {
            // Air Block
            block[0] = new Block();
            block[0].is = getClass().getResourceAsStream("/blocks/air.png");
            assert block[0].is != null;
            block[0].image = ImageIO.read(block[0].is);
            block[0].block = "minecraft2d:air";

            // Grass Block
            block[1] = new Block();
            block[1].is = getClass().getResourceAsStream("/blocks/grass_block_plain.png");
            assert block[1].is != null;
            block[1].image = ImageIO.read(block[1].is);
            block[1].block = "minecraft2d:grass_block";

            // Dirt Block
            block[2] = new Block();
            block[2].is = getClass().getResourceAsStream("/blocks/dirt.png");
            assert block[2].is != null;
            block[2].image = ImageIO.read(block[2].is);
            block[2].block = "minecraft2d:dirt";

            // Stone Block
            block[3] = new Block();
            block[3].is = getClass().getResourceAsStream("/blocks/stone.png");
            assert block[3].is != null;
            block[3].image = ImageIO.read(block[3].is);
            block[3].block = "minecraft2d:stone";

            // Oak Log
            block[4] = new Block();
            block[4].is = getClass().getResourceAsStream("/blocks/oak_log.png");
            assert block[4].is != null;
            block[4].image = ImageIO.read(block[4].is);
            block[4].block = "minecraft2d:oak_log";

            // Oak Leaves
            block[5] = new Block();
            block[5].is = getClass().getResourceAsStream("/blocks/oak_leaves.png");
            assert block[5].is != null;
            block[5].image = ImageIO.read(block[5].is);
            block[5].block = "minecraft2d:oak_leaves";


        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    //GENERATING WORLD
    public void generateWorld() {
        generateGrassBlock();
        generateDirtBlock();
        generateStoneBlock();

        generateTrees();
    }

    public void generateGrassBlock() {
        int blockDirection;
        int blockTimes;
        while (currentCol < worldCol  && currentRow < worldRow) {

            blocksPos[currentCol][currentRow] = "minecraft2d:grass_block";
            blockDirection = (int)(Math.random() * 3);
            blockTimes = (int)(Math.random() * 8);
            if (blockDirection == 0) {
                currentRow--;
            }
            if (blockDirection == 2) {
                currentRow++;
            }
            if (blockTimes <= 0) {
                blockDirection = (int)(Math.random() * 3);
                blockTimes = (int)(Math.random() * 8);
            }

            blockTimes--;
            currentCol++;

        }
        while (_currentCol > -1  && _currentRow < worldRow) {
            blocksPos[_currentCol][_currentRow] = "minecraft2d:grass_block";
            blockDirection = (int)(Math.random() * 3);
            blockTimes = (int)(Math.random() * 8);
            if (blockDirection == 0) {
                _currentRow--;
            }
            if (blockDirection == 2) {
                _currentRow++;
            }
            if (blockTimes <= 0) {
                blockDirection = (int)(Math.random() * 3);
                blockTimes = (int)(Math.random() * 8);
            }

            blockTimes--;
            _currentCol--;
        }
    }
    public void generateDirtBlock() {
        int col = 0;
        int row = 0;
        while (col < worldCol && row < worldRow) {
            if (blocksPos[col][row] == block[1].block) {
                blocksPos[col][row + 1] = block[2].block;
                blocksPos[col][row + 2] = block[2].block;
                blocksPos[col][row + 3] = block[2].block;
                blocksPos[col][row + 4] = block[2].block;
            }
            col++;
            if (col == worldCol) {
                col = 0;
                row++;
            }
        }
    }
    public void generateStoneBlock() {
        int col = 0;
        int row = 0;
        while (col < worldCol && row < worldRow) {
            if (blocksPos[col][row] == block[2].block) {
                int stoneRow = row + 4;
                while (stoneRow <= worldRow - 1) {
                    blocksPos[col][stoneRow] = block[3].block;
                    stoneRow++;
                }
            }
            col++;
            if (col == worldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void generateTrees() {
        if (treeGenerated == false) {
            int col = 0;
            int row = 0;
            while (col < worldCol && row < worldRow) {

                if (blocksPos[col][row] == block[1].block) {
                    int canGenerateTree = (int) (Math.random() * 50);
                    if (canGenerateTree == 0) {
                        int treeType = (int) (Math.random() * 8);
                        //if (treeType == 0) {
                            // Generate Normal oak tree

                            //     _______
                            //    |       |
                            // ___|       |___
                            // |             |
                            // |_____________|
                            //       | |
                            //       | |
                            //       |_|

                            // Log
                            blocksPos[col][row - 1] = block[4].block;
                            blocksPos[col][row - 2] = block[4].block;
                            blocksPos[col][row - 3] = block[4].block;

                            // Leaves
                            blocksPos[col - 2][row - 4] = block[5].block;
                            blocksPos[col - 1][row - 4] = block[5].block;
                            blocksPos[col][row - 4] = block[5].block;
                            blocksPos[col + 1][row - 4] = block[5].block;
                            blocksPos[col + 2][row - 4] = block[5].block;
                            blocksPos[col - 2][row - 5] = block[5].block;
                            blocksPos[col - 1][row - 5] = block[5].block;
                            blocksPos[col][row - 5] = block[5].block;
                            blocksPos[col + 1][row - 5] = block[5].block;
                            blocksPos[col + 2][row - 5] = block[5].block;
                            blocksPos[col - 1][row - 6] = block[5].block;
                            blocksPos[col][row - 6] = block[5].block;
                            blocksPos[col + 1][row - 6] = block[5].block;
                            blocksPos[col - 1][row - 7] = block[5].block;
                            blocksPos[col][row - 7] = block[5].block;
                            blocksPos[col + 1][row - 7] = block[5].block;

                            System.out.println("Tree Generated at" + col + " " + (row - 1));
                        //}
                    }
                }

                col++;
                if (col == worldCol) {
                    col = 0;
                    row++;
                }
            }
            treeGenerated = true;
        }
    }

    public void draw(Graphics2D g2d) {

        int col = 0;
        int row = 0;
        while (col < worldCol && row < worldRow) {
            int worldX = col * gp.tileSize;
            int worldY = row * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                if (blocksPos[col][row] != null) {


                    if (blocksPos[col][row] == "minecraft2d:grass_block") { g2d.drawImage(block[1].image, screenX, screenY, gp.tileSize, gp.tileSize, null); }
                    if (blocksPos[col][row] == "minecraft2d:dirt") { g2d.drawImage(block[2].image, screenX, screenY, gp.tileSize, gp.tileSize, null); }
                    if (blocksPos[col][row] == "minecraft2d:stone") { g2d.drawImage(block[3].image, screenX, screenY, gp.tileSize, gp.tileSize, null); }
                    if (blocksPos[col][row] == "minecraft2d:oak_log") { g2d.drawImage(block[4].image, screenX, screenY, gp.tileSize, gp.tileSize, null); }
                    if (blocksPos[col][row] == "minecraft2d:oak_leaves") { g2d.drawImage(block[5].image, screenX, screenY, gp.tileSize, gp.tileSize, null); }
                }
            }
            col++;
            if (col == worldCol) {
                col = 0;
                row++;
            }
        }
    }
}