/**
 * double precision Matrix maths
 */

public class Matrix {
    public final int width,height;
    public double[][] data;

    /**
     * Create an nxn identity Matrix.
     * @param n
     */
    public Matrix(int n){
        this(n,n);
    }

    /**
     * create a width * height identity Matrix.
     * @param width
     * @param height
     */
    public Matrix(int width, int height){
        this.width = width;
        this.height = height;

        data = Util.identity(width,height).data;
    }

    /**
     * create a Matrix with preset values.
     * @param data the values to be stored
     */
    public Matrix(double[][] data){
        this.data = data.clone();
        width = data.length;
        height = data[0].length;
    }

    /**
     * returns a copy of the current Matrix data
     * @return
     */
    public double[][] get_data(){
        return data.clone();
    }

    /**
     * print the current Matrix in readable form
     */
    public void print(){
        String output = "\n(";
        for(int j = 0 ;j < height;j++){
            for(int i = 0 ; i < width; i++){
                output += String.format("%.16f",data[i][j]) + (i == width - 1 ? "" : " , ");
            }

            if(j == height -1){
                output+= ")";
            }
            output += "\n";
        }
        System.out.printf(output);
    }

    /**
     * prints the Matrix in latex compatable form
     */
    public void print_bmat(){
        String output = "";
        for(int j = 0;  j < height;j++){
            for(int i = 0; i < width; i++){
                output += String.format("%.16f",data[i][j]) + (i == width - 1 ? "" : "&");
            }
            if(j!= height -1){
                output+= "\\\\";
            }
        }
        System.out.println(output);
    }

    /**
     * round all entries in the Matrix to digits # of digits after decimal point
     * @param digits
     */
    public Matrix round(int digits){
        if(digits < 1){
            System.err.println("Cannot round to less than 1 decimal point");
            return this;
        }

        int factor = (int)Math.pow(10,digits);

        for(int i = 0 ;i < width; i++){
            for(int j = 0; j < height; j++){
                data[i][j] *= factor;
                data[i][j] = (int)data[i][j];
                data[i][j] /= factor;
            }
        }
        return this;
    }

    /**
     * stable rounding to 15 decimal places
     */

    public Matrix round(){
        round(15);
        return this;
    }
}