class Matrix {

    //Attributes
    private int nrows;
    private int ncolumns;
    private int[][] data;
    //

    public Matrix(int nrows, int ncolumns) {
        if (nrows <= 0 || ncolumns <= 0)
            throw new IllegalArgumentException("Rows and columns have to be positive");

        this.nrows = nrows;
        this.ncolumns = ncolumns;
        data = new int[nrows][ncolumns];
    }

    public int getRows() {
        return nrows;
    }

    public int getColumns() {
        return ncolumns;
    }

    public void set(int r, int c, double value) {
        data[r][c] = (int) value;
    }

    public double get(int r, int c) {
        return data[r][c];
    }

    //O - big-O notation

    //Complexity: O(r * c)
    public void fillRandom() {
        for (int i = 0; i < nrows; i++)
            for (int j = 0; j < ncolumns; j++)
                data[i][j] = (int) (Math.random() * 100) + 1;
    }

    //Complexity O(r * c)
    public String print() {
        String s = " ";

        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncolumns; j++) {
                s += data[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    //Complexity O(r * c)
    public Matrix transpose() {
        Matrix t = new Matrix(ncolumns, nrows);
        for (int i = 0; i < nrows; i++)
            for (int j = 0; j < ncolumns; j++) {
                t.set(j, i, data[i][j]);
            }
        return t;
    }

    //Complexity: O(r * c * other.c)
    public Matrix multiply(Matrix other) {
        if (this.ncolumns != other.nrows)
            return null;

        Matrix res = new Matrix(this.nrows, other.ncolumns);

        for (int i = 0; i < this.nrows; i++) {
            for (int j = 0; j < other.ncolumns; j++) {
                int sum = 0;
                for (int k = 0; k < this.ncolumns; k++) {
                    sum += this.data[i][k] * other.data[k][j];
                }
                res.data[i][j] = sum;
            }
        }
        return res;
    }

    // Complexity: O(r * c)
    public String spiral() {
        String sp = "";

        int top = 0, bottom = nrows - 1;
        int left = 0, right = ncolumns - 1;

        while (top <= bottom && left <= right) {

            for (int j = left; j <= right; j++)
                sp += data[top][j];
            top++;

            for (int i = top; i <= bottom; i++)
                sp += data[i][right];
            right--;

            if (top <= bottom) {
                for (int j = right; j >= left; j--)
                    sp += data[bottom][j];
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    sp  += data[i][left];
                left++;
            }
        }

        return sp;
    }
}



