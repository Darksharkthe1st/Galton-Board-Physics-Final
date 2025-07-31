package io.galtonsim;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Ball extends Grobject {
    public static final int ballSize = 10;
    public static final int delay = 20;
    final double mass = 10;
    private Vector force;
    private Vector velocity;
    private Vector position;
    private Vector lastPose = new Vector(0, 0);
    private ArrayList<Grobject> allObjects;
    private static ArrayList<Ball> done = new ArrayList<>();
    public static ArrayList<Pillar> pillars;

    //For user adjustment
    public static double xBias = 0;
    public static double gravity = 0.05;

    public Ball(double x, double y, ArrayList<Grobject> allObjects) {
        super(Color.black, Color.red, new Ellipse2D.Double(x - ballSize / 2, y - ballSize / 2, ballSize, ballSize));
        this.force = new Vector(0, gravity); // Constant force of gravity -- other forces build on this
        this.velocity = new Vector(0, 0); // Start off stationary
        this.position = new Vector(x, y);
        this.allObjects = allObjects;
        new Thread() {
            public void run() {
                act();
            }
        }.start();
    }

    /**
     * Applies a force on the object, causing an acceleration
     * 
     * @param v The vector representing the force to be applied
     */
    public void applyForce(Vector v) {
        this.force.add(v);
    }

    /**
     * Moves the ball to the position outlined by the vector
     * 
     * @param v vector representing the position of the new ball
     */
    public void moveTo(Vector v) {
        this.position = v;
        this.internalObj = new Ellipse2D.Double(v.x - ballSize / 2, v.y - ballSize / 2, ballSize, ballSize);
    }

    /**
     * A method designed to make the ball act as it should according to the laws of
     * physics.
     */
    public void act() {
        LinkedList<Vector> queuedForces = new LinkedList<>();
        LinkedList<Double> queuedTiming = new LinkedList<>();
        Vector current = null;
        int collideTime = 0;
        while (position.y < Galter.height - 50) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            lastPose = position;
            if (position.y < Galter.height - 300)
                ;
            this.moveTo(Vector.plus(position, velocity));

            velocity.add(Vector.scale(force, 1 / mass));

            // if (velocity.getMagnitude() >10) {
            // velocity = Vector.withMagnitude(velocity, 10);
            // }
            force = new Vector(0, gravity);
            boolean fullyDone = false;
            // if (this.position.y > 800)
            // {
            //     synchronized(done) {
            //     for (Ball b : done) {
            //         if (Math.abs(Vector.between(b.position, this.position).getMagnitude()) < ballSize) {
            //             // System.out.println(Vector.between(b.position, this.position));
            //             if (!fullyDone) {
            //                 fullyDone = true;
            //             } else {
            //                 this.moveTo(Vector.plus(position,velocity));
            //                 finishMe();
            //                 return;
            //             }
            //         }
            //     }
            // }
            // }

            Vector now = collide();
            if (now != null) {
                current = now;
                current.add(new Vector(xBias, 0));
                collideTime = 3;
            }
            if (current != null) {
                if (collideTime >= 0) {
                    force.add(current);
                    collideTime--;
                } else {
                    current = null;
                }
            }

            while (Galton.paused) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

            // TODO: Add element of randomness here.
        }
        finishMe();
    }

    public void finishMe() {
        for (Pillar p : pillars) {
            if (p.containsSpot(this.position.x)) {
                p.addOne();
                break;
            }
        }
        synchronized(done) {
        done.add(this);
        }
    }

    public Vector collide() {
        if (this.position.y > Galter.height - 200) {
            return Vector.scale(this.force, -1); // Oppose gravity!
        }

        Grobject collideWith = null;
        for (Grobject o : allObjects) {
            if (o.getInternalObj().intersects(this.internalObj.getBounds2D())) {
                if (collideWith == null) {
                    collideWith = o;
                } else { // If we're in collision with multiple objects, just stop for now -- too
                         // complicated to figure out
                    return null;
                }
            }
        }

        if (collideWith != null) {
            return collision(collideWith);
        } else {
            return null;
        }
    }

    public Vector collision(Grobject brog) {

        if (brog instanceof Peg) {
            return Vector.withMagnitude(Vector.between(((Peg) brog).pose, this.lastPose), Math.random() * 0.05 + 0.2);
        }

        if (brog instanceof Pillar) {
            return null;
        }

        Rectangle2D bounds = brog.internalObj.getBounds2D();
        PathIterator p = new Ellipse2D.Double(position.x - ballSize, position.y - ballSize, ballSize * 2.0,
                ballSize * 2.0).getPathIterator(null);
        while (!p.isDone()) {
            double[] coords = new double[6];
            p.currentSegment(coords);
            if (bounds.contains(coords[0], coords[1])) {
                Vector direction = Vector.between(new Vector(coords[0], coords[1]), lastPose);
                return Vector.withMagnitude(direction, -0.5);
            }
            p.next();
        }
        return null;
    }

    public static int doneSize() {
        return done.size();
    }
}
