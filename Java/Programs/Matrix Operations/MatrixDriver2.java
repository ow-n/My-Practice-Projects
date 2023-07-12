package matrix;

/**
 * Demonstrates addition and multiplication of complex matricies using the
 * GenericMatrix, ComplexMatrix, Complex classes.
 */
public class MatrixDriver2 {
   public static void main(String[] args) {
      ComplexMatrix complexMatrix = new ComplexMatrix();

      Complex[][] matrix1 = {
            { new Complex(1, 1), new Complex(1, 2), complexMatrix.zero() },
            { new Complex(2, 1), complexMatrix.zero(), new Complex(2, 3) },
            { new Complex(3, 1), new Complex(3, 2), complexMatrix.zero() } };

      Complex[][] matrix2 = {
            { new Complex(-1, 1), complexMatrix.zero(), new Complex(1, 3) },
            { new Complex(-2, 1), complexMatrix.zero(), new Complex(2, 3) },
            { new Complex(-3, 1), complexMatrix.zero(), new Complex(3, 3) } };

      System.out.println("Addition of two matrices: ");
      GenericMatrix.printResultVertical(matrix1, matrix2,
            complexMatrix.addMatrix(matrix1, matrix2), '+');

      System.out.println("\nMultiplication of two matrices: ");
      GenericMatrix.printResultVertical(matrix1, matrix2,
            complexMatrix.multiplyMatrix(matrix1, matrix2), '*');

      Complex[][] matrix3 = { { new Complex(1, 1), new Complex(1, 2) },
            { new Complex(2, -1), new Complex(2, -2) } };

      System.out.println("\nAddition of two different sized matrices: ");
      try {
         Complex[][] resultAdditionInvalid = complexMatrix.addMatrix(matrix1,
               matrix3);
      } catch (RuntimeException e) {
         System.out.println("Error: Cannot add matrices of different sizes. "
               + e.getMessage());
      }
   }
}

/******************************************************************************/
/*                                   Output                                   */
/****************************************************************************//*
Addition of two matrices: 
(1.0+1.0i)(1.0+2.0i)(0.0)         (-1.0+1.0i)(0.0)(1.0+3.0i)
(2.0+1.0i)(0.0)(2.0+3.0i)    +    (-2.0+1.0i)(0.0)(2.0+3.0i)
(3.0+1.0i)(3.0+2.0i)(0.0)         (-3.0+1.0i)(0.0)(3.0+3.0i)
         (0.0+2.0i)(1.0+2.0i)(1.0+3.0i)
    =    (0.0+2.0i)(0.0)(4.0+6.0i)
         (0.0+2.0i)(3.0+2.0i)(3.0+3.0i)

Multiplication of two matrices: 
(1.0+1.0i)(1.0+2.0i)(0.0)         (-1.0+1.0i)(0.0)(1.0+3.0i)
(2.0+1.0i)(0.0)(2.0+3.0i)    *    (-2.0+1.0i)(0.0)(2.0+3.0i)
(3.0+1.0i)(3.0+2.0i)(0.0)         (-3.0+1.0i)(0.0)(3.0+3.0i)
         (-6.0+-3.0i)(0.0)(-6.0+11.0i)
    =    (-12.0+-6.0i)(0.0)(-4.0+22.0i)
         (-12.0+1.0i)(0.0)(0.0+23.0i)

Addition of two different sized matrices: 
Error: Cannot add matrices of different sizes. The matrices do not have the same
 size
/******************************************************************************/