package atm;
// ATM.java
// Represents an automated teller machine

public class ATM 
{
	private boolean userAuthenticated; // whether user is authenticated
	private int currentAccountNumber; // current user's account number
	private Screen screen; // ATM's screen
	private Keypad keypad; // ATM's keypad
	private CashDispenser cashDispenser; // ATM's cash dispenser
	private DepositSlot depositSlot; // ATM's deposit slot
	private BankDatabase bankDatabase; // account information database


	// no-argument ATM constructor initializes instance variables
	public ATM() 
	{
		userAuthenticated = false; // user is not authenticated to start
		currentAccountNumber = 0; // no current account number to start
		screen = new Screen(); // create screen
		keypad = new Keypad(); // create keypad 
		cashDispenser = new CashDispenser(); // create cash dispenser
		depositSlot = new DepositSlot(); // create deposit slot
		bankDatabase = new BankDatabase(); // create acct info database
	} // end no-argument ATM constructor

	// start ATM 
	public void run()
	{
		// welcome and authenticate user; perform transactions
		while (true)
		{
			// loop while user is not yet authenticated
			while (!userAuthenticated) 
			{
				screen.displayMessageLine("\nWelcome!"); 
				authenticateUser(); // authenticate user
			} // end while

			performTransactions(); // user is now authenticated 
			userAuthenticated = false; // reset before next ATM session
			currentAccountNumber = 0; // reset before next ATM session 
			screen.displayMessageLine("\nThank you! Goodbye!");
		} // end while   
	} // end method run

	// attempts to authenticate user against database
	private void authenticateUser() 
	{

		screen.displayMessage("\nPlease enter your account number: ");
		int accountNumber = keypad.getInput(); // input account number
		screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
		int pin = keypad.getInput(); // input PIN

		// set userAuthenticated to boolean value returned by database
		userAuthenticated = 
				bankDatabase.authenticateUser(accountNumber, pin);

		// check whether authentication succeeded
		if (userAuthenticated)
		{
			currentAccountNumber = accountNumber; // save user's account #
		} // end if
		else
			screen.displayMessageLine(
					"Invalid account number or PIN. Please try again.");
	} // end method authenticateUser

	// display the main menu and perform transactions
	private void performTransactions() 
	{
		// local variable to store transaction currently being processed
		Transaction currentTransaction = null;

		boolean userExited = false; // user has not chosen to exit

		// loop while user has not chosen option to exit system
		while (!userExited)
		{     
			//SHOW MENU
			MainMenu.display();
			int mainMenuSelection = MainMenu.getOption(keypad);

			//FOLLOW CHOICE

			// show main menu and get user selection

			// decide how to proceed based on user's menu selection
			switch (mainMenuSelection)
			{
		
			case 1: // balance
				currentTransaction = new BalanceInquiry(
						currentAccountNumber, screen, bankDatabase);
				currentTransaction.execute(); // execute transaction
				break; 
			case 2: //withdraw
				currentTransaction = new Withdrawal(currentAccountNumber, screen, 
						bankDatabase, keypad, cashDispenser);
				currentTransaction.execute(); // execute transaction
				break; 
			case 3: // deposit
				currentTransaction = new Deposit(currentAccountNumber, screen, 
						bankDatabase, keypad, depositSlot);
				currentTransaction.execute(); // execute transaction
				break; 
			case 4: // user chose to terminate session
				System.out.println("\nExiting the system...");
				userExited = true; // this ATM session should end
				break;
			default: // user did not enter an integer from 1-4
				System.out.println(
						"\nYou did not enter a valid selection. Try again.");
				break;
			} // end switch
		} // end while
	} // end method performTransactions
	
} // end class ATM



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