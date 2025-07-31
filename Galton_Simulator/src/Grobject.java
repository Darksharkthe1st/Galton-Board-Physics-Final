package src;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.util.ArrayList;;

public class Grobject {
    protected Color fillColor;
    protected Color borderColor;
    public Shape internalObj;
    public Grobject(int[] xpoints, int[] ypoints, int npoints, Color borderColor, Color fillColor) {
        this(fillColor, borderColor, new Polygon(xpoints, ypoints, npoints));
    }
    
    /** Constructor that assumes the color black by default
     * @param internalObj //Internal object 
     */
    public Grobject(Shape internalObj) {
        this(Color.black, Color.black, internalObj);
    }

    /** Default all-param constructor
     * @param fillColor Color of internal shape fill
     * @param borderColor Color of borders of the object
     * @param internalObj //Internal object 
     */
    public Grobject(Color fillColor, Color borderColor, Shape internalObj) {
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.internalObj = internalObj;
    }

    /** Create an immovable object from an array of Pointy's, assuming all colors are black
     * @param points Array of Pointy variables representing the positions in the polygon
     */
    public Grobject(Vector[] points) {
        this(Color.black, Color.black, points);
    }

    /** Create an immovable object from an array of Pointy's
     * @param points Array of Pointy variables representing the positions in the polygon
     */
    public Grobject(Color fillColor, Color borderColor, Vector[] points) {
        
        int[] xpoints = new int[points.length];
        int[] ypoints = new int[points.length];
        int npoints = points.length;
        for (int i = 0; i < points.length; i++) {
            xpoints[i] = (int)points[i].x;
            ypoints[i] = (int)points[i].y;
        }

        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.internalObj = new Polygon(xpoints, ypoints, npoints); 
    }

    public void drawMe(Graphics g) {
        Graphics2D gra = (Graphics2D)g;
        gra.setColor(fillColor);
        gra.setPaint(borderColor);
        gra.fill(internalObj);
    }

    /** Draws many objects outlined
     * @param g The graphics interface to draw upon
     * @param objects The objects to be drawn
     */
    public static void drawMany(Graphics g, ArrayList<Grobject> objects) {
        Graphics2D gra = (Graphics2D)g;
        for (Grobject i : objects) {
            i.drawMe(gra);
        }
    }

    /** Draws many objects outlined
     * @param g The graphics interface to draw upon
     * @param objects The objects to be drawn
     */
    public static void drawManyBalls(Graphics g, ArrayList<Ball> objects) {
        Graphics2D gra = (Graphics2D)g;
        for (Grobject i : objects) {
            gra.setColor(i.fillColor);
            gra.setPaint(i.borderColor);
            gra.fill(i.internalObj);
        }
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Shape getInternalObj() {
        return internalObj;
    }

    public void setInternalObj(Shape internalObj) {
        this.internalObj = internalObj;
    }

    public double getX() {
        return this.internalObj.getBounds2D().getX();
    }

    public double getY() {
        return this.internalObj.getBounds2D().getY();
    }

    //TODO: Implement
    /*public collidedWith(Polygon other) {

    }*/
}
