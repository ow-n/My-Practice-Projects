package matrix;

/*
 * Models a complex number by holding a real part and imaginary part "a + bi".
 */
public class Complex implements Cloneable, Comparable<Complex> {
   private double a; // Real part
   private double b; // Imaginary part

   /** Constructors */
   public Complex() {
      this(0, 0);
   }

   public Complex(double a) {
      this(a, 0);
   }

   public Complex(double a, double b) {
      this.a = a;
      this.b = b;
   }

   /** Arithmetic Operations */
   public Complex add(Complex second) {
      return new Complex(a + second.a, b + second.b);
   }

   public Complex subtract(Complex second) {
      return new Complex(a - second.a, b - second.b);
   }

   public Complex multiply(Complex second) {
      return new Complex(a * second.a - b * second.b,
            b * second.a + a * second.b);
   }

   public Complex divide(Complex second) {
      return new Complex(
            (a * second.a + b * second.b)
                  / (Math.pow(second.a, 2) + Math.pow(second.b, 2)),
            (b * second.a - a * second.b)
                  / (Math.pow(second.a, 2) + Math.pow(second.b, 2)));
   }

   public double abs() {
      return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
   }

   @Override
   public String toString() {
      if (b == 0) {
         return Double.toString(a);
      }
      return a + "+" + b + "i";
   }

   public double getRealPart() {
      return a;
   }

   public double getImaginaryPart() {
      return b;
   }

   @Override
   protected Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException ex) {
         throw new RuntimeException(ex);
      }
   }

   @Override
   public int compareTo(Complex second) {
      double thisAbsoluteValue = this.abs();
      double secondAbsoluteValue = second.abs();
      return Double.compare(thisAbsoluteValue, secondAbsoluteValue);
   }
}