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

  public Player(GamePanel gamePanel, KeyHandler keyH) {
      this.gamePanel = gamePanel;
      this.keyH = keyH;

      setDefaultValues();
      getPlayerImage();
  }

  public void setDefaultValues() {
      x = 100;
      y = 100;
      speed = 4;
      direction = "down";
  }

  public void getPlayerImage(){
      try{

          front = ImageIO.read(getClass().getResourceAsStream("/kitty/Kitty01 FRONT.png"));
          back = ImageIO.read(getClass().getResourceAsStream("/kitty/Kitty01 BACK.png"));
          down = ImageIO.read(getClass().getResourceAsStream("/kitty/Kitty01Down.png"));
          left = ImageIO.read(getClass().getResourceAsStream("/kitty/Kitty01LEFT.png"));
          right = ImageIO.read(getClass().getResourceAsStream("/kitty/Kitty01RIGHT.png"));



      } catch (IOException e) {
          e.printStackTrace();


      }
  }

  public void update() {
      if(keyH.upPressed == true){
          direction = "up";
          y -= speed;
      }
      else if (keyH.downPressed == true) {
          direction = "down";
          y += speed;
      }
      else if (keyH.leftPressed == true){
          direction = "left";
          x -= speed;
      }
      else if (keyH.rightPressed == true) {
          direction = "right";
          x += speed;
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
      g2.drawImage(image, x, y, w * 5, h * 5, null); // scales up 3x without stretching

  }
}
