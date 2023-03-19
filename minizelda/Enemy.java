package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle {
    public int spd = 1;

    public int idleAnim = 0;

    public int idleFrames = 0; int targetFrame = 15;

    public Enemy(int x, int y) {
        super(x,y,32,32);
    }

    public void followPlayer() {
        Player player = Game.player;
        if(x < player.x && World.collision(x + spd, y)) {
            if (new Random().nextInt(100) < 50)
                x += spd;
        }
        else if(x > player.x && World.collision(x - spd, y)) {
            if (new Random().nextInt(100) < 50)
                x -= spd;
        }

        if(y < player.y && World.collision(x, y + spd)) {
            if (new Random().nextInt(100) < 50)
                y += spd;
        }
        else if(y > player.y && World.collision(x, y - spd)) {
            if (new Random().nextInt(100) < 50)
                y -= spd;
        }
    }

    public void tick() {
        followPlayer();

        idleFrames++;
        if (idleFrames == targetFrame) {
            idleFrames = 0;
            idleAnim++;
            if (idleAnim == Spritesheet.slime_walk.length) idleAnim = 0;

        }

    }

    public void render(Graphics graphics) {
//        graphics.setColor(Color.blue);
//        graphics.fillRect(x,y,width,height);
        graphics.drawImage(Spritesheet.slime_walk[idleAnim],x,y,32,32,null);

    }
}
