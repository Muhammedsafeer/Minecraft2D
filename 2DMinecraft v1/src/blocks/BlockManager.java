package blocks;


import java.awt.Graphics2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BlockManager {

	GamePanel gp;
	Block[] block;
	
	String blocksPos[][];
	int chunkX = 16;
	int chunkY = 73;
	
	String line[] = new String[73];
	File chunk[];
	
	public BlockManager(GamePanel gp) {
		this.gp = gp;
		
		block = new Block[999];
		
		blocksPos = new String[chunkX][chunkY];
		
		getBlockImage();
		checkChunk();
	}
	
	public void getBlockImage() {
		try {
			
			block[0] = new Block();
			block[0].image = ImageIO.read(getClass().getResource("/blocks/air.png"));
			block[0].block = "minecraft2d:air";
			
			block[1] = new Block();
			block[1].image = ImageIO.read(getClass().getResource("/blocks/grass_block_plain.png"));
			block[1].block = "minecraft2d:grass_block";
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generatingWorld() {
//		int col = 0;
//		int row = 0;
//		while (col < chunkX && row < chunkY) {
//			
//			blocks[col][row] = block[1].block;
//			
//			col++;
//			if (col == gp.maxScreenCol) {
//				row++;
//				col = 0;
//			}
//		}
		
		int _currentSurface = 10;
		int _currentXCol = 8;
		int _previousSurface = 10;
		int _previousCol = 8;
		int _blockDirection = (int)(Math.random() * 3);
		
		while (_currentXCol > -1) {
			blocksPos[_currentXCol][_currentSurface] = block[1].block;
			int blockTimes = (int)(Math.random() * 5);
			if (blockTimes <= 0) {
				_blockDirection = (int)(Math.random() * 3);
			}
			if (_blockDirection == 0) { 
				_currentSurface += 1;
				_previousSurface += 1;
			}
			if (_blockDirection == 2) {
				_currentSurface -= 1;
				_previousSurface -= 1;
			}
			_currentXCol--;
			_previousCol--;
			blockTimes--;
		}
		
		int currentSurface = 10;
		int currentXCol = 9;
		int previousSurface = 10;
		int previousCol = 9;
		int blockDirection = (int)(Math.random() * 3);
		
		while (currentXCol < chunkX) {
			
			blocksPos[currentXCol][currentSurface] = block[1].block;
			
			int blockTimes = (int)(Math.random() * 5);
			if (blockTimes <= 0) {
				blockDirection = (int)(Math.random() * 3);
			}
			if (blockDirection == 0) { currentSurface += 1; previousSurface += 1; }
			if (blockDirection == 2) { currentSurface -= 1; previousSurface -= 1; }
			
			
			currentXCol++;
			previousCol++;
			blockTimes--;
		}
		
		
	}
	public void changeToString() {
		int col = 0;
		int row = 0;
		while (col < chunkX && row < chunkY) {
			
			if (blocksPos[col][row] == null) { line[row] += "0 "; }
			if (blocksPos[col][row] != null) {
				if (blocksPos[col][row] == block[1].block) { line[row] += "1 "; }
			}
			
			col++;
			if (col >=chunkX) {
				col = 0;
				row++;
			}
		}
		
		// REMOVING NULL
//		int lineX = 0;
//		int lineY = 0;
//		while (lineX < chunkX && lineY < chunkY) {
//			
//			line[lineY].substring(0, 3);
//			
//			lineX++;
//			if (lineX >=chunkX) {
//				lineX = 0;
//				lineY++;
//			}
//		}
		
		// REMOVING NULL
		for (int i=0;i<line.length;i++) {
			line [i] = line[i].substring(4, 35);
			System.out.println(line[i]);
		}
	}
	public void checkChunk() {
		
		chunk = new File[401];
		
		
		// SAVING TO TXT FILE
		try {

			chunk[201] = new File("saves/world1/chunk0.txt");
			if (chunk[201].createNewFile()) {
				generatingWorld();
				changeToString();
				
				FileWriter bWriter = new FileWriter("saves/world1/chunk0.txt");
				String nextLine = "\n";
				
				int j = 0;
				for (int i=0;i<line.length-1;i++) {
					bWriter.write(line[i] + nextLine);
					j = i + 1;
				}
				bWriter.write(line[j]);
				
				bWriter.close();
			}else {
				
			}
			
		}catch(IOException e) {
			
		}
	}
	
	public void draw(Graphics2D g2d) {
		int col = 0;
		int row = 0;
		while (col < chunkX && row < chunkY) {
			
			if (blocksPos[col][row] != null) {
				if (blocksPos[col][row] == "minecraft2d:grass_block") { g2d.drawImage(block[1].image, col * gp.tileSize, row * gp.tileSize, gp.tileSize, gp.tileSize, null); }
			}
			col++;
			if (col == chunkX) {
				row++;
				col = 0;
			}
		}
	}
}
