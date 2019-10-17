package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Bank {
	// Variable for logging/not logging
	private static final boolean LOG = false;

	private static int accountCounter = 1;
	private String name;
	private ArrayList<Account> accounts;

	public Bank() {
		this("Bank Name");
	}

	public Bank(String name) {
		this.name = name;
		accounts = new ArrayList<>();
		log("Bank Created");
	}

	public int createAccount(String name) {
		Account newAccount = new Account(name);
		accounts.add(newAccount);

		log("Added account " + newAccount);
		return newAccount.accountNumber;
	}

	public boolean closeAccount(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not close account " + accountNumber);
			return false;
		}
		accounts.remove(account);
		log("Successfully closed " + account);
		return true;
	}

	public boolean deposit(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not deposit to account " + accountNumber);
			return false;
		}
		account.balance += amount;
		log("Successfully deposited $" + amount + " to " + account);
		return true;
	}

	public boolean withdraw(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not withdraw from account " + accountNumber);
			return false;
		}
		if (account.balance < amount) {
			log("Insufficient funds in " + account);
			return false;
		}
		account.balance -= amount;
		log("Successfully withdrew $" + amount + " from " + account);
		return true;
	}

	public int checkBalance(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not check balance of account " + accountNumber);
			return -1;
		}
		log("Successfully checked balance of account " + account);
		return account.balance;
	}

	public void saveAccounts(String filename) {
		try {
			FileWriter fw = new FileWriter(filename + ".txt");
			for(Account a: accounts)
			{
				String write = a.toString();
				fw.append(write);
				fw.append("\n");
			}
			log("Save done?");
			fw.close();
		} catch (IOException e) {
			log("exception");
			e.printStackTrace();
		}
	}

	public void loadAccounts(String filename) {
		try {
			Scanner fileIn = new Scanner(new File(filename + ".txt"));
			
			while(fileIn.hasNextLine())
			{
				String line = fileIn.nextLine();
				String[] split = line.split("::");
				
				int accountNum = Integer.parseInt((split[0]).substring(1));
				String name = split[1];
				int balance = Integer.parseInt(split[2].substring(1, split[2].length() - 1));
				
				Account a = new Account(accountNum, name, balance);
				accounts.add(a);
			}
			System.out.println("Accounts successfully loaded.");
			
		} catch (FileNotFoundException e) {
			
			System.out.println("No saved account file found.");
		}
	}

	private Account findAccount(int accountNumber) {
		for (int i = accounts.size() - 1; i >= 0; i--) {
			if (accounts.get(i).accountNumber == accountNumber)
				return accounts.get(i);
		}
		return null;
	}

	private void log(String message) {
		if (LOG)
			System.out.println(name + " ::: " + message + ".");
	}

	/**
	 * Private Inner Class Account
	 * Deals with Account information
	 */
	private class Account {
		int accountNumber;
		String name;
		int balance;

		private Account(String name) {
			this.name = name;
			balance = 0;
			accountNumber = accountCounter++;
		}

		public Account(int accountNum, String name, int balance) {
			this.accountNumber = accountNum;
			this.name = name;
			this.balance = balance;
		}

		public String toString() {
			return "{" + accountNumber + "::" + name + "::$" + balance + "}";
		}

	}
}
