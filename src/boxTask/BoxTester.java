package boxTask;

public class BoxTester {

    public static void main(String[] args) {

        // Box with default constructor
        Box b1 = new Box();
        // Box with parameters
        Box b2 = new Box(3,6,2);

        System.out.println("Default Box parameters: \nWidth= " + b1.getWidth() + ", Height= " + b1.getHeight() + ", Depth= " + b1.getDepth());
        System.out.println("Surface Area: " + b1.getSurfaceArea());
        System.out.println("Volume: " + b1.getVolume());

        System.out.println();

        System.out.println("My Box parameters: \nWidth= " + b2.getWidth() + ", Height= " + b2.getHeight() + ", Depth= " + b2.getDepth());
        System.out.println("Surface Area: " + b2.getSurfaceArea());
        System.out.println("Volume: " + b2.getVolume());
    }
}
