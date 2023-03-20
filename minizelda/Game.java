package minizelda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

    public static int WIDTH = 640, HEIGHT = 480;
    public static int SCALE = 3;

    public static Player player;
    public static World world;
    public static List<Enemy> enemies = new ArrayList<>();
    public UI ui;
    public static String gameState = "NORMAL";
    private boolean showMessageGameOver = true;
    private int gameOverFrames = 0;
    private boolean restartGame = false;

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        new Spritesheet();
        world = new World();
        player = new Player(50,50);
        ui = new UI();
        enemies.clear(); // static list doesn't reset on game over
        enemies.add(new Enemy(340,150));
        enemies.add(new Enemy(50, 70));
        enemies.add(new Enemy(480,300));
        enemies.add(new Enemy(200, 50));
        enemies.add(new Enemy(100,100));
        enemies.add(new Enemy(140, 140));
        enemies.add(new Enemy(370,50));
//        enemies.add(new Enemy(90, 100));
    }

    private void tick() {

        if(gameState.equals("NORMAL")) {

            player.tick();

            for (int i = 0;  i < enemies.size(); i++) {
                enemies.get(i).tick();
            }
        }
        else if(gameState.equals("GAME_OVER")) {
//            System.out.println("Game over");
            gameOverFrames++;
            if(gameOverFrames==30) {
                gameOverFrames = 0;
                showMessageGameOver = !showMessageGameOver; // game over blinking message
            }

            if(restartGame){
                restartGame = false;
                gameState = "NORMAL";
                new Game();
            }

        }

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null) { // cannot render
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();

        graphics.setColor(Color.green);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        world.render(graphics);
        for (int i = 0;  i < enemies.size(); i++) {
            enemies.get(i).render(graphics);
        }
        player.render(graphics);
        ui.render(graphics);
        if(gameState.equals("GAME_OVER")) {
            Graphics2D graphics2 = (Graphics2D) graphics;
            graphics2.setColor(new Color(0,0,0,100));
            graphics2.fillRect(0, 0,WIDTH, HEIGHT);
                graphics.setFont(new Font("arial", Font.BOLD, 28));
                graphics.setColor(Color.WHITE);
                graphics.drawString("GAME OVER", WIDTH / 2 - 80, HEIGHT / 2);
            if(showMessageGameOver) {
                graphics.drawString(">PRESS ENTER FOR RESTART<", WIDTH / 2 - 215, HEIGHT / 2 + 40);
            }
        }
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

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_Z) {
            player.shoot = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            restartGame = true;
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

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }
}

