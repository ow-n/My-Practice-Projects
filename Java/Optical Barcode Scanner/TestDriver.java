package opticalbarcode;

/*
 * Test driver that demonstrates the functionality of an Optical Barcode Reader
 * - Reads 2D barcode images
 * - Decodes the 2D barcode and translates it into a hidden message
 * - Creates a new barcode from a custom message
 */
public class TestDriver {

   public static void main(String[] args) {
      String[] sImageIn1 = {
         "* * * * * * * * * * * * * * *",   // Col = 29, Row = 10
         "*                           *",   // 128s   (8)
         "**********  *** *** *******  ",   // 64s    (7)
         "* ***************************",   // 32s    (6)
         "**    * *   * *  *   * *     ",   // 16s    (5)
         "* **     ** **          **  *",   // Eights (4)
         "****** ****  **   *  ** ***  ",   // Fours  (3)
         "****  **     *   *   * **   *",   // Twos   (2)
         "***  *  *   *** * * ******** ",   // Ones   (1)
         "*****************************"    // 8 relevant rows
      };
      
      String[] sImageIn2 = {
         "* * * * * * * * * * * * * * *",
         "*                           *",   // 128s
         "*** ** ******** ** ***** *** ",   // 64s
         "*  **** ***************** ***",   // 32s
         "* *  *    *      *  *  *  *  ",   // 16s
         "*       ** **** *          **",   // Eights
         "*    * ****  **    * * * *** ",   // Fours
         "***    ***       * **    * **",   // Twos
         "*** *   **  *   ** * **   *  ",   // Ones
         "*****************************"
      };

      BarcodeImage bc = new BarcodeImage(sImageIn1);
      DataMatrix dm = new DataMatrix(bc);

      System.out
            .println("Printing out BarcodeImage in the bottom left corner...");
      bc.displayToConsole();

      System.out.println("\n\nChecking that width should be '29' and "
            + "height should be '10'...");
      System.out.println("Width = " + dm.getActualWidth() + ", Height = "
            + dm.getActualHeight());

      // First secret message
      System.out.println("\n\nTranslating first image to text...");
      dm.translateImageToText();
      dm.displayTextToConsole();
      System.out.println("Printing first image to console...`");
      dm.displayImageToConsole();

      // Second secret message
      System.out.println("\n\nTranslating second image to text...");
      bc = new BarcodeImage(sImageIn2);
      dm.scan(bc);
      dm.translateImageToText();
      dm.displayTextToConsole();
      System.out.println("Printing second image to console...");
      dm.displayImageToConsole();
      System.out.println("Printing raw second image to console...");
      dm.displayRawImage();

      // Create custom message (Two '!!''s to complete rectangle corner)
      System.out.println("\n\nCreating custom message "
            + "\"What a great resume builder this is!!\"...");
      dm.readText("What a great resume builder this is!!");
      System.out.print("Generating image from text...");
      dm.generateImageFromText();
      System.out.println("      ...complete!");
      dm.displayTextToConsole();
      System.out.println("Printing custom image to console...");
      dm.displayImageToConsole();
      System.out.println("|*What a great resume builder this is!!*|");
      System.out.println("-----------------------------------------");
      System.out.println("Printing raw custom image to console...");
      dm.displayRawImage();
      System.out.println("*      <Checking Convesion Width>     *");
      System.out.println("*What a great resume builder this is!!*");
      System.out.println("***************************************");

      System.out.println("\nTranslating custom image back to text...");
      dm.translateImageToText();
      dm.displayTextToConsole();

      System.out.println("\n\nMaking sure user input errors are checked before "
            + "computations...");
      String[] errorString = { "1234567890123456789012345678901234567890"
            + "123456789012345678901234567890" }; // string length = 70
      System.out.println("   ...creating new BarcodeImage with error...");
      BarcodeImage bcError = new BarcodeImage(errorString);
      System.out.println("   BarcodeImage constructor returned without "
            + "throwing exceptions.");
      System.out.println(
            "\n   ...creating new DataMatrix(BarcodeImage) " + "with error...");
      DataMatrix dmError1 = new DataMatrix(bcError);
      System.out.println("   DataMatrix constructor returned without "
            + "throwing exceptions.");
      System.out.println(
            "\n   ...creating new DataMatrix(String) " + "with error...");
      DataMatrix dmError2 = new DataMatrix("");
      System.out.println("   DataMatrix constructor returned without "
            + "throwing exceptions.");
   }
}


