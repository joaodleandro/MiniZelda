package minizelda;

import java.awt.*;

public class Bullet extends Rectangle {

    public int dir;
    public int speed = 8;
    public int frames = 0;
    public int damage = 5;

    public Bullet(int x, int y, int dir) {
        super(x+16,y+16,10,10);
        this.dir = dir;

    }

    public void collision() {
        Rectangle actual = new Rectangle(x,y,10,10);

        for (Enemy a: Game.enemies) {
            Rectangle targetEnemy = new Rectangle(a.x,a.y,32,32);
            if(actual.intersects(targetEnemy)) {
                a.health-=damage;
                Player.bullets.remove(this);
                return;
            }
        }
    }

    public void tick() {
        x+=speed*dir;
        frames++;
        if(frames == 60) {
            Player.bullets.remove(this);
        }

        collision();
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillOval(x,y,width,height);

    }

}
