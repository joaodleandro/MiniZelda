package minizelda;

import java.awt.*;

public class UI {

    public void render(Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0,Game.HEIGHT-20, 640, 20);
        graphics.setColor(Color.red);
        graphics.fillRect(0,Game.HEIGHT-20, (int)((Game.player.health/100)*640), 20);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("arial", Font.BOLD, 20));
        graphics.drawString((int)Game.player.health+"/100", Game.WIDTH/2 - 30, Game.HEIGHT);
    }

}
