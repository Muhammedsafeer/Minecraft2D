package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import blocks.BlockManager;
import entity.Player;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x TEXTURES
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    public int mouseX;
    public int mouseY;

    public int mouseWorldX;
    public int mouseWorldY;

    public boolean mouseLeftPressed = false;
    public boolean mouseRightPressed = false;

    public BlockManager blockM = new BlockManager(this);
    KeyHandler KeyH = new KeyHandler();
    private MouseHandler mouseH = new MouseHandler();

    Thread gameThread;

    final int FPS_SET = 120;
    final int UPS_SET = 120;

    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, KeyH, mouseH);
    public SuperObject obj[] = new SuperObject[10];

    BufferedImage crossHair;

    // World Settings
    public int maxWorldCol = 176;
    public int maxWorldRow = 256;
    public int worldWidth = tileSize * maxWorldCol;
    public int worldHeight = tileSize * maxWorldRow;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true);
        startGameThread();
        getCrossHairImage();
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void getCrossHairImage() {
        try {
            InputStream is = getClass().getResourceAsStream("/minecraft2d/textures/gui/widgets.png");
            crossHair = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;


        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (gameThread != null) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    public void update() {
        player.update();

        // Get mouse screen position
        mouseX = mouseH.mouseX;
        mouseY = mouseH.mouseY;

        // Get mouse world position
        mouseWorldX = mouseX + player.worldX - player.screenX;
        mouseWorldY = mouseY + player.worldY - player.screenY;

        // Detect mouse click
        if (mouseH.mouseLeftPressed) {
            mouseLeftPressed = true;
        } else {
            mouseLeftPressed = false;
        }
        if (mouseH.mouseRightPressed) {
            mouseRightPressed = true;
        } else {
            mouseRightPressed = false;
        }


    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        blockM.draw(g2d);

        for (int i=0;i<obj.length;i++) {
            if (obj[i] != null) {
                obj[i].draw(g2d, this);
            }
        }

        player.draw(g2d);

        cChecker.draw(g2d);

        // CrossHair
        g2d.drawImage(crossHair.getSubimage(240, 0, 16, 16), mouseX - 16, mouseY - 16, 32, 32, null);

        g2d.dispose();
    }

}















