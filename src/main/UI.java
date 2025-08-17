package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    private final GamePanel gp;
    private Graphics2D g2;

    private final Font arial_40;
    private final Font arial_80B;

    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public boolean gameFinished = false;

    private BufferedImage kitty02Front;

    public double playTime;
    public final DecimalFormat dFormat = new DecimalFormat("#0.00");

    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) {
            // draw HUD for play state here if you want
        } else if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawTitleScreen() {
        g2.setColor(new Color(70,120,80));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "PAWblem solver";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*3;

        // shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        // main color
        g2.setColor(Color.white);
        g2.drawString(text, x,y);

        // image for meow
        x = gp.screenWidth / 2 - (gp.tileSize *2) / 2;
        y += gp.tileSize *2;
        g2.drawImage(gp.Kitty02FRONT, x,y, gp.tileSize * 2, gp.tileSize * 2, null);

        // menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text =  "NEW GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize*3.5;
        g2.drawString(text,x,y);
        if (commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text =  "LOAD GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text =  "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text,x,y);
        if (commandNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    private void drawPauseScreen() {
        g2.setColor(new Color(0, 0, 0, 150)); // semi-transparent overlay
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        String text = "PAUSED";
        g2.setFont(new Font("Kristen ITC", Font.ITALIC, 80));
        g2.setColor(Color.WHITE);

        // get metrics for the current font
        int textWidth = g2.getFontMetrics().stringWidth(text);
        int textHeight = g2.getFontMetrics().getHeight();
        int ascent = g2.getFontMetrics().getAscent();

        // X = centered horizontally
        int x = (gp.screenWidth - textWidth) / 2;
        // Y = centered vertically (baseline corrected)
        int y = (gp.screenHeight - textHeight) / 2 + ascent;

        g2.drawString(text, x, y);
    }


    private int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }



}
