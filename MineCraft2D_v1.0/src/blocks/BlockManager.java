package blocks;


import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BlockManager {

    GamePanel gp;
    Block[] block;

    int currentCol = 89;
    int currentRow = 195;
    int _currentCol = 88;
    int _currentRow = 195;

    int chunkCol = 16;
    int chunkRow = 256;

    int chunks = 5;
    int negativeChunks = 5;

    int worldCol = chunkCol * ((chunks + negativeChunks) + 1);
    int worldRow = chunkRow;


     String blocksPos[][] = new String[worldCol][worldRow];

    public BlockManager(GamePanel gp) {
        this.gp = gp;

        block = new Block[999];



        getBlockImage();

    }

    public void getBlockImage() {
        try {

            block[0] = new Block();
            block[0].is = getClass().getResourceAsStream("/blocks/air.png");
            assert block[0].is != null;
            block[0].image = ImageIO.read(block[0].is);
            block[0].block = "minecraft2d:air";

            block[1] = new Block();
            block[1].is = getClass().getResourceAsStream("/blocks/grass_block_plain.png");
            assert block[1].is != null;
            block[1].image = ImageIO.read(block[1].is);
            block[1].block = "minecraft2d:grass_block";

            block[2] = new Block();
            block[2].is = getClass().getResourceAsStream("/blocks/dirt.png");
            assert block[2].is != null;
            block[2].image = ImageIO.read(block[2].is);
            block[2].block = "minecraft2d:dirt";

            block[3] = new Block();
            block[3].is = getClass().getResourceAsStream("/blocks/stone.png");
            assert block[3].is != null;
            block[3].image = ImageIO.read(block[3].is);
            block[3].block = "minecraft2d:stone";

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    //GENERATING WORLD
    public void generateWorld() {
        generateGrassBlock();
        generateDirtBlock();
        generateStoneBlock();
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

    public void draw(Graphics2D g2d) {

        generateWorld();
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
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
                }
            }
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }
}
