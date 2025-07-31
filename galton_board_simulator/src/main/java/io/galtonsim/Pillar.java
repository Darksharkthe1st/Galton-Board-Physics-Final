package io.galtonsim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Pillar extends Grobject {
    private static final int pillarHeight = 400;
    private static final int pillarWidth = 5;
    private final double xPos;
    public static int increment = 0;
    private int runningCount = 0;
    public static double scale = 1000;
    public Pillar(double x) {
        super(Color.black, Color.black, new Rectangle2D.Double(x - pillarWidth/2, Galter.height - pillarHeight, pillarWidth, pillarHeight));
        this.xPos = x;
    }

    @Override
    public void drawMe(Graphics g) {
        // TODO Auto-generated method stub
        super.drawMe(g);
        g.setColor(new Color(0,0,255,150));
        g.fillRect((int)xPos, (int)(Galter.height - 50 - runningCount * scale), (int)increment, (int)(runningCount * scale));
        // System.out.println(xPos + ": " + runningCount);
    }



    public void addOne() {
        runningCount++;
        while (runningCount * scale > pillarHeight - 50) {
            scale -= 0.01;
        }
    }

    public boolean containsSpot(double x) {
        return (x >= xPos && x <= xPos + increment);
    }
    
}
