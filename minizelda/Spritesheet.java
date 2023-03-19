package minizelda;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {

    public static BufferedImage spritesheet;
    public static BufferedImage[] player_front;
    public static BufferedImage tile_Wall;

    public static BufferedImage dirt;

    public Spritesheet() {
        try {
            spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
            dirt = ImageIO.read(getClass().getResource("/10.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        player_front = new BufferedImage[2]; // 2-sprite animation
        player_front[0] = getSprite(0,11,16,16);
        player_front[1] = getSprite(16,11,16,16);
        tile_Wall = dirt.getSubimage(0,0,128,128);
    }

    public static BufferedImage getSprite(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x,y,width,height);
    }

}
