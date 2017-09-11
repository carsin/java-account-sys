package me.rze;

import java.util.Random;
import java.util.Scanner;

public class Main {
	
	Scanner scan = new Scanner(System.in); 
	Random r = new Random();
	
	public int users = 16;
	
	public boolean angryText = true;
	
	public String currentChoice;
	
	private String usernames[] = new String[users];
	private String passwords[] = new String[users];
	private String IDS[] = new String[users];
	
	private String usernameInput;
	private String passwordInput;
	
	private String currentUsername;
	private String currentPassword;
	private String currentId;
	
	private String usernameCreationInput;
	private String passwordCreationInput;
	
	
	public Main() {
		loginMenu();
	}
	
	public static void main(String args[]) {
		new Main();
	}
	
	public void loginMenu() {
		currentChoice = null;
		System.out.print("PRESS 1 TO LOGIN. PRESS 2 TO CREATE ACCOUNT.");
		currentChoice = scan.nextLine();
		if (currentChoice.equals("1")) {
			login();
			currentChoice = null;
		}
		if (currentChoice.equals("2")) {
			createAccount();
			currentChoice = null;
		} else {
			System.out.println("COMMAND UNRECOGNIZED. TRY AGAIN.");
			currentChoice = null;
			loginMenu();	
		}
	}
	
	public void login() {
		System.out.print("USERNAME: ");
		usernameInput = scan.nextLine();
		usernameInput = usernameInput.toLowerCase();
		
		System.out.print("PASSWORD: ");
		passwordInput = scan.nextLine();
		passwordInput = passwordInput.toLowerCase();
		
		if (checkLogin(usernameInput, passwordInput)) {
			currentUsername = usernameInput;
			currentPassword = passwordInput;
			currentId = getId(getArrayId(currentUsername));
			System.out.println(currentId);
			System.out.println("LOGIN SUCCESSFUL. WELCOME, " + usernameInput);
			mainMenu();
		} else {
			System.out.println("CREDENTIALS INCORRECT. TRY AGAIN.");
			usernameInput = null;
			passwordInput = null;
			loginMenu();
		}
	}
	
	public void createAccount() {
		System.out.print("USERNAME: ");
		
		usernameCreationInput = scan.nextLine();
		usernameCreationInput = usernameCreationInput.toLowerCase();
		
		for (int i = 0; i < users; i++) {
			if (usernameCreationInput.equals(usernames[i])) {
				System.out.println("USERNAME TAKEN.");
				usernameCreationInput = null;
				createAccount();
			}
		}
		
		System.out.print("PASSWORD: ");
		
		passwordCreationInput = scan.nextLine();
		passwordCreationInput = passwordCreationInput.toLowerCase();
		for (int i = 0; i < users; i++) {
			if (usernames[i] == null) {
				usernames[i] = usernameCreationInput;
				passwords[i] = passwordCreationInput;
				if (r.nextInt(1) == 1) IDS[i] = "N";
				if (r.nextInt(1) == 0) IDS[i] = "Y";
				System.out.println("ACCOUNT CREATION SUCCESSFUL.");
				loginMenu();
			} else {
				System.out.println("ERROR. USERS FULL.");
			}
		}
	}
	
	public boolean checkLogin(String usernameInput, String passwordInput) {
		for (int i = 0; i < users; i++) {
			if (usernameInput.equals(usernames[i]) && passwordInput.equals(passwords[i])) {
				return true;
			}
		}
		return false;
	}
	
	public void mainMenu() {
		System.out.print("PRESS 1 TO GO TO ID INFO. PRESS 2 FOR SETTINGS. PRESS 3 FOR GAMES (NOT WORKING), OR 4 TO LOG OUT.");
		currentChoice = scan.nextLine();
		
		if (currentChoice.equals("1")) {
			System.out.println("Your id is " + currentId);
			System.out.println("The user in slot 1's id is " + getId(1));
			System.out.println("You are " + readId(currentId));
			currentChoice = null;
			mainMenu();
		}
		
		if (currentChoice.equals("2")) {
			settingsMenu();
		}
		
		if (currentChoice.equals("3")) {
			System.out.println("its not working");
			currentChoice = null;
			mainMenu();
		}
		
		if (currentChoice.equals("4")) {
			usernameInput = null;
			passwordInput = null;
			currentUsername = null;
			currentPassword = null;
			currentId = null;
			usernameCreationInput = null;
			passwordCreationInput = null;
			loginMenu();
		}
		System.out.println("COMMAND NOT RECOGNZIED.");
		mainMenu();
		currentChoice = null;
	}
	
	public void settingsMenu() {
		currentChoice = null;
		System.out.print("PRESS 1 FOR ANGRY TEXT, PRESS 2 FOR NICE TEXT.");
		currentChoice = scan.nextLine();
		
		if (currentChoice.equals("1")) {
			if (angryText == true) {
				System.out.println("YOU ALREADY HAVE ANGRY TEXT");
			}
			angryText = true;
		}
		if (currentChoice.equals("2")) {
			if (angryText = false) {
				System.out.println("You already have nice text, " + currentUsername);
			}
			angryText = false;
		}
	}
	
	public String getId(int arrayId) {
		for (int i = 0; i < users; i++) {
			if (usernames[i].equals(currentUsername)) {
				return IDS[i];
			}
		}
		return "error couldn't get id";
	}
	
	public int getArrayId(String username) {
		for (int i = 0; i < users; i++) {
			if (usernames[i].equals(username)) {
				return i;
			}
		}
		return -1;
	}

	public String readId(String currentId) {
		if (currentId.charAt(0) == 'Y') return "a meme, ";
		if (currentId.charAt(0) == 'N') return "not a meme,";
		return "error couldn't read id";
	}
	
}
