package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class dirt extends SuperObject{

    public dirt() {

        name = "dirt";
        id = 2;

        try {

            image = ImageIO.read(getClass().getResourceAsStream("/minecraft2d/textures/item/dirt.png"));

        }catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }

}
