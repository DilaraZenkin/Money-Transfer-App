package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.*;
import org.springframework.web.client.*;


import com.techelevator.tenmo.model.Account;
import com.techelevator.view.ConsoleService;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class AccountService {

    private final String API_BASE_URL;
    private final AuthenticatedUser console;
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;

    public AccountService(String apiURL, AuthenticatedUser consoleService) {
        API_BASE_URL = apiURL;
        this.console = consoleService;
    }

    public Account getAccountById(long accountId) {
        Account account = null;
        try {
            ResponseEntity<Account> response = restTemplate.exchange(API_BASE_URL + "/accounts/" + accountId, HttpMethod.GET
                    , makeAuthEntity(), Account.class);
            account = response.getBody();
            // account = restTemplate.getForObject(API_BASE_URL + "/accounts/" + accountId, Account.class);
        } catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return account;
    }

    public BigDecimal getBalance() {
        BigDecimal balance = new BigDecimal(0);
        try {
            balance = restTemplate.exchange(API_BASE_URL + "/accounts/balance/" + console.getUser().getId(), HttpMethod.GET, makeAuthEntity(), BigDecimal.class).getBody();
            System.out.println("Your current account balance is: $" + balance);
        } catch (RestClientException e) {
            System.out.println("Error getting balance");
        }
        return balance;
    }

    //    public Account getBalance(long accountID) {
//        Account account = null;
//        BigDecimal balance = new BigDecimal(0);
//        try {
//            account = restTemplate.getForObject(API_BASE_URL + "/accounts/balance/" + accountID, Account.class);
//            System.out.println("Your current account balance is: $" + balance);
//        } catch (RestClientResponseException ex) {
//            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
//        } catch (ResourceAccessException ex) {
//            console.printError(ex.getMessage());
//        }
//        return account;
//    }
    public boolean increaseBalance(Account account) {
        boolean success = false;
        String url = API_BASE_URL + "/accounts/balance/increase" + account.getAccountID();
        try {
            restTemplate.put(url, makeEntity(account));
            success = true;
        } catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return success;
    }


    public boolean decreaseBalance(Account account) {
        boolean success = false;
        String url = API_BASE_URL + "/accounts/balance/decrease" + account.getAccountID();
        try {
            restTemplate.put(url, makeEntity(account));
            success = true;
        } catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return success;
    }
    private HttpEntity<Account> makeEntity(Account account) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(account, headers);
    }
    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}





















