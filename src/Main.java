//Variables - private - encapsulation
//Constructors - public - can create objects from outside the class
//Getters and Setters - public - outside code can read or update in a controlled way
//Math Methods - public - define behavior of the class outside
//Helper Functions - private - internal     implemented details

public class Main {
    //Main doesn't contain any attributes
    public static void main(String[] args) {

        Circle c = new Circle(4.0);
        System.out.println(c.inf());
        System.out.println();

        Sphere s = new Sphere(8.0);
        System.out.println(s.inf());
        System.out.println();

        Matrix m = new Matrix(3,6);
        Matrix n = new Matrix(6,4);
        m.fillRandom();
        n.fillRandom();

        System.out.print("Matrix M:\n");
        System.out.print(m.print());
        System.out.print("Matrix N:\n");
        System.out.print(n.print());

        System.out.print("Spiral M:\n");
        System.out.print(m.spiral());
        System.out.print("\nTranspose M:\n");
        System.out.print(m.transpose().print());
        System.out.print("\nMultiplication M x N: \n");
        System.out.print(m.multiply(n).print());





    }
}