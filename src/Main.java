import java.util.*;
//Variables - private - encapsulation
//Constructors - public - can create objects from outside the class
//Getters and Setters - public - outside code can read or update in a controlled way
//Math Methods - public - define behavior of the class outside
//Helper Functions - private - internal     implemented details

public class Main {
    //Main doesn't contain any attributes
    public static void main(String[] args) {

        Matrix M = new Matrix(3, 6);
        System.out.println("Matrix M:");
        M.print();

        Matrix MT = M.transpose();
        System.out.println("Transpose of M:");
        MT.print();

        Matrix product = M.multiply(MT);
        System.out.println("M * Mᵀ:");
        if (product != null) {
            product.print();
        } else {
            System.out.println("Cannot multiply");
        }

        System.out.println("Spiral of M:");
        int[] spiral = M.spiral();
        for (int v : spiral) {
            System.out.print(v + " ");
        }
        System.out.println();
        System.out.println();


        Circle c = new Circle(4.0);
        System.out.println("CIRCLE");
        System.out.println("Radius = " + c.getRadius());
        System.out.println("Circumference = " + c.Circumference());
        System.out.println("Area = " + c.area());
        System.out.println();


        Sphere s = new Sphere(4.0);
        System.out.println("SPHERE");
        System.out.println("Radius = " + s.getRadius());
        System.out.println("Surface area = " + s.surfaceArea());
        System.out.println("Volume = " + s.volume());
        System.out.println();
    }
}

class Matrix {

    //Attributes
    private int rows;
    private int columns;
    private int[][] data;
    //

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        data = new int[rows][columns];
        fillRandom();
    }

    //O - big-O notation

    //Complexity: O(r * c)
    private void fillRandom() {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                data[i][j] = (int) (Math.random() * 100) + 1;
    }

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }

    public void setData(int[][] newData) {
        if (newData == null || newData.length == 0) return;
        data = newData;
        rows = newData.length;
        columns = newData[0].length;
    }

    //Complexity O(r * c)
    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Complexity O(r * c)
    public Matrix transpose() {
        Matrix t = new Matrix(columns, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                t.data[j][i] = this.data[i][j];
        return t;
    }

    //Complexity: O(r * c * other.c)
    public Matrix multiply(Matrix other) {
        if (this.columns != other.rows)
            return null;

        Matrix res = new Matrix(this.rows, other.columns);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                int sum = 0;
                for (int k = 0; k < this.columns; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                res.data[i][j] = sum;
            }
        }
        return res;
    }

    // Complexity: O(r * c)
    public int[] spiral() {
        int[] out = new int[rows * columns];
        int idx = 0;

        int top = 0, bottom = rows - 1;
        int left = 0, right = columns - 1;

        while (top <= bottom && left <= right) {

            for (int j = left; j <= right; j++)
                out[idx++] = data[top][j];
            top++;

            for (int i = top; i <= bottom; i++)
                out[idx++] = data[i][right];
            right--;

            if (top <= bottom) {
                for (int j = right; j >= left; j--)
                    out[idx++] = data[bottom][j];
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    out[idx++] = data[i][left];
                left++;
            }
        }
        return out;
    }
}

class Circle {

    //Attributes
    private double radius;
    //

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius= radius;
    }

    public double Circumference() {
        return 2 * Math.PI * radius;
    }

    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }
}

class Sphere {

    //Attributes
    private double radius;
    //

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double surfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    public double volume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }
}






