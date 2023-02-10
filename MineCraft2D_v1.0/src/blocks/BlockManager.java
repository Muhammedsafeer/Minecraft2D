package blocks;


import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BlockManager {

    GamePanel gp;
    public Block[] block;

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


    public String[][] blocksPos = new String[worldCol][worldRow];
    public int[][] mapBlockNum = new int[worldCol][worldRow];

    BufferedImage widgets;

    public BlockManager(GamePanel gp) {
        this.gp = gp;

        block = new Block[999];

        getBlockImage();
        generateWorld();

        for (int i = 0; i < blocksPos.length; i++) {
            for (int j = 0; j < blocksPos[i].length; j++) {
                if (blocksPos[i][j] == null) { mapBlockNum[i][j] = 0; }
                if (blocksPos[i][j] == block[1].block) { mapBlockNum[i][j] = 1; }
                if (blocksPos[i][j] == block[2].block) { mapBlockNum[i][j] = 2; }
                if (blocksPos[i][j] == block[3].block) { mapBlockNum[i][j] = 3; }
                if (blocksPos[i][j] == block[4].block) { mapBlockNum[i][j] = 4; }
                if (blocksPos[i][j] == block[5].block) { mapBlockNum[i][j] = 5; }
            }
        }
    }

    public void getBlockImage() {
        try {
            // Air Block
            block[0] = new Block();
            block[0].is = getClass().getResourceAsStream("/minecraft2d/textures/block/air.png");
            assert block[0].is != null;
            block[0].image = ImageIO.read(block[0].is);
            block[0].block = "minecraft2d:air";

            // Grass Block
            block[1] = new Block();
            block[1].is = getClass().getResourceAsStream("/minecraft2d/textures/block/grass_block_plain.png");
            assert block[1].is != null;
            block[1].image = ImageIO.read(block[1].is);
            block[1].block = "minecraft2d:grass_block";
            block[1].collision = true;

            // Dirt Block
            block[2] = new Block();
            block[2].is = getClass().getResourceAsStream("/minecraft2d/textures/block/dirt.png");
            assert block[2].is != null;
            block[2].image = ImageIO.read(block[2].is);
            block[2].block = "minecraft2d:dirt";
            block[2].collision = true;

            // Stone Block
            block[3] = new Block();
            block[3].is = getClass().getResourceAsStream("/minecraft2d/textures/block/stone.png");
            assert block[3].is != null;
            block[3].image = ImageIO.read(block[3].is);
            block[3].block = "minecraft2d:stone";
            block[3].collision = true;

            // Oak Log
            block[4] = new Block();
            block[4].is = getClass().getResourceAsStream("/minecraft2d/textures/block/oak_log.png");
            assert block[4].is != null;
            block[4].image = ImageIO.read(block[4].is);
            block[4].block = "minecraft2d:oak_log";

            // Oak Leaves
            block[5] = new Block();
            block[5].is = getClass().getResourceAsStream("/minecraft2d/textures/block/oak_leaves.png");
            assert block[5].is != null;
            block[5].image = ImageIO.read(block[5].is);
            block[5].block = "minecraft2d:oak_leaves";

            InputStream is = getClass().getResourceAsStream("/minecraft2d/textures/gui/widgets.png");
            widgets = ImageIO.read(is);


        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    //GENERATING WORLD
    public void generateWorld() {
        generateGrassBlock();
        smoothenTerrain();
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
    public void smoothenTerrain() {
        int col = 0;
        int row = 0;
        while (col < worldCol && row < worldRow) {


            if (blocksPos[col][row] == block[1].block && col < worldCol - 2) {
                // ___   ___
                //    |_|
                if (blocksPos[col + 1][row] == null && blocksPos[col + 2][row] == block[1].block) {
                    blocksPos[col + 1][row] = block[1].block;
                    blocksPos[col + 1][row + 1] = null;
                }

                //    ___
                // ___| |___
                if (blocksPos[col + 1][row - 1] == block[1].block &&
                    blocksPos[col + 2][row - 1] == null &&
                    blocksPos[col + 2][row - 2] == null) {
                    blocksPos[col + 1][row] = block[1].block;
                    blocksPos[col + 1][row - 1] = null;
                }

            }

            // For Bug Fixing
            if (blocksPos[col][row] == block[1].block && col < worldCol - 1) {
                if (blocksPos[col + 1][row + 1] == null &&
                    blocksPos[col + 1][row] == null &&
                    blocksPos[col + 1][row - 1] == null) {
                    for (int i=0;i<worldRow;i++) {
                        if (blocksPos[col + 1][i] == block[1].block) {
                            blocksPos[col + 1][i] = null;
                        }
                    }
                    blocksPos[col + 1][row] = block[1].block;
                }
            }



            col++;
            if (col == worldCol) {
                col = 0;
                row++;
            }
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
                int stoneRow[] = {row + 2, row + 3, row + 4};
                int randomStoneRow = stoneRow[(int)(Math.random() * 3)];

                while (randomStoneRow <= worldRow - 1) {
                    blocksPos[col][randomStoneRow] = block[3].block;
                    randomStoneRow++;
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
        try {

            int col = 0;
            int row = 0;
            while (col < worldCol && row < worldRow) {



                if (blocksPos[col][row] == block[1].block) {
                    int canGenerateTree = (int) (Math.random() * 50);
                    if (canGenerateTree == 0) {
                        String treeType[] = {"Normal_Oak_Tree"/*, "Normal_Oak_Tree", "Normal_Oak_Tree", "Fancy_Oak_Tree", "Fancy_Oak_Tree", "Balloon_Oak_Tree"*/};
                        String randomTreeType = treeType[(int) (Math.random() * treeType.length)];


                        if (randomTreeType == "Normal_Oak_Tree") {

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
                        }
                    }
                }
                col++;
                if (col == worldCol) {
                    col = 0;
                    row++;
                }
            }


        }catch (Exception e) {

            int col = 0;
            int row = 0;

            while (col < worldCol && row < worldRow) {

                if (blocksPos[col][row] == block[4].block || blocksPos[col][row] == block[5].block) {
                    blocksPos[col][row] = null;
                }

                col++;
                if (col == worldCol) {
                    col = 0;
                    row++;
                }
            }
            generateTrees();

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

        g2d.drawImage(widgets.getSubimage(240, 0, 16, 16), gp.mouseX - 16, gp.mouseY - 16, 32, 32, null);

        // Block Detection
        int blockX = (gp.mouseX + gp.player.worldX - gp.player.screenX) / gp.tileSize;
        int blockY = (gp.mouseY + gp.player.worldY - gp.player.screenY) / gp.tileSize;
        if (blockX >= 0 && blockX < worldCol && blockY >= 0 && blockY < worldRow) {
            if (blocksPos[blockX][blockY] != null) {
                g2d.setColor(Color.WHITE);
                g2d.drawString(blocksPos[blockX][blockY], gp.mouseX + 16, gp.mouseY);

                if (gp.mouseLeftPressed == true) {
                    if (blocksPos[blockX][blockY] == "minecraft2d:grass_block") {  }

                    blocksPos[blockX][blockY] = "minecraft2d:air";
                    mapBlockNum[blockX][blockY] = 0;
                }
            }
        }

    }
}