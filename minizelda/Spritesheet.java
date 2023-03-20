package minizelda;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Spritesheet {

    public static BufferedImage spritesheet;
    public static BufferedImage slime;
    public static BufferedImage[] slime_walk;
    public static BufferedImage[] player_front;
    public static BufferedImage player_dmg;
    public static BufferedImage tile_Wall;
    public static BufferedImage dirt;

    public Spritesheet() {
        try {
            spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
            dirt = ImageIO.read(getClass().getResource("/10.png"));
            slime = ImageIO.read(getClass().getResource("/slime-spritesheet.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        player_front = new BufferedImage[2]; // 2-sprite animation
        player_front[0] = getSprite(0,11,16,16);
        player_front[1] = getSprite(16,11,16,16);

        slime_walk = new BufferedImage[4];
        slime_walk[0] = getSpriteSlime(5,46,26,18);
        slime_walk[1] = getSpriteSlime(34,47,29,17);
        slime_walk[2] = getSpriteSlime(68,49,27,15);
        slime_walk[3] = getSpriteSlime(102,45,25,19);

        player_dmg = getSprite(200,241,16,16);

        tile_Wall = dirt.getSubimage(0,0,128,128);
    }

    public static BufferedImage getSprite(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x,y,width,height);
    }

    public static BufferedImage getSpriteSlime(int x, int y, int width, int height) {
        return slime.getSubimage(x,y,width,height);
    }

}
