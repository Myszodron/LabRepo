import java.util.*;

public class Sphere {

    //Attributes
    private double radius;
    //

    public Sphere(double radius) {
       setRadius(radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive");
        }
        this.radius = radius;
    }

    public double surfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    public double volume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    public String inf() {
        return "Sphere:\n" +
                "Radius: " + radius +
                "\nSurface area: " +surfaceArea() +
                "\nVolume: " + volume();
    }
}

