package entity;

import java.awt.Rectangle;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage front,back, left, right;
    public String direction;
    public Rectangle solidArea;
    public boolean collisionON = false;
}
