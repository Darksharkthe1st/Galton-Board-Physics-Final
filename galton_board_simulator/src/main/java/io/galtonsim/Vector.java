package io.galtonsim;

//Custom class to represent points as doubles
public class Vector {
    double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** Creates a vector
     * @param magnitude Magnitude of the vector in pixels
     * @param direction Direction of the vector in radians, assuming +x = 0 degrees
     */
    public Vector(float magnitude, float angle) {
        this.x = magnitude * Math.cos(angle); 
        this.y = magnitude * Math.sin(angle);
    }

    //Overrided to swap x and y from doubles to ints here
    //This is to ensure accuracy to the tenths place
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        int temp;
        temp = (int)(x*10);
        result = prime * result + temp * (int)(Math.pow(2,temp));
        temp = (int)(y*10);
        result = prime * result + temp * (int)(Math.pow(2,temp));
        return result;
    }

    //Overrided to swap x and y from doubles to ints here
    //This is to ensure accuracy to the tenths place
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vector other = (Vector) obj;
        if ((int)(x*10) != (int)(other.x*10))
            return false;
        if ((int)(y*10) != (int)(other.y*10))
            return false;
        return true;
    }

    public void add(Vector o) {
        this.add(o.x, o.y);
    }

    public void add(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public static Vector plus(Vector a, Vector b) {
        return new Vector(a.x + b.x, a.y + b.y);
    }

    public static Vector scale(Vector a, double scale) {
        return new Vector(a.x * scale, a.y * scale);
    }

    /** Draws the vector connecting the two vectors if they were points
     * @param a The point starting from
     * @param b The point going to
     * @return The resulting vector for travel
     */
    public static Vector between(Vector a, Vector b) {
        return new Vector(b.x - a.x, b.y - a.y);
    }

    public double getMagnitude() {
        return (Math.sqrt(x*x + y*y));
    }

    public double getDirection() {
        return Math.tanh(y/x);
    }


    /** Returns the vector with magnitude v
     * @param v vector facing in the direction of the resulting vector
     * @param magnitude the magnitude of the resulting vector
     * @return a vector in the direction of v with magnitude {magnitude}
     */
    public static Vector withMagnitude(Vector v, double magnitude) {
        return Vector.scale(v, magnitude / v.getMagnitude());
    }

    public static double dotProduct(Vector a, Vector b) {
        return a.x * b.x + a.y * b.y;
    }

    @Override
    public String toString() {
        return "Pointy [x=" + Math.round(x*10)/10.0 + ", y=" + Math.round(y*10)/10.0 + "]";
    }    
    
}
