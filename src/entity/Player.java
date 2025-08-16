package entity;


import main.CollisonChecker;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;

        screenX = gamePanel.screenWidth/2;
        screenY = gamePanel.screenHeight/2;

        setDefaultValues();
        getPlayerImage();
        solidArea = new Rectangle(8, 16, 32, 32);
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{

            front = ImageIO.read(getClass().getResourceAsStream("/kitty/kitty02FRONT2.png"));
            back = ImageIO.read(getClass().getResourceAsStream("/kitty/kitty02BACK.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/kitty/kitty02LEFTy.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/kitty/Kitty02RIGHT.png"));



        } catch (IOException e) {
            e.printStackTrace();


        }
    }

    public void update() {
        boolean moving = false;

        if(keyH.upPressed){
            direction = "up";
            moving = true;
        }
        else if(keyH.downPressed){
            direction = "down";
            moving = true;
        }
        else if(keyH.leftPressed){
            direction = "left";
            moving = true;
        }
        else if(keyH.rightPressed){
            direction = "right";
            moving = true;
        }

        if(moving){
            // CHECK TILE COLLISION
            collisionON = false;
            gamePanel.collisonChecker.checkTile(this);

            // MOVE ONLY if no collision
            if(!collisionON){
                switch (direction) {
                    case "up":    worldY -= speed; break;
                    case "down":  worldY += speed; break;
                    case "left":  worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }
        }
    }


    public void draw(Graphics2D g2) {
//      g2.setColor(Color.white);
//      g2.fillRect(x,y, gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                image = back;
                break;
            case "down":
                image = front;
                break;
            case "right":
                image = right;
                break;
            case "left":
                image = left;
                break;
        }
        int w = image.getWidth();
        int h = image.getHeight();
        g2.drawImage(image, screenX, screenY, w * 3, h * 3, null); // scales up 3x without stretching

    }
}