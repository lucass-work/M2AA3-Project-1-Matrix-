/**
 * Static Matrix util
 */

public class Util {

    /**
     * Returns an width * height identity Matrix
     * @param width
     * @param height
     * @return
     */
    public static Matrix identity(int width,int height){
        if(width <= 0 || height <= 0){
            return new Matrix(0,0);
        }

        double[][] mat = new double[width][height];

        for(int i = 0 ;i < width;i++){
            for(int j = 0; j < height; j++){
                mat[i][j] = i==j ? 1 : 0;
            }
        }
        return new Matrix(mat);
    }

    /**
     * returns an nxn identity Matrix
     * @param n
     * @return
     */
    public static Matrix identity(int n){
        return identity(n,n);
    }

    /**
     * returns a width * height zero Matrix
     * @param width
     * @param height
     * @return
     */
    public static Matrix zero(int width,int height){
        if(width <= 0 || height <= 0){
            return new Matrix(0,0);
        }

        double[][] mat = new double[width][height];

        for(int i = 0 ; i < width;i ++){
            for(int j = 0 ;j < height;j++){
                mat[i][j] = 0;
            }
        }

        return new Matrix(mat);
    }

    /**
     * Performs Matrix multiplication A * B. Original Matrices are not modified.
     * @param A
     * @param B
     * @return
     */
    public static Matrix mult(Matrix A,Matrix B){
        if(A.width != B.height){
            System.err.println("Inner dimensions of matricies do not match, cannot multiply");
            return null;
        }

        double[][] mat = zero(B.width,A.height).data;

        for(int row_a = 0 ; row_a < A.height; row_a++){
            for(int col_b = 0 ; col_b < B.width; col_b++){
                for(int col_a = 0 ; col_a < A.width; col_a++){
                    mat[col_b][row_a] += A.data[col_a][row_a] * B.data[col_b][col_a];
                }
            }
        }

        return new Matrix(mat);

    }

    /**
     * performs scalar Matrix multiplication lambda * A. Original Matrix not modified.
     * @param lambda
     * @param A
     * @return
     */
    public static Matrix mult(double lambda, Matrix A){
        double[][] mat = A.get_data();

        for(int i = 0;i < mat.length;i++){
            for(int j = 0 ;j < mat[0].length;j++){
                mat[i][j] *= lambda;
            }
        }

        return new Matrix(mat);

    }
    public static double[] mult(double lambda, double[] A){
        double[] out = A.clone();
        for(int  i = 0; i < A.length;i++){
            out[i] *= lambda;
        }
        return out;
    }

    /**
     * performs componentwise addition of A and B. Does not modify the original Matrix.
     * @param A
     * @param B
     * @return
     */
    public static Matrix add(Matrix A,Matrix B){
        if(A.height != B.height || A.width != B.width){
            System.err.println("Cannot add matrices with non-matching dimensions");
        }

        double[][] mat = A.get_data();

        for(int i = 0 ; i < A.width; i++){
            for(int j = 0 ; j < A.height;j++){
                mat[i][j] += B.data[i][j];
            }
        }

        return new Matrix(mat);
    }

    public static double[] add(double[] A, double[] B){
        if(A.length != B.length){
            System.err.println("Cannot add two vectors of different lengths");
            return new double[]{0};
        }

        double[] vec = A.clone();

        for(int i = 0 ; i< A.length; i++){
            vec[i] += B[i];
        }

        return vec;
    }

    /**
     * A-B
     * @param A
     * @param B
     * @return
     */
    public static Matrix sub(Matrix A, Matrix B){
        return add(A,mult(-1,B));
    }

    /**
     * A-B
     * @param A
     * @param B
     * @return
     */
    public static double[] sub(double[] A,double[] B) {
        return add(A,mult(-1,B));
    }

    /**
     * transpose the given Matrix.
     * @param A
     * @return
     */
    public static Matrix transpose(Matrix A){
        double[][] mat = new double[A.height][A.width];

        for(int i = 0; i < mat.length;i++){
            for(int j = 0; j < mat[0].length;j++){
                mat[i][j] = A.data[j][i];
            }
        }

        return new Matrix(mat);
    }

    /**
     * performs the inner product of matrices A and B if possible. The original Matrix is not modified.
     * @param A
     * @param B
     * @return
     */
    public static Matrix inner(Matrix A, Matrix B){
        return mult(transpose(A),B);
    }

    /**
     * performs the inner product of vectors A and B
     * @param A
     * @param B
     * @return
     */
    public static double inner(double[] A, double[] B){
        if(A.length != B.length){
            System.err.println("Cannot perform dot product on two different sized vectors");
            return 0;
        }
        double dot = 0;

        for(int i = 0 ; i < A.length; i++){
            dot += A[i] * B[i];
        }

        return dot;
    }

    /**
     * returns the magnitude of the vector A. These are of the form width=1.
     * @param A
     * @return
     */
    public static double mag(double[] A){
        double mag = 0;
        for(int i = 0;i < A.length;i ++){
            mag += A[i] * A[i];
        }

        return Math.sqrt(mag);
    }

    /**
     * returns the norm of a vector.
     * @param A
     * @return
     */
    public static double[] norm(double[] A){
        double[] vec = A.clone();
        double mag = mag(A);
        for(int i = 0 ; i < vec.length; i++){
            vec[i] *= 1/mag;
        }

        return vec;
    }

    /**
     * checks if a given Matrix is upper triangular
     * @param A
     * @return
     */
    public static boolean utriangular(Matrix A){
        double[][] data = A.get_data();
        for(int i = 1 ; i < A.height; i++){
            for(int j = 0 ; j < i; j++){
                if(j == A.width){
                    break;//Matrix is "thin"
                }
                if(data[j][i] != 0){
                    return false;
                }
            }
        }
        return true;
    }
}