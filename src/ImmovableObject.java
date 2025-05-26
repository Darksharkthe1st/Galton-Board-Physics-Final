import java.awt.Color;
import java.awt.Polygon;

public class ImmovableObject extends Polygon {
    private Color fillColor;
    private Color borderColor;
    public ImmovableObject(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }

    /** Create an immovable object from an array of Pointy's
     * @param points Array of Pointy variables representing the positions in the polygon
     */
    public ImmovableObject(Pointy[] points) {
        this.xpoints = new int[points.length];
        this.ypoints = new int[points.length];
        this.npoints = points.length;
        for (int i = 0; i < points.length; i++) {
            xpoints[i] = (int)points[i].x;
            ypoints[i] = (int)points[i].y;
        }
    }

    //TODO: Implement
    /*public collidedWith(Polygon other) {

    }*/
}
