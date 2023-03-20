package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {

    public boolean right,left,up,down;
    public int spd = 4;
    public int idleAnim = 0;
    public int idleFrames = 0; int targetFrame = 15; int damageFrames = 0;
    public static List<Bullet> bullets = new ArrayList<>();
    public int dir = 1;
    public boolean shoot = false;
    public double health = 100;
    public static boolean damaged = false;

    public Player(int x, int y) {
        super(x,y,32,32);
    }

    public void tick() {
        boolean moved = false;
        if(right && World.collision(x + spd, y)) {
            x+=spd;
            moved = true;
            dir=1;
        } else if(left && World.collision(x - spd, y)) {
            x-=spd;
            moved = true;
            dir=-1;
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

        if(damaged) {
            damageFrames++;
            if(damageFrames==15){
                damageFrames = 0;
                damaged = false;
            }
        }

        if(health<=0) Game.gameState = "GAME_OVER";

        if(shoot){
            shoot = false;
            bullets.add(new Bullet(x,y,dir));
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).tick();
        }

    }

    public void render(Graphics graphics) {
//        graphics.setColor(Color.blue);
//        graphics.fillRect(x,y,width,height);
        if(!damaged)
            graphics.drawImage(Spritesheet.player_front[idleAnim],x,y,32,32,null);
        else
            graphics.drawImage(Spritesheet.player_dmg,x,y,32,32,null);

        for (Bullet a: bullets) {
            a.render(graphics);
        }

    }
}
