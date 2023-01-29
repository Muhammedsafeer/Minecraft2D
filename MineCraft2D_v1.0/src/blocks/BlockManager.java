package blocks;


import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BlockManager {

    GamePanel gp;
    Block[] block;
    Chunk[] chunk;

    int currentCol = 9;
    int currentRow = 195;
    int _currentCol = 8;
    int _currentRow = 195;

    int chunkCol = 16;
    int chunkRow = 256;

    String blocksPos[][] = new String[16 * 5][256];

    public BlockManager(GamePanel gp) {
        this.gp = gp;

        block = new Block[999];
        chunk = new Chunk[5];


        getBlockImage();

    }

    public void getBlockImage() {
        try {

            block[0] = new Block();
            block[0].is = getClass().getResourceAsStream("/blocks/air.png");
            block[0].image = ImageIO.read(block[0].is);
            block[0].block = "minecraft2d:air";

            block[1] = new Block();
            block[1].is = getClass().getResourceAsStream("/blocks/grass_block_plain.png");
            block[1].image = ImageIO.read(block[1].is);
            block[1].block = "minecraft2d:grass_block";

            block[2] = new Block();
            block[2].is = getClass().getResourceAsStream("/blocks/dirt.png");
            block[2].image = ImageIO.read(block[2].is);
            block[2].block = "minecraft2d:dirt";

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    //GENERATING WORLD
    public void generateWorld() {
        if (chunk[2] == null) {
            chunk[2] = new Chunk();
            if (chunk[2].generated == false) {

                int blockDirection = (int)(Math.random() * 3);
                int blockTimes = (int)(Math.random() * 5);

                while (_currentCol > -1) {
                    blocksPos[_currentCol][_currentRow] = block[1].block;
                    if (blockTimes <= 0) {
                        blockTimes = (int)(Math.random() * 5);
                        blockDirection = (int)(Math.random() * 3);
                    }
                    if (blockDirection == 0) {
                        _currentRow += 1;
                    }
                    if (blockDirection == 2) {
                        _currentRow -= 1;
                    }
                    _currentCol--;
                    blockTimes--;
                }

                while (currentCol < chunkCol) {

                    blocksPos[currentCol][currentRow] = block[1].block;

                    if (blockTimes <= 0) {
                        blockTimes = (int)(Math.random() * 5);
                        blockDirection = (int)(Math.random() * 3);
                    }
                    if (blockDirection == 0) { currentRow += 1; }
                    if (blockDirection == 2) { currentRow -= 1; }

                    currentCol++;
                    blockTimes--;
                }
            }
        }
    }

    public void draw(Graphics2D g2d) {

        generateWorld();

        int col = 0;
        int row = 0;

        for (int i=0;i<chunk.length;i++) {
            if (chunk[i] != null) {
                col = i * 16;
                while (col < chunkCol * chunk.length && row < chunkRow) {

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
                        }
                    }

                    col++;
                    if (col == chunkCol * chunk.length) {
                        col = i * 16;
                        row++;
                    }
                }
            }
        }

    }
}
