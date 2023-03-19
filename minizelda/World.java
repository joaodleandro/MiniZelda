package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static List<Blocks> blocks = new ArrayList<>();

    public World() {
        // x upper axis
        for(int i = 0; i < 20; i++) {
            blocks.add(new Blocks(i*32, 0));
        }
        // y left axis
        for(int i = 0; i < 20; i++) {
            blocks.add(new Blocks(0, i*32));
        }
        // y right axis
        for(int i = 0; i < 20; i++) {
            blocks.add(new Blocks(640-32, i*32));
        }
        // x lower axis
        for(int i = 0; i < 20; i++) {
            blocks.add(new Blocks(i*32, 480-32));
        }
    }

    public static boolean collision(int x, int y) {
        for (Blocks a: blocks)
            if(a.intersects(new Rectangle(x,y,32,32)))
                return false;

        return true;
    }

    public void render(Graphics graphics) {
        for (Blocks a: blocks) {
            a.render(graphics);
        }
    }
}
