package main;

import object.grass_block;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.obj[0] = new grass_block();
        gp.obj[0].worldX = 89 * gp.tileSize;
        gp.obj[0].worldY = 193 * gp.tileSize;
    }
}
