package io.galtonsim;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Peg extends Grobject {
    public static final int size = 20;
    public final Vector pose;
    public Peg(double x, double y) {
        super(Color.black, Color.black, new Ellipse2D.Double(x - size/2, y - size/2, size, size));
        this.pose = new Vector(x, y);
    }
    
}
