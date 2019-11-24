package core.main;

import core.util.ComplexNumber;
import core.util.Constants;

import java.awt.*;
import java.util.LinkedList;

public class Graph extends GameObject {

    Handler handler;

    public static int xOffset = Constants.WIDTH / 2;
    public static int yOffset = Constants.HEIGHT / 2;

    public LinkedList<ComplexNumber> plottingList;

    ComplexNumber testPoint;

    ComplexNumber origin = new ComplexNumber(0, 0);

    Graphics g;

    public void drawHelpers(Graphics g) {
        g.setColor(new Color(128, 128, 128));
        for (int i = -30; i <= 30; i++) {
            if (i == 0) {
                continue;
            }
            int a = i;
            a *= Constants.scale;
            a += xOffset;
            g.drawLine(a, 0, a, Constants.HEIGHT);
            g.drawString("" + i, a + 10, yOffset + 15);
        }
        for (int i = -30; i <= 30; i++) {
            if (i == 0) {
                continue;
            }
            int a = i;
            a *= Constants.scale;
            a += yOffset;
            g.drawLine(0, a, Constants.WIDTH, a);
            g.drawString("" + -i + "i", xOffset - 20, a - 10);

        }

    }


    public Graph(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        plottingList = new LinkedList<>();

        testPoint = new ComplexNumber(.25f, .333333f);

        addPoint(testPoint);
        //addPoint(origin);

        System.out.println(ComplexNumber.pow(3, new ComplexNumber(3, 4)));

        testPoint = new ComplexNumber(1, 2);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        this.g = g;
        g.setColor(Color.WHITE);
        //args are x1, y1, x2, y2
        g.drawLine(0, yOffset, Constants.WIDTH, yOffset); // x-axis
        g.drawLine(xOffset, 0, xOffset, Constants.HEIGHT); // y-axis
        drawHelpers(g);

        try {
            plotPoints(g, plottingList);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void increaseXOffset(float offset) {
        xOffset += offset;
    }

    public void increaseYOffset(float offset) {
        yOffset += offset;
    }

    public void plotPoints(Graphics g, LinkedList<ComplexNumber> list) {
        for (int i = 0; i < list.size(); i++) {
            ComplexNumber z = list.get(i);
            float _x = z.a;
            float _y = -z.b;

            _x *= Constants.scale;
            _y *= Constants.scale;


            _x += xOffset;
            _y += yOffset;


            g.setColor(Color.WHITE);
            g.fillOval((int) (_x - Constants.size / 2), (int) (_y - Constants.size / 2), (int) Constants.size, (int) Constants.size);
        }
    }

    public void zeta(ComplexNumber z) {
        ComplexNumber sum = ComplexNumber.div(new ComplexNumber(1, 0), ComplexNumber.pow(1, z));
        for (int i = 2; i < Constants.iterations; i++) {
            sum = ComplexNumber.add(sum, ComplexNumber.div(new ComplexNumber(1, 0), ComplexNumber.pow(i, z)));
            addPoint(sum);
        }
    }

    public void addPoint(ComplexNumber z) {
        plottingList.add(z);
    }

    public void addPoint(float x, float y) {
        addPoint(new ComplexNumber(x, y));
    }

    public static ComplexNumber transformMousePoint(int mx, int my) {
        float diffx = (mx - xOffset) / Constants.scale;
        float diffy = (my - yOffset) / Constants.scale;

        return new ComplexNumber(diffx, -diffy);
    }

    public void plotPointFromMouse(int mx, int my) {
        addPoint(transformMousePoint(mx, my));
    }
}
