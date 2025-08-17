package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean talkPressed; // optional: E to talk/advance dialogue

    public KeyHandler(GamePanel gp) { this.gp = gp; }

    @Override public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // ===== TITLE =====
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) gp.ui.commandNum = (gp.ui.commandNum + 2) % 3;
            if (code == KeyEvent.VK_S) gp.ui.commandNum = (gp.ui.commandNum + 1) % 3;

            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) gp.gameState = gp.playState; // start
                else if (gp.ui.commandNum == 1) { /* options later */ }
                else if (gp.ui.commandNum == 2) System.exit(0);         // quit
            }
            return;
        }

        // ===== PLAY =====
        if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) upPressed = true;
            if (code == KeyEvent.VK_S) downPressed = true;
            if (code == KeyEvent.VK_A) leftPressed = true;     // FIX: no longer nested under S
            if (code == KeyEvent.VK_D) rightPressed = true;    // FIX: no longer nested under S
            if (code == KeyEvent.VK_P) gp.gameState = gp.pauseState;
            if (code == KeyEvent.VK_E) talkPressed = true;     // optional
            return;
        }

        // ===== PAUSE =====
        if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_P) gp.gameState = gp.playState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = false;
        if (code == KeyEvent.VK_S) downPressed = false;
        if (code == KeyEvent.VK_A) leftPressed = false;
        if (code == KeyEvent.VK_D) rightPressed = false;
        if (code == KeyEvent.VK_E) talkPressed = false; // optional
    }
}