/******************************************************************************/
/*                                   Output                                   */
/****************************************************************************//*

Printing out BarcodeImage in the bottom left corner...
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
* * * * * * * * * * * * * * *                                    
*                           *                                    
**********  *** *** *******                                      
* ***************************                                    
**    * *   * *  *   * *                                         
* **     ** **          **  *                                    
****** ****  **   *  ** ***                                      
****  **     *   *   * **   *                                    
***  *  *   *** * * ********                                     
*****************************                                    


Checking that width should be '29' and height should be '10'...
Width = 29, Height = 10


Translating first image to text...
The secret message is: Wonderful, you are awesome!
Printing first image to console...`
-------------------------------
|* * * * * * * * * * * * * * *|
|*                           *|
|**********  *** *** *******  |
|* ***************************|
|**    * *   * *  *   * *     |
|* **     ** **          **  *|
|****** ****  **   *  ** ***  |
|****  **     *   *   * **   *|
|***  *  *   *** * * ******** |
|*****************************|


Translating second image to text...
The secret message is: CS at Foothill is great Fun
Printing second image to console...
-------------------------------
|* * * * * * * * * * * * * * *|
|*                           *|
|*** ** ******** ** ***** *** |
|*  **** ***************** ***|
|* *  *    *      *  *  *  *  |
|*       ** **** *          **|
|*    * ****  **    * * * *** |
|***    ***       * **    * **|
|*** *   **  *   ** * **   *  |
|*****************************|
Printing raw second image to console...
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
* * * * * * * * * * * * * * *                                    
*                           *                                    
*** ** ******** ** ***** ***                                     
*  **** ***************** ***                                    
* *  *    *      *  *  *  *                                      
*       ** **** *          **                                    
*    * ****  **    * * * ***                                     
***    ***       * **    * **                                    
*** *   **  *   ** * **   *                                      
*****************************                                    


Creating custom message "What a great resume builder this is!!"...
Generating image from text...      ...complete!
The secret message is: What a great resume builder this is!!
Printing custom image to console...
-----------------------------------------
|* * * * * * * * * * * * * * * * * * * *|
|*                                     *|
|***** * ***** ****** ******* **** **   |
|* *************************************|
|**  *    *  * * **    *    * *  *  *   |
|* *               *    **     **  *   *|
|**  *   * * *  * ***  * ***  *         |
|**      **    * *    *     *    *  *  *|
|** *  * * **   *****  **  *    ** **** |
|***************************************|
|*What a great resume builder this is!!*|
-----------------------------------------
Printing raw custom image to console...
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
                                                                 
* * * * * * * * * * * * * * * * * * * *                          
*                                     *                          
***** * ***** ****** ******* **** **                             
* *************************************                          
**  *    *  * * **    *    * *  *  *                             
* *               *    **     **  *   *                          
**  *   * * *  * ***  * ***  *                                   
**      **    * *    *     *    *  *  *                          
** *  * * **   *****  **  *    ** ****                           
***************************************                          
*      <Checking Convesion Width>     *
*What a great resume builder this is!!*
***************************************

Translating custom image back to text...
The secret message is: What a great resume builder this is!!


Making sure user input errors are checked before computations...
   ...creating new BarcodeImage with error...
      ...input String[] is invalid.
   BarcodeImage constructor returned without throwing exceptions.

   ...creating new DataMatrix(BarcodeImage) with error...
      ...failed to clone.
   DataMatrix constructor returned without throwing exceptions.

   ...creating new DataMatrix(String) with error...
      ...invalid text input
   DataMatrix constructor returned without throwing exceptions.

/******************************************************************************/