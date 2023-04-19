package opticalbarcode;

/*
 * BarcodeIO interface contains method signatures for Barcode to allow I/O.
 */
public interface BarcodeIO {
   public boolean scan(BarcodeImage bc);
   public boolean readText(String text);
   public boolean generateImageFromText();
   public boolean translateImageToText();
   public void displayTextToConsole();
   public void displayImageToConsole();
}