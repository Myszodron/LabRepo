package boxTask;

public class Box {

    protected double width;
    protected double height;
    protected double depth;

    // Default constructor
    public Box() {
        width = 1.0;
        height = 1.0;
        depth = 1.0;
    }

    // Three-parameter constructor
    public Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    // Calculate surface area
    public double getSurfaceArea() {
        return 2 * (width * height + height * depth + width * depth);
    }

    // Calculate volume
    public double getVolume(){
        return width * height * depth;
    }

    // Getters
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDepth() {
        return depth;
    }
}
