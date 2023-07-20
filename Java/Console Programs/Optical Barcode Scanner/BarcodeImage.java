package opticalbarcode;

/*
 * BarcodeImage class realizes all essential data and methods associated with a
 * 2D pattern (an image of a rectangular barcode).
 */
public class BarcodeImage implements Cloneable {
   public static final int MAX_HEIGHT = 30; // exact internal dimensions
   public static final int MAX_WIDTH = 65; // of 2D data
   private boolean[][] imageData; // where image is stored

   /* Constructors */
   public BarcodeImage() {
      this.imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
   }

   public BarcodeImage(String[] strData) {
      // System.out.println(" <in top of constructor>");
      if (!checkSize(strData)) {
         System.out.println("      ...input String[] is invalid.");
         return;
      }
      // System.out.println(" <checkSize() returned true>");
      this.imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
      int imageWidth = strData[0].length();
      int imageHeight = strData.length;
      // Convert 1D String array to 2D Boolean array
      for (int row = 0; row < imageHeight; row++) {
         for (int col = 0; col < imageWidth; col++) {
            this.imageData[MAX_HEIGHT - 1
                  - row][col] = (strData[imageHeight - 1 - row]
                        .charAt(col) == '*');
         }
      }
   }

   /* Accessor & Mutator */
   public boolean getPixel(int row, int col) {
      if (row < 0 || row >= MAX_HEIGHT || col < 0 || col >= MAX_WIDTH) {
         return false;
      } else {
         return imageData[row][col];
      }
   }

   public boolean setPixel(int row, int col, boolean value) {
      if (row < 0 || row >= MAX_HEIGHT || col < 0 || col >= MAX_WIDTH) {
         return false;
      } else {
         this.imageData[row][col] = value;
         return true;
      }
   }

   /* Methods */
   private boolean checkSize(String[] data) {
      if (data == null || data.length < 1 || data.length > MAX_HEIGHT) {
         return false;
      } else {
         for (int row = 0; row < data.length; row++) { // checks width
            if (data[row].length() > MAX_WIDTH) {
               return false;
            }
         }
         return true;
      }
   }

   public void displayToConsole() {
      for (int row = 0; row < MAX_HEIGHT; row++) {
         for (int col = 0; col < MAX_WIDTH; col++) {
            System.out.print(imageData[row][col] ? '*' : ' ');
         }
         System.out.println();
      }
   }

   @Override
   public BarcodeImage clone() {
      BarcodeImage clonedImage = new BarcodeImage();
      if (this.imageData == null) {
         System.out.println("      ...failed to clone.");
         return clonedImage;
      }
      for (int row = 0; row < MAX_HEIGHT; row++) {
         for (int col = 0; col < MAX_WIDTH; col++) {
            clonedImage.setPixel(row, col, this.imageData[row][col]);
         }
      }
      return clonedImage;
   }
}