package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class EvilKitty extends Entity{
    public String dialogue = "im evil kittyuh";

    public EvilKitty(GamePanel gp) {

        super(gp);

        getNPCImage();
    }

    public void getNPCImage(){
        try{

            front = ImageIO.read(getClass().getResourceAsStream("/monster/Monster.png"));
            back = ImageIO.read(getClass().getResourceAsStream("/monster/Monster.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/monster/Monster.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/monster/Monster.png"));



        } catch (IOException e) {
            e.printStackTrace();


        }
    }



    public void draw(Graphics2D g2, Player player) {
        // Calculate screen position relative to player (camera)
        int screenX = worldX - player.worldX + player.screenX;
        int screenY = worldY - player.worldY + player.screenY;

        // Draw NPC image
        g2.drawImage(front, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // Draw dialogue if player is nearby
        if(Math.abs(player.worldX - worldX) < gp.tileSize*1.5 &&
                Math.abs(player.worldY - worldY) < gp.tileSize*1.5){
            g2.setFont(new Font("Serif", Font.PLAIN, 18));
            g2.setColor(Color.WHITE);
            g2.drawString(dialogue, screenX, screenY - 10);
        }
    }


}
