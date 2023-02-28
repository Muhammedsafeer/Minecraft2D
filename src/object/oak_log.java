package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class oak_log extends SuperObject{

    public oak_log() {

        name = "oak_log";
        id = 4;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/minecraft2d/textures/item/oak_log.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
