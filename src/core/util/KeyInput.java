package core.util;

import core.main.Graph;
import core.main.Handler;
import core.main.Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Main main;
    private Handler handler;
    private Graph graph;


    public KeyInput(Handler handler, Main main) {
        this.handler = handler;
        this.main = main;
        this.graph = main.graph;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_EQUALS) {
            Constants.scale += 10;
        }

        if (key == KeyEvent.VK_MINUS) {
            Constants.scale -= 10;
        }

        if (key == KeyEvent.VK_H) {
            Graph.xOffset = Constants.WIDTH / 2;
            Graph.yOffset = Constants.HEIGHT / 2;
            Constants.scale = 100;
        }

        if (key == KeyEvent.VK_W) {
            graph.increaseYOffset(Constants.offset);
        }
        if (key == KeyEvent.VK_S) {
            graph.increaseYOffset(-Constants.offset);
        }
        if (key == KeyEvent.VK_A) {
            graph.increaseXOffset(Constants.offset);
        }
        if (key == KeyEvent.VK_D) {
            graph.increaseXOffset(-Constants.offset);
        }
    }

    public void keyReleased(KeyEvent e) {
    }

}
