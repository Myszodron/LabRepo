//Variables - private - encapsulation
//Constructors - public - can create objects from outside the class
//Getters and Setters - public - outside code can read or update in a controlled way
//Math Methods - public - define behavior of the class outside
//Helper Functions - private - internal     implemented details

public class Main {
    //Main doesn't contain any attributes
    public static void main(String[] args) {
    }
}

class Matrix {

    //Attributes
    private int rows;
    private int columns;
    private int[][] data;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        data = new int[rows][columns];
        fillRandom();
    }

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

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Matrix transpose() {
        Matrix t = new Matrix(columns, rows);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                t.data[j][i] = this.data[i][j];
        return t;
    }

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






