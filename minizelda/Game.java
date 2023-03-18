package minizelda;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static int WIDTH = 480, HEIGHT = 480;

    private boolean running = false;
    private Thread thread;

    public Game() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public synchronized void start() {
        if (running) {
            return;
        }

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tick() {

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) { // cannot render
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();

        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        graphics.setColor(Color.red);
        graphics.fillRect(10,10,100,100);

        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            render();

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        stop();
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

        game.start();
    }
}

