package app;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		example1();
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
		
		System.out.println("Would you like to load from a file?(yes or no)");
		String load = input.nextLine();
		if((load.toLowerCase()).contentEquals("yes"))
		{
			System.out.println("What is the name of the file you are loading from?");
			load = input.nextLine();
			bank.loadAccounts(load);
		}
		
	}
}
