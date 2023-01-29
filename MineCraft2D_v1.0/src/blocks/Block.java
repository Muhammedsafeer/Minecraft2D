package blocks;

import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Block {

    public InputStream is;
    public BufferedImage image;
    public boolean collision = false;

    public int x, y;

    public String block;
}
