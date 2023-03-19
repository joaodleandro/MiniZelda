package minizelda;

import java.awt.*;

public class Player extends Rectangle {

    public boolean right,left,up,down;
    public int spd = 4;

    public int idleAnim = 0;

    public int idleFrames = 0; int targetFrame = 15;

    public Player(int x, int y) {
        super(x,y,32,32);
    }

    public void tick() {
        boolean moved = false;
        if(right && World.collision(x + spd, y)) {
            x+=spd;
            moved = true;
        } else if(left && World.collision(x - spd, y)) {
            x-=spd;
            moved = true;
        } else if(up && World.collision(x, y - spd)) {
            y-=spd;
            moved = true;
        } else if(down && World.collision(x, y + spd)) {
            y+=spd;
            moved = true;
        }

        if(moved) {
            idleFrames++;
            if (idleFrames == targetFrame) {
                idleFrames = 0;
                idleAnim++;
                if (idleAnim == Spritesheet.player_front.length) idleAnim = 0;
            }
        }
    }

    public void render(Graphics graphics) {
//        graphics.setColor(Color.blue);
//        graphics.fillRect(x,y,width,height);
        graphics.drawImage(Spritesheet.player_front[idleAnim],x,y,32,32,null);

    }
}
