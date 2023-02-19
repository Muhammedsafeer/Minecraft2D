package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class grass_block extends SuperObject{

    public grass_block() {

        name = "grass_block";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/minecraft2d/textures/item/grass_block.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
