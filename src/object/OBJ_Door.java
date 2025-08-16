package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {

    public OBJ_Door() {
        name = "Kitty";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/npc/Kitty01.png"));

        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
