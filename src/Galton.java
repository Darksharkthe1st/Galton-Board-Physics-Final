import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Galton extends JPanel {
    ArrayList<Grobject> immovableObjs = new ArrayList<>();
    ArrayList<Ball> balls = new ArrayList<>();
    public void begin() {
        int pillarHeight = 400;
        int count = 20;
        int pillarWidth = 20;
        Pillar.increment = (Galter.realWidth) / count;
        Ball.pillars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            // immovableObjs.add(new Grobject());
            Ball.pillars.add(new Pillar(((Galter.realWidth) / count) * i + ((Galter.realWidth) / count) / 2 - pillarWidth / 2));
            immovableObjs.add(Ball.pillars.get(Ball.pillars.size()-1)); 
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
                immovableObjs.add(new Peg(startX-size/2 + incrementX/2 + c * incrementX, startY-size/2));
            }
            startY += incrementY;
            for (int c = 0; c < count; c++) {
                immovableObjs.add(new Peg(startX-size/2 + c * incrementX, startY-size/2));
            }
            startY += incrementY;
        }

        immovableObjs.add(new Grobject(new Rectangle2D.Double(-10,0,10,Galter.height)));
        immovableObjs.add(new Grobject(new Rectangle2D.Double(Galter.width,0,10,Galter.height)));
        immovableObjs.add(new Grobject(new Rectangle2D.Double(0,Galter.height,Galter.width, 100)));
        
        int variation = 30;
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            synchronized(balls) {
            balls.add(new Ball(Math.random() * variation - variation/2 + Galter.width/2-20,100, immovableObjs));
            }
        }
        
    }

    public void paintComponent(Graphics g) {
        Grobject.drawMany(g, immovableObjs);
        synchronized(balls) {
        Grobject.drawManyBalls(g, balls);
        }        
    }
}
