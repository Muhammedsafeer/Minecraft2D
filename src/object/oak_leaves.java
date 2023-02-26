package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class oak_leaves extends SuperObject {

    public oak_leaves() {

        name = "oak_leaves";

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/minecraft2d/textures/item/oak_leaves.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }}
