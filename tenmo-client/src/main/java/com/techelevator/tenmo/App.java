package com.techelevator.tenmo;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.AuthenticationServiceException;
import com.techelevator.tenmo.services.TransferService;
import com.techelevator.view.ConsoleService;
import io.cucumber.java.en_old.Ac;
import org.springframework.http.HttpMethod;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {

	private static final String API_BASE_URL = "http://localhost:8080";

	private static final String MENU_OPTION_EXIT = "Exit";
	private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
	private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
	private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };


    private AuthenticatedUser currentUser;
    private ConsoleService console;
    private AuthenticationService authenticationService;


    public static void main(String[] args) {
    	App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL));
    	app.run();
    }

    public App(ConsoleService console, AuthenticationService authenticationService) {
		this.console = console;
		this.authenticationService = authenticationService;
	}

	public void run() {
		System.out.println("*********************");
		System.out.println("* Welcome to TEnmo! *");
		System.out.println("*********************");

		registerAndLogin();
		mainMenu();
	}

	private void mainMenu() {
		while(true) {
			String choice = (String)console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
				viewCurrentBalance();
			} else if(MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
				viewTransferHistory();
			} else if(MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
				viewPendingRequests();
			} else if(MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
				sendBucks();
			} else if(MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
				requestBucks();
			} else if(MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void viewCurrentBalance() {
		// TODO Auto-generated method stub
		AccountService accountService = new AccountService(API_BASE_URL, currentUser);
		try {
			accountService.getBalance();
		} catch (NullPointerException e) {
			System.out.println("Could not find a balance");

		}
	}


	private void viewTransferHistory() {
		AccountService accountsService = new AccountService(API_BASE_URL, currentUser);
		Account[] accounts = accountsService.getAccountsByUserId(currentUser.getUser().getId());
		Set<Long> accountIds = new HashSet<>();
		for (Account account : accounts) {
			accountIds.add(account.getAccountID());
		}

		TransferService transferService = new TransferService(API_BASE_URL, currentUser);
		Transfer[] transfers = transferService.getAllTransfers();



		try {
			System.out.println("-------------------------------------------\r\n" +
					"Transfer\r\n" +
					"ID            From/To        Amount\r\n" +
					"-------------------------------------------\r\n");


			for (Transfer i : transfers) {
				String name = "";

				String fromOrTo = "";
				if (accountIds.contains(i.getAccountFrom())) {
					name = accountsService.getUserByAccountId(i.getAccountTo()).getUsername();
					fromOrTo = "To: ";

				} else if(accountIds.contains(i.getAccountTo())){
					name = accountsService.getUserByAccountId(i.getAccountFrom()).getUsername();
					fromOrTo = "From: ";

				} // todo will need to add an exception handling

				System.out.println(i.getTransferID() +"\t\t" + fromOrTo + name + "\t\t$" + i.getAmount());

			}
			System.out.print("-------------------------------------------\r\n" +
					"Please enter transfer ID to view details (0 to cancel): ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			if (Integer.parseInt(input) != 0) {
				boolean foundTransferId = false;
				for (Transfer temp : transfers) {
					if (Integer.parseInt(input) == temp.getTransferID()) {
//						Transfer temp = restTemplate.exchange(API_BASE_URL + "/transfers/getalltransfers/" + i.getTransferID(), HttpMethod.GET, makeAuthEntity(), Transfer.class).getBody();
						foundTransferId = true;
						System.out.println("--------------------------------------------\r\n" +
								"Transfer Details\r\n" +
								"--------------------------------------------\r\n" +
								" Id: "+ temp.getTransferID() + "\r\n" +
								" From: " + temp.getAccountFroms() + "\r\n" +
								" To: " + temp.getAccountTos() + "\r\n" +
								" Type: " + temp.getTransferTypeId() + "\r\n" +
								" Status: " + temp.getTransferStatusId() + "\r\n" +
								" Amount: $" + temp.getAmount());
					}
				}
				if (!foundTransferId) {
					System.out.println("Not a valid transfer ID");
				}
			}
		} catch (Exception e) {
			System.out.println("Something went wrong!");
			e.printStackTrace();
		}
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub

	}

	private void sendBucks() {
		// TODO Auto-generated method stub
TransferService transferService = new TransferService(API_BASE_URL, currentUser);
transferService.sendBucks();
	}

	private void requestBucks() {
		// TODO Auto-generated method stub
TransferService transferService = new TransferService(API_BASE_URL,currentUser);
transferService.requestBucks();
	}

	private void exitProgram() {
		System.exit(0);
	}

	private void registerAndLogin() {
		while(!isAuthenticated()) {
			String choice = (String)console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	private boolean isAuthenticated() {
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
		while (!isRegistered) //will keep looping until user is registered
		{
			UserCredentials credentials = collectUserCredentials();
			try {
				authenticationService.register(credentials);
				isRegistered = true;
				System.out.println("Registration successful. You can now login.");
			} catch(AuthenticationServiceException e) {
				System.out.println("REGISTRATION ERROR: "+e.getMessage());
				System.out.println("Please attempt to register again.");
			}
		}
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) //will keep looping until user is logged in
		{
			UserCredentials credentials = collectUserCredentials();
			try {
				currentUser = authenticationService.login(credentials);
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: "+e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}

	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
}
