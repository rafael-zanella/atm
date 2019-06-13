package atm;
// Keypad.java
// Represents the keypad of the ATM
import java.util.Scanner; // program uses Scanner to obtain user input

public class Keypad extends HardwareType
{
   private Scanner input; // reads data from the command line
                         
   
   private static Keypad instance;
   

   private Keypad()
   {
      input = new Scanner(System.in);    
   } 
   
   public static Keypad getInstance() {
       if (instance == null) instance = new Keypad();
       return instance;
   }
   
   
   // no-argument constructor initializes the Scanner
  

   // return an integer value entered by user 
   public int getInput()
   {
      return input.nextInt(); // we assume that user enters an integer  
   } // end method getInput
} // end class Keypad  



/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/