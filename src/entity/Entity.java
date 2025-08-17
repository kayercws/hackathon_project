package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage front,back, left, right;
    public String direction;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public boolean collisionON = false;
    public int solidAreaDefaultX, solidAreaDefualtY;

    public Entity (GamePanel gp) {
        this.gp = gp;
    }

}
