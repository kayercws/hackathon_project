package main;

import entity.NPC_kitty;
import entity.NPC_lake_kitty;
import entity.Player;
import object.SuperObject;
import tile.TileManager;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    // GAME state

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 786 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public BufferedImage Kitty02FRONT;

    // world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public CollisonChecker collisonChecker = new CollisonChecker(this);
    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public NPC_kitty npcKitty = new NPC_kitty(this);
    public NPC_lake_kitty npcLakeKitty = new NPC_lake_kitty(this);
    public SuperObject obj[] = new SuperObject[10];

    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public UI ui = new UI(this);



    // player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;



    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        loadTitleAssets();
    }

    private void loadTitleAssets() {
        try {
            // make sure the file exists at src/main/resources/kitty/Kitty02FRONT.png
            Kitty02FRONT = ImageIO.read(
                    getClass().getResourceAsStream("/kitty/Kitty02FRONT.png")
            );
            // quick sanity check (remove later):
            // System.out.println(getClass().getResource("/kitty/Kitty02FRONT.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupGame() {
        aSetter.setObject();
        gameState = titleState;
        npcKitty = new NPC_kitty(this);
        npcKitty.worldX = player.worldX + tileSize * 1; // right next to player
        npcKitty.worldY = player.worldY - tileSize * 2;

        npcLakeKitty.worldX = player.worldX + tileSize * 1; // right next to player
        npcLakeKitty.worldY = player.worldY - tileSize * 13;


    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // draw screen 0.01666 times per second
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {
            // update info ex. chara positions
            update();

            // draw screen with updated information
            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime); // sleep accpets miliseconds

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {

        if(gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {
            // nothing
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2); // draw tiles first
        player.draw(g2); // then player (on top)
        npcKitty.draw(g2, player);// then NPC
        npcLakeKitty.draw(g2, player);
        ui.draw(g2);             // then UI



        // TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);

        } else {
            tileM.draw(g2);

            for(int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }

            //PLAYER
            player.draw(g2);
            ui.draw(g2);

            //NPC_KITTY
            npcKitty.draw(g2, player);

            npcLakeKitty.draw(g2,player);

        }

        g2.dispose();

    }



}
