package main;

import entity.Entity;

import java.util.Map;

public class CollisonChecker {
    GamePanel gamePanel;

    public CollisonChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRigthCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileM.mapTileNum[entityRigthCol][entityTopRow];

                if (gamePanel.tileM.tile[tileNum1].collision == true ||
                        gamePanel.tileM.tile[tileNum2].collision == true) {
                    entity.collisionON = true;

                }
                break;
            case "down":

                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileM.mapTileNum[entityRigthCol][entityBottomRow];

                if (gamePanel.tileM.tile[tileNum1].collision == true ||
                        gamePanel.tileM.tile[tileNum2].collision == true) {
                    entity.collisionON = true;
                }

                break;


            case "left":

                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                if (gamePanel.tileM.tile[tileNum1].collision == true ||
                        gamePanel.tileM.tile[tileNum2].collision == true) {
                    entity.collisionON = true;
                }

                break;


            case "right":

                entityRigthCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileM.mapTileNum[entityRigthCol][entityBottomRow];

                if (gamePanel.tileM.tile[tileNum1].collision == true ||
                        gamePanel.tileM.tile[tileNum2].collision == true) {
                    entity.collisionON = true;
                }

                break;


        }
    }
}
