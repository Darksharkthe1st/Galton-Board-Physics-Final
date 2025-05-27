import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Galton extends JPanel {
    ArrayList<Grobject> objects = new ArrayList<>();
    public void begin() {
        int pillarHeight = 400;
        int count = 10;
        int pillarWidth = 20;
        for (int i = 0; i < count; i++) {
            objects.add(new Grobject(new Rectangle2D.Double(((Galter.realWidth) / count) * i + ((Galter.realWidth) / count) / 2 - pillarWidth / 2, Galter.height - pillarHeight, pillarWidth, pillarHeight)));    
        }
        
        count = 10;
        double startX = (0.05 * Galter.realWidth);
        double startY = (0.2 * Galter.height);
        double incrementX = ((Galter.realWidth - startX * 2)/(count-1));
        System.out.println(startX + ", " + incrementX + ", " + (incrementX * count));
        double incrementY = incrementX / 3;

        int size = 20;
        for (int row = 0; row < 6; row++) {
            for (int c = 0; c < count - 1; c++) {
                objects.add(new Grobject(new Ellipse2D.Double(startX-size/2 + incrementX/2 + c * incrementX, startY-size/2, size, size)));
            }
            startY += incrementY;
            for (int c = 0; c < count; c++) {
                objects.add(new Grobject(new Ellipse2D.Double(startX-size/2 + c * incrementX, startY-size/2, size, size)));
            }
            startY += incrementY;
        }

        objects.add(new Ball(100,100));
        
    }

    public void paintComponent(Graphics g) {
        Grobject.drawMany(g, objects);
    }
}
