package blocks;


import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BlockManager {

	GamePanel gp;
	Block[] block;
	
	int mapTileNum[][];
	
	public String blocksPos[][];
	int chunkX = 16;
	int chunkY = 256;
	public int maxChunks = chunkX * 5;
	
	int chunksNum = 1;
	
	String line[] = new String[256];
	File chunk[];
	
	int currentSurface = 195;
	int currentXCol = 9;
	int _currentSurface = 195;
	int _currentXCol = 8;
	
	boolean createdChunk0Grass_block = false;
	boolean createdChunk0Dirt = false;
	
	String[] currentChunks = new String[5];
	
	public BlockManager(GamePanel gp) {
		this.gp = gp;
		
		block = new Block[999];
		mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
		
		blocksPos = new String[maxChunks][chunkY];

		
		getBlockImage();
		System.out.println(currentChunks[4]);
		
	}
	
	public void getBlockImage() {
		try {
			
			block[0] = new Block();
			block[0].image = ImageIO.read(getClass().getResource("/blocks/air.png"));
			block[0].block = "minecraft2d:air";
			
			block[1] = new Block();
			block[1].image = ImageIO.read(getClass().getResource("/blocks/grass_block.png"));
			block[1].block = "minecraft2d:grass_block";
			
			block[2] = new Block();
			block[2].image = ImageIO.read(getClass().getResource("/blocks/dirt.png"));
			block[2].block = "minecraft2d:dirt";
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	// GENERATE WORLD
	public void generateWorld() {
		generateGrassBlock();
		generateDirtBlock();
	}
	public void generateGrassBlock() {
		if (createdChunk0Grass_block == false) {
			int _blockDirection = (int)(Math.random() * 3);

			int blockTimes = (int)(Math.random() * 5);
			while (_currentXCol > -1) {
				blocksPos[_currentXCol][_currentSurface] = block[1].block;
				if (blockTimes <= 0) {
					blockTimes = (int)(Math.random() * 5);
					_blockDirection = (int)(Math.random() * 3);
				}
				if (_blockDirection == 0) { 
					_currentSurface += 1;
				}
				if (_blockDirection == 2) {
					_currentSurface -= 1;
				}
				_currentXCol--;
				blockTimes--;
			}
			
			int blockDirection = (int)(Math.random() * 3);
			
			while (currentXCol < chunkX) {
				
				blocksPos[currentXCol][currentSurface] = block[1].block;
				
				if (blockTimes <= 0) {
					blockTimes = (int)(Math.random() * 5);
					blockDirection = (int)(Math.random() * 3);
				}
				if (blockDirection == 0) { currentSurface += 1; }
				if (blockDirection == 2) { currentSurface -= 1; }
				
				
				currentXCol++;
				blockTimes--;
			}
			currentChunks[2] = "0";
			createdChunk0Grass_block = true;
		}
		if (currentChunks[3] == null) {
			if (currentChunks[2].contains("+") || currentChunks[2] == "0") {
				
				int blockDirection = (int)(Math.random() * 3);
				int blockTimes = (int)(Math.random() * 5);
				
				while (currentXCol < chunkX * 2) {
					
					blocksPos[currentXCol][currentSurface] = block[1].block;
					
					if (blockTimes <= 0) {
						blockTimes = (int)(Math.random() * 5);
						blockDirection = (int)(Math.random() * 3);
					}
					if (blockDirection == 0) { currentSurface += 1; }
					if (blockDirection == 2) { currentSurface -= 1; }
					
					currentXCol++;
					System.out.println(currentXCol);
					blockTimes--;
				}
				currentChunks[3] = "+1";
			}
		}
		if (currentChunks[4] == null) {
			if (currentChunks[3].contains("+") || currentChunks[3] == "0") {
				
				int blockDirection = (int)(Math.random() * 3);
				int blockTimes = (int)(Math.random() * 5);
				
				while (currentXCol < chunkX * 3) {
					
					blocksPos[currentXCol][currentSurface] = block[1].block;
					
					if (blockTimes <= 0) {
						blockTimes = (int)(Math.random() * 5);
						blockDirection = (int)(Math.random() * 3);
					}
					if (blockDirection == 0) { currentSurface += 1; }
					if (blockDirection == 2) { currentSurface -= 1; }
					
					currentXCol++;
					System.out.println(currentXCol);
					blockTimes--;
				}
			}
		}
		
	}
	public void generateDirtBlock() {
		if (createdChunk0Dirt == false) {
			int col = 0;
			int row = 0;
			while (col < maxChunks && row < chunkY) {
				if (blocksPos[col][row] == block[1].block) {
					blocksPos[col][row + 1] = block[2].block;
					blocksPos[col][row + 2] = block[2].block;
					blocksPos[col][row + 3] = block[2].block;
					blocksPos[col][row + 4] = block[2].block;
				}
				
				col++;
				if (col == maxChunks) {
					col = 0;
					row++;
				}
			}
			createdChunk0Dirt = true;
		}
	}
	
//	public void loadMap() {
//		try {
//			InputStream is = getClass().getResourceAsStream("/world1/chunk" + chunksNum +".txt");
//			BufferedReader br = new BufferedReader(new InputStreamReader(is));
//			
//			int col = 0;
//			int row = 0;
//			while (col < chunkX && row < chunkY) {
//				
//				String line = br.readLine();
//				
//				while (col < chunkX) {
//					
//					String numbers[] = line.split(" ");
//					
//					int num = Integer.parseInt(numbers[col]);
//					
//					mapTileNum[col][row] = num;
//					col++;
//				}
//				if (col == chunkX) {
//					col = 0;
//					row++;
//				}
//			}
//			br.close();
//			is.close();
//			
//		}catch (Exception e) {
//			System.out.println("Map didn't Load");
//			e.printStackTrace();
//		}
//	}


	
//	public void changeToString() {
//		
//		
//		int col = 0;
//		int row = 0;
//		while (col < chunkX && row < chunkY) {
//			
//			if (blocksPos[col][row] == null) { line[row] += "0 "; }
//			if (blocksPos[col][row] != null) {
//				if (blocksPos[col][row] == block[1].block) { line[row] += "1 "; }
//				if (blocksPos[col][row] == block[2].block) { line[row] += "2 "; }
//			}
//			
//			col++;
//			if (col >=chunkX) {
//				col = 0;
//				row++;
//			}
//		}
//		
//		// REMOVING NULL
//		for (int i=0;i<line.length;i++) {
//			line [i] = line[i].substring(4, 35);
//			System.out.println(line[i]);
//		}
//	}
//	public void checkChunk() {
//		
//		chunk = new File[401];
//		
//		
//		// SAVING TO TXT FILE
//		try {
//			
//			chunk[201 + chunksNum] = new File("saves/world1/chunk" + chunksNum +".txt");
//			if (chunk[201 + chunksNum].createNewFile()) {
//				System.out.println("Creating Map");
//				
//				generateGrassBlock();
//				generateDirtBlock();
//				changeToString();
//				
//				FileWriter bWriter = new FileWriter("saves/world1/chunk" + chunksNum + ".txt");
//				String nextLine = "\n";
//				
//				int j = 0;
//				for (int i=0;i<line.length-1;i++) {
//					bWriter.write(line[i] + nextLine);
//					j = i + 1;
//				}
//				bWriter.write(line[j]);
//				
//				bWriter.close();
//				System.out.println("Created Map");
//				System.out.println("Loading Map");
//
//			}else {
//				System.out.println("Map Already Created, Loading Map");
//			}
//			
//		}catch(IOException e) {
//			
//		}
//	}
	
	public void draw(Graphics2D g2d) {
		generateWorld();
		int col = 0;
		int row = 0;
		while (col < maxChunks && row < chunkY) {
			
			
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
			
			if (col == maxChunks) {
				col = 0;
				row++;
			}
		}
	}
}
