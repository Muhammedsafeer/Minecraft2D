package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public Rectangle hitBox;

    public String currentAction = "idle_left";
    public String mainAction = "idle_left";

    public String direction = "right";

    BufferedImage image;

    int aniTick, aniIndex, aniSpeed = 30, aniCode;
    int maxAniIndex;

    public boolean collisionOn = false;
}
