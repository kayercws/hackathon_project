package entity;


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
      if(keyH.upPressed == true){
          direction = "up";
          worldY -= speed;
      }
      else if (keyH.downPressed == true) {
          direction = "down";
          worldY += speed;
      }
      else if (keyH.leftPressed == true){
          direction = "left";
          worldX -= speed;
      }
      else if (keyH.rightPressed == true) {
          direction = "right";
          worldX += speed;
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
