package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 786 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // world settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    public CollisonChecker collisonChecker = new CollisonChecker(this);
    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    // game state
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;



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
    }

    public void setupGame() {
        aSetter.setObject();
        gameState = playState;
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
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //TILE
        tileM.draw(g2);

        //OBJECT
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);
        g2.dispose();

    }



}
