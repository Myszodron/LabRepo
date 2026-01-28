package lab8;

public class Circle {

    //Attributes
    private double radius;
    //

    public Circle(double radius) {
        setRadius(radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("Radius has to be positive");
        this.radius = radius;
    }

    public double circumference() {
        return 2 * Math.PI * radius;
    }

    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    public String inf() {
        return
                "lab8.Circle metrics: \n" +
                "Radius: " + radius +
                "\nCircumference: " + circumference() +
                "\nArea: " + area();
    }

}
