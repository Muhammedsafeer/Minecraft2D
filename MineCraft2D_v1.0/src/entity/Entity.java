package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public String currentAction = "idle_left";
    public String mainAction = "idle_left";

    BufferedImage image;

    int aniTick, aniIndex, aniSpeed = 30, aniCode;
    int maxAniIndex;


}
