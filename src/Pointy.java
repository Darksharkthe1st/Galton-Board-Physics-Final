//Custom class to represent points as doubles
public class Pointy {
    double x, y;

    public Pointy(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Overrided to swap x and y from doubles to ints here
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pointy other = (Pointy) obj;
        if ((int)(x*10) != (int)(other.x*10))
            return false;
        if ((int)(y*10) != (int)(other.y*10))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pointy [x=" + Math.round(x*10)/10.0 + ", y=" + Math.round(y*10)/10.0 + "]";
    }    
    
}
