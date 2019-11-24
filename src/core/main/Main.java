package core.main;

import core.util.Constants;
import core.util.KeyInput;
import core.util.MouseInput;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main extends Canvas implements Runnable {

    Handler handler;
    public Graph graph;
    Thread thread;
    KeyInput keyInput;
    MouseInput mouseInput;
    boolean running;
    int showFrames = 0;

    void init() {
        thread = new Thread();
        handler = new Handler();
        graph = new Graph(0, 0, ID.graph, handler);
        keyInput = new KeyInput(handler, this);
        mouseInput = new MouseInput(this);
        handler.addObject(graph);

        this.addKeyListener(keyInput);
        this.addMouseListener(mouseInput);

        new Window(Constants.WIDTH, Constants.HEIGHT, "Complex Numbers and Functions", this);
    }

    public Main() {
        init();
    }

    public void tick() {
        handler.tick();
        mouseInput.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
                frames++;
            }

            if (running) {
                render();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                showFrames = frames;
                frames = 0;
            }
        }
        stop();
    }

    synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop() {
        try {
            thread.join();
            running = false;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Build Successful");
        new Main();
    }
}
