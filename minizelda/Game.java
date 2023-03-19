package minizelda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 640, HEIGHT = 480;

    public static int SCALE = 3;

    public Player player;
    public World world;

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        new Spritesheet();
        player = new Player(50,50);
        world = new World();
    }

    private void tick() {
        player.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) { // cannot render
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();

        graphics.setColor(Color.green);
        graphics.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);

        player.render(graphics);
        world.render(graphics);
        bs.show();
    }

    @Override
    public void run() {

        while (true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        // Window functions
        JFrame jframe = new JFrame();

        jframe.add(game);
        jframe.setTitle("Mini ZELDA");
        jframe.pack();
        jframe.setLocationRelativeTo(null); // center window
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // shutdown if closed
        jframe.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }
}

