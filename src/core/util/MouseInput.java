package core.util;

import core.main.Graph;
import core.main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    Main main;
    public int mx, my;

    public boolean mouseDown;

    public MouseInput(Main main) {
        this.main = main;
    }

    public void tick() {
        Point cursor = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(cursor, main);
        mx = cursor.x;
        my = cursor.y;

        if (mouseDown) {
            main.graph.plottingList.clear();
            main.graph.zeta(Graph.transformMousePoint(mx, my));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        main.graph.plottingList.clear();
        main.graph.plotPointFromMouse(mx, my);
        //main.graph.zeta(Graph.transformMousePoint(mx, my));

        mouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

}
