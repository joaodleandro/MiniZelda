package minizelda;

import java.awt.*;

public class Blocks extends Rectangle {

    public Blocks(int x, int y) {
        super(x,y,32,32);
    }

    public void render(Graphics graphics) {
        graphics.drawImage(Spritesheet.tile_Wall,x,y,32,32,null);
//        graphics.setColor(Color.magenta);
//        graphics.fillRect(x,y,width,height);
//        graphics.setColor(Color.black);
//        graphics.drawRect(x,y,width,height);
    }
}
