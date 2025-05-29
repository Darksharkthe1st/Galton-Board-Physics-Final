import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Galton extends JPanel {
    ArrayList<Grobject> immovableObjs = new ArrayList<>();
    ArrayList<Ball> balls = new ArrayList<>();

    /* Variables for tweaking */
    public static boolean paused = true;
    public static double variation = 50;
    public static double startingX = Galter.width/2-20;
    public static int totalBalls = 40;

    public void begin() {
        // System.out.println(startX);
        int count = 20;
        int pillarWidth = 20;
        Pillar.increment = (Galter.realWidth) / count;
        Ball.pillars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            // immovableObjs.add(new Grobject());
            Ball.pillars.add(new Pillar(((Galter.realWidth) / count) * i + ((Galter.realWidth) / count) / 2));
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
        
        for (int i = 0; i < totalBalls; i++) {
            while (paused) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(Galter.delay/2);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            synchronized(balls) {
            balls.add(new Ball(Math.random() * variation - variation/2 + startingX,100, immovableObjs));
            // System.out.println(startX);
            }
        }
        // while (Ball.doneSize() < balls.size() - 10) {
        //     try {
        //         Thread.sleep(200);
        //     } catch (InterruptedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }
    }

    public void paintComponent(Graphics g) {
        Grobject.drawMany(g, immovableObjs);
        synchronized(balls) {
        Grobject.drawManyBalls(g, balls);
        }        
    }
}
