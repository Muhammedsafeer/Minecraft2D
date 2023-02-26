package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public Rectangle hitBox;
    public int hitBoxDefaultX, hitBoxDefaultY;

    public String currentAction = "idle_left";
    public String mainAction = "idle_left";

    public String directionX = "right";
    public String directionY = "down";

    BufferedImage image;

    int aniTick, aniIndex, aniSpeed = 30, aniCode;
    int maxAniIndex;

    public boolean collisionOnX = false;
    public boolean collisionOnY = false;
    public boolean downCollisionOnY = false;

    public String currentItem = "none";
}
