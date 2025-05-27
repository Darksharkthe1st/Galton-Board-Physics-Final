import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.security.InvalidParameterException;

public class Ball extends Grobject {
    public static final int ballSize = 10;
    private Ellipse2D internal;
    

    public Ball(int x, int y) {
        super(Color.black, Color.red, new Ellipse2D.Double(x - ballSize/2, y - ballSize/2, ballSize, ballSize));
        this.internal = (Ellipse2D)this.getInternalObj();
    }
    @Override
    public Ellipse2D getInternalObj() {
        return internal;
    }
    @Override
    public void setInternalObj(Shape internalObj) {
        if (!(internalObj instanceof Ellipse2D)) {
            throw new InvalidParameterException("Can't make a ball out of anything other than an Ellipse2D");
        }
        super.setInternalObj(internalObj);
        this.internal = (Ellipse2D)internalObj;
    }
}
