package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class stone extends SuperObject{
    public stone() {

        name = "stone";
        id = 3;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/minecraft2d/textures/item/stone.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
