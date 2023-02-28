package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int speed;

    public Rectangle hitBox;
    public Rectangle legHitBox;
    public Rectangle rightHitBox;
    public Rectangle leftHitBox;
    public Rectangle topHitBox;

    public int hitBoxDefaultX, hitBoxDefaultY;

    public String currentAction = "idle_left";
    public String mainAction = "idle_left";

    public String directionX = "right";
    public String directionY = "down";

    BufferedImage image;

    int aniTick, aniIndex, aniSpeed = 30, aniCode;
    int maxAniIndex;

    public boolean rightCollision = false;
    public boolean leftCollision = false;
    public boolean upCollision = false;
    public boolean downCollision = false;

    public String currentItem;
    public int currentItemID;
    public int currentItemSlot;

    public int jumpFPU = 0;

}
