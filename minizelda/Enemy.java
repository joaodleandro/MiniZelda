package minizelda;

import java.awt.*;
import java.util.Random;

public class Enemy extends Rectangle {
    public int spd = 1;
    public int idleAnim = 0;
    public int idleFrames = 0; int targetFrame = 15;

    public int health = 10;

    public Enemy(int x, int y) {
        super(x,y,32,32);
    }

    public boolean collisionWithPlayer() {

        Rectangle actual = new Rectangle(x, y,32,32);
        Rectangle player = new Rectangle(Game.player.x, Game.player.y, 32, 32);

        return actual.intersects(player);
    }

    public boolean collisionWithOtherEnemies(int xspd, int yspd) {
        Rectangle actual = new Rectangle(x+xspd, y+yspd,32,32);

        for (Enemy a: Game.enemies) {
            if(a==this) continue;

            Rectangle targetEnemy = new Rectangle(a.x,a.y,32,32);
            if(actual.intersects(targetEnemy)) return false;
        }
        return true;
    }

    public void followPlayer() {
        Player player = Game.player;
        if(!this.collisionWithPlayer()) {
            if (x < player.x && World.collision(x + spd, y) && collisionWithOtherEnemies(spd, 0)) {
                if (new Random().nextInt(100) < 50) {
                    x += spd;
                }
            } else if (x > player.x && World.collision(x - spd, y) && collisionWithOtherEnemies(-spd, 0)) {
                if (new Random().nextInt(100) < 50) {
                    x -= spd;
                }
            }

            if (y < player.y && World.collision(x, y + spd) && collisionWithOtherEnemies(0, spd)) {
                if (new Random().nextInt(100) < 50)
                    y += spd;
            } else if (y > player.y && World.collision(x, y - spd) && collisionWithOtherEnemies(0, -spd)) {
                if (new Random().nextInt(100) < 50)
                    y -= spd;
            }
        } else { // intersecting with player
            if(new Random().nextInt(100) < 10) {
                player.health--;
                Player.damaged = true;
                System.out.println("HP: " + player.health);
            }
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

        if(health <= 0) {
            Game.enemies.remove(this);
        }

    }

    public void render(Graphics graphics) {
            graphics.setColor(Color.BLACK);
            graphics.drawRect(x,y,32,32);
            graphics.drawImage(Spritesheet.slime_walk[idleAnim],x,y,32,32,null);

    }
}
