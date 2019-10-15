package app;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		gamer();
	}
	
	public static void example1()
	{
		Bank bank = new Bank("Bank of CPHS");
		int ewbankAccountNo = bank.createAccount("Mr. Ewbank");
		int howardAccountNo = bank.createAccount("Mr. Howard");
		
		bank.deposit(ewbankAccountNo, 25);
		bank.deposit(howardAccountNo, 75);

		bank.checkBalance(ewbankAccountNo);
		bank.checkBalance(howardAccountNo);
		
		bank.withdraw(ewbankAccountNo, 10);
		bank.closeAccount(howardAccountNo);
		
		bank.checkBalance(ewbankAccountNo);
		bank.checkBalance(howardAccountNo);
		
	}
	
	public static void gamer()
	{
		Scanner input = new Scanner(System.in);
		Bank bank = new Bank("Gamer Bank");
		int accountNumber, amount;
		boolean work;
		
		System.out.println("Would you like to load from a file?(answer \"yes\" or \"no\")");
		String load = input.nextLine();
		if((load.toLowerCase()).contentEquals("yes"))
		{
			System.out.println("What is the name of the file you are loading from?");
			load = input.nextLine();
			bank.loadAccounts(load);
		}
		
		int menu;
		
		do {
			System.out.println("\nChose an option.");
			System.out.println("1. Deposit Money");
			System.out.println("2. Withdraw Money");
			System.out.println("3. Check Balance");
			System.out.println("4. Create Account");
			System.out.println("5. Close Account");
			System.out.println("6. Transfer Money");
			System.out.println("7. Save & Quit");
			System.out.print('>');
			menu = input.nextInt();
			input.nextLine();
			
			switch(menu)
			{
			case 1:
				System.out.println("What is your bank account number?");
				System.out.print('>');
				accountNumber = input.nextInt();
				input.nextLine();
				System.out.println("How much are you depositing?");
				System.out.print('>');
				amount = input.nextInt();
				input.nextLine();
				work = bank.deposit(accountNumber, amount);
				if(work = true)
				{
					System.out.println("Successfully deposited $" + amount + "into account " + accountNumber);
				}
				else
				{
					System.out.println("Failed to deposit into account " + accountNumber);
				}
				break;
				
			case 2:
				System.out.println("What is your bank account number?");
				System.out.print('>');
				accountNumber = input.nextInt();
				input.nextLine();
				System.out.println("How much are you withdrawing?");
				System.out.print('>');
				amount = input.nextInt();
				input.nextLine();
				work = bank.withdraw(accountNumber, amount);
				if(work)
				{
					System.out.println("Successfully withdrew $" + amount + "from account " + accountNumber);
				}
				else
				{
					System.out.println("Failed to withdraw $" + amount + " from account " + accountNumber);
				}
				break;
				
			case 3:
				System.out.println("What is your bank account number?");
				System.out.print('>');
				accountNumber = input.nextInt();
				input.nextLine();
				System.out.println("Account number" + accountNumber + "has $" + bank.checkBalance(accountNumber));
				break;
				
			case 4:
				System.out.println("What is the name you would like to create this account under?");
				System.out.print('>');
				String name = input.nextLine();
				System.out.println(name + "'s account number is " + bank.createAccount(name));
				break;
				
			case 5:
				System.out.println("What account number are you closing?");
				bank.closeAccount(input.nextInt());
				input.nextLine();
			case 6:
				System.out.println("What is the number of the account you are transferring from?");
				System.out.print('>');
				accountNumber = input.nextInt();
				input.nextLine();
				System.out.println("What is the number of the account you are transferring to?");
				System.out.print('>');
				int accountNumber2 = input.nextInt();
				input.nextLine();
				System.out.println("How much money are you transferring?");
				System.out.print('>');
				amount = input.nextInt();
				input.nextLine();
				work = bank.withdraw(accountNumber, amount);
				if(work)
				{
					work = bank.deposit(accountNumber2, amount);
					if(work)
					{
						System.out.println("Successfully transferred $" + amount + "from account " + accountNumber 
								+ "to account " + accountNumber2);
					}
					else
					{
						bank.deposit(accountNumber, amount);
						System.out.println("Failed to transfer from account" + accountNumber + "to account " + accountNumber2);
					}
				}
				else
				{
					System.out.println("Failed to transfer from account" + accountNumber + "to account " + accountNumber2);
				}
				break;
				
			case 7:
				System.out.println("Would you like to export the current storage to a file?(answer \"yes\" or \"no\")");
				System.out.print('>');
				String save = input.nextLine();
				if((save.toLowerCase()).contentEquals("yes"))
				{
					System.out.println("What would you like to name the file you are saving to, without file type?");
					save = input.nextLine();
					bank.saveAccounts(save);
				}
				System.out.println("bye-bye");
				break;
			case 8:
				
			default:
				System.out.println("Invalid input.");
			}
		}while(menu != 7);
	}
}
