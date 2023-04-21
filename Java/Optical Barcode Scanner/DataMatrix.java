package opticalbarcode;

/*
 * DataMatrix class is a pseudo Datamatrix data structure.
 * Contains a 2D array formatted to contain a left and bottom BLACK "spine" and
 * a alternating right and top BLACK-WHITE pattern in the image.
 */
public class DataMatrix implements BarcodeIO {
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   private BarcodeImage image; // copy of image
   private String text; // image as string
   private int actualWidth;
   private int actualHeight;

   /* Constructors */
   public DataMatrix() {
      this.image = new BarcodeImage();
      this.actualWidth = 0;
      this.actualHeight = 0;
      this.text = "";
   }

   public DataMatrix(BarcodeImage image) {
      if (image == null) {
         System.out.println("      ...invalid BarcodeImage input");
         return;
      }
      if (!scan(image)) {
         System.out.println("      ...failed to scan the BarcodeImage");
         return;
      }
      this.text = ""; // leaves text at its default value
   }

   public DataMatrix(String text) {
      if (text == null || text.length() == 0) {
         System.out.println("      ...invalid text input");
         return;
      }
      if (!readText(text)) {
         System.out.println("      ...failed to read the text");
         return;
      }
      this.image = new BarcodeImage(); // leaves image at its default value
   }

   /* Accessor & Mutator */
   @Override
   public boolean scan(BarcodeImage image) {
      if (image == null) {
         return false;
      }
//    try { // commenting out try block due to causing compilation errors
      this.image = image.clone();
//    } catch (CloneNotSupportedException e) {
      // Don't throw exception, not compatible with BarcodeIO interface
//       System.err.println("Error cloning BarcodeImage: " + e.getMessage());
//    }
      this.actualWidth = this.computeSignalWidth();
      this.actualHeight = this.computeSignalHeight();
      return true;
   }

   @Override
   public boolean readText(String text) {
      if (text == null || text.length() > BarcodeImage.MAX_WIDTH - 1) {
         return false;
      } else {
         this.text = text;
         this.actualWidth = text.length() + 2;
         return true;
      }
   }

   public int getActualWidth() {
      return this.actualWidth;
   }

   public int getActualHeight() {
      return this.actualHeight;
   }

   /* Methods */
   private int computeSignalWidth() {
      int width = 0;
      for (int col = 0; col < BarcodeImage.MAX_WIDTH - 1; col++) {
         if (image.getPixel(BarcodeImage.MAX_HEIGHT - 1, col)) {
            width++;
         }
      }
      return width;
   }

   private int computeSignalHeight() {
      int height = 0;
      for (int row = BarcodeImage.MAX_HEIGHT - 1; row >= 0; row--) {
         if (image.getPixel(row, 0)) {
            height++;
         }
      }
      return height;
   }

   @Override
   public void displayTextToConsole() {
      System.out.println("The secret message is: " + this.text);
   }

   @Override
   public void displayImageToConsole() {
      for (int topBorder = 0; topBorder < getActualWidth() + 2; topBorder++) {
         System.out.print("-"); // prints top open border
      }
      System.out.println();
      for (int row = BarcodeImage.MAX_HEIGHT
            - getActualHeight(); row < BarcodeImage.MAX_HEIGHT; row++) {
         System.out.print("|");
         for (int col = 0; col < getActualWidth(); col++) {
            System.out.print(
                  this.image.getPixel(row, col) ? BLACK_CHAR : WHITE_CHAR);
         }
         System.out.println("|");
      }
   }

   @Override
   public boolean translateImageToText() {
      if (image == null) {
         return false;
      } else {
         StringBuilder sb = new StringBuilder();
         for (int col = 1; col < actualWidth - 1; col++) {
            char c = readCharFromCol(col);
            sb.append(c);
         }
         text = sb.toString();
         return true;
      }
   }

   private char readCharFromCol(int col) {
      int charFromCol = 0;
      int rowValue = 1;
      for (int row = BarcodeImage.MAX_HEIGHT - 2; row > BarcodeImage.MAX_HEIGHT
            - getActualHeight(); row--) {
         if (image.getPixel(row, col)) {
            charFromCol += rowValue;
         }
         rowValue *= 2;
      }
      return (char) charFromCol;
   }

   @Override
   public boolean generateImageFromText() {
      if (text == null) {
         return false;
      } else {
         clearImage();
         // Draw the closed borderlines on the bottom and left
         for (int col = 0; col < text.length() + 2; col++) {
            image.setPixel(BarcodeImage.MAX_HEIGHT - 1, col, true);
         }
         for (int row = BarcodeImage.MAX_HEIGHT
               - 1; row > BarcodeImage.MAX_HEIGHT - 11; row--) {
            image.setPixel(row, 0, true);
         }
         // Draw the open limitation lines on the top and right
         for (int col = 0; col < text.length() + 2; col += 2) {
            image.setPixel(BarcodeImage.MAX_HEIGHT - 10, col, true);
         }
         for (int row = BarcodeImage.MAX_HEIGHT
               - 1; row > BarcodeImage.MAX_HEIGHT - 11; row -= 2) {
            image.setPixel(row, text.length() + 1, true);
         }
         // Write character to column
         int col = 1;
         for (int textIndex = 0; textIndex < text
               .length(); textIndex++, col++) {
            int asciiValue = (int) text.charAt(textIndex);
            writeCharToCol(col, asciiValue);
         }
         return true;
      }
   }

   private boolean writeCharToCol(int col, int code) {
      int rowValue = 1;
      for (int row = BarcodeImage.MAX_HEIGHT - 2; row >= BarcodeImage.MAX_HEIGHT
            - getActualHeight(); row--) {
         if ((code & rowValue) > 0) {
            image.setPixel(row, col, true);
         }
         rowValue *= 2;
      }
      return true;
   }

   /* Utility Methods for Debugging */
   public void displayRawImage() {
      for (int row = 0; row < BarcodeImage.MAX_HEIGHT; row++) {
         for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++) {
            System.out
                  .print(image.getPixel(row, col) ? BLACK_CHAR : WHITE_CHAR);
         }
         System.out.println();
      }
   }

   public void clearImage() {
      for (int row = 0; row < BarcodeImage.MAX_HEIGHT; row++) {
         for (int col = 0; col < BarcodeImage.MAX_WIDTH; col++) {
            image.setPixel(row, col, false);
         }
      }
   }
}