package matrix;

import java.util.Scanner;

/*
 * Demonstrates the operations of matricies using the `IntegerMatrix` class.
 */
public class MatrixDriver1 {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      System.out.print("Enter the first complex number: ");
      double a = input.nextDouble();
      double b = input.nextDouble();
      Complex c1 = new Complex(a, b);

      System.out.print("Enter the second complex number: ");
      double c = input.nextDouble();
      double d = input.nextDouble();
      input.close();
      Complex c2 = new Complex(c, d);

      System.out.println(
            "(" + c1 + ")" + " + " + "(" + c2 + ")" + " = " + c1.add(c2));
      System.out.println(
            "(" + c1 + ")" + " - " + "(" + c2 + ")" + " = " + c1.subtract(c2));
      System.out.println(
            "(" + c1 + ")" + " * " + "(" + c2 + ")" + " = " + c1.multiply(c2));
      System.out.println(
            "(" + c1 + ")" + " / " + "(" + c2 + ")" + " = " + c1.divide(c2));
      System.out.println("|" + c1 + "| = " + c1.abs());

      Complex c3 = (Complex) c1.clone();
      System.out.println(c1 == c3);
      System.out.println(c3.getRealPart());
      System.out.println(c3.getImaginaryPart());
      Complex c4 = new Complex(4, -0.5);
      Complex[] list = { c1, c2, c3, c4 };
      java.util.Arrays.sort(list);
      System.out.println(java.util.Arrays.toString(list));

      System.out.println(
            "Comparing " + c1 + " and " + c2 + " gives: " + c1.compareTo(c2));
      System.out.println(
            "Comparing " + c2 + " and " + c1 + " gives: " + c2.compareTo(c1));
      System.out.println(
            "Comparing " + c1 + " and " + c1 + " gives: " + c1.compareTo(c1));
   }
}


/******************************************************************************/
/*                                   Output                                   */
/****************************************************************************//*
Enter the first complex number: 3.5 5.5 
Enter the second complex number: –3.5 1 
(3.5 + 5.5i) + (–3.5 + 1.0i) = 0.0 + 6.5i
(3.5 + 5.5i) – (–3.5 + 1.0i) = 7.0 + 4.5i
(3.5 + 5.5i) * (–3.5 + 1.0i) = –17.75 + –15.75i
(3.5 + 5.5i) / (–3.5 + 1.0i) = –0.5094 + –1.7i
|(3.5 + 5.5i)| = 6.519202405202649
false
3.5
5.5
[–3.5 + 1.0i, 4.0 + –0.5i, 3.5 + 5.5i, 3.5 + 5.5i] 
/******************************************************************************/