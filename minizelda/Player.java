package minizelda;

import java.awt.*;

public class Player extends Rectangle {

    public boolean right,left,up,down;
    public int spd = 4;

    public Player(int x, int y) {
        super(x,y,32,32);
    }

    public void tick() {
        if(right && !World.collision(x+spd, y)) {
            x+=spd;
        } else if(left && !World.collision(x-spd, y)) {
            x-=spd;
        } else if(up && !World.collision(x, y-spd)) {
            y-=spd;
        } else if(down && !World.collision(x, y+spd)) {
            y+=spd;
        }
    }

    public void render(Graphics graphics) {
//        graphics.setColor(Color.blue);
//        graphics.fillRect(x,y,width,height);
        graphics.drawImage(Spritesheet.player_front,x,y,32,32,null);

    }
}
