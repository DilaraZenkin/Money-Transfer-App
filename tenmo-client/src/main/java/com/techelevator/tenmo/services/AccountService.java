package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.User;
import org.springframework.http.*;
import org.springframework.web.client.*;


import com.techelevator.tenmo.model.Account;
import com.techelevator.view.ConsoleService;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class AccountService {

    private  String API_BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;



    public AccountService(String apiURL, AuthenticatedUser currentUser) {
        API_BASE_URL = apiURL;
        this.currentUser = currentUser;
    }
    public User getUserByAccountId(long accountId) {
        return restTemplate.exchange(API_BASE_URL + "/accounts/" + accountId + "/owner", HttpMethod.GET, makeAuthEntity(), User.class).getBody();
    }

    public Account[] getAccountsByUserId(long userID) {
        Account[] accounts = null;

            accounts = restTemplate.exchange(API_BASE_URL + "/accounts/by-user/" + userID, HttpMethod.GET, makeAuthEntity(), Account[].class).getBody();

        return accounts;
    }

    public BigDecimal getBalance() {
        BigDecimal balance = new BigDecimal(0);
        try {
            balance = restTemplate.exchange(API_BASE_URL + "/accounts/balance/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), BigDecimal.class).getBody();
            System.out.println("Your current account balance is: $" + balance);
        } catch (RestClientException e) {
            System.out.println("Error getting balance");
        }
        return balance;
    }

    public boolean increaseBalance(Account account) {
        boolean success = false;
        String url = API_BASE_URL + "/accounts/balance/increase" + account.getAccountID();
        try {
            restTemplate.put(url, makeEntity(account));
            success = true;
        } catch (RestClientResponseException ex) {
            System.out.println("Error");
        } catch (ResourceAccessException ex) {
            System.out.println("Error also");
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
            System.out.println("Error");
        } catch (ResourceAccessException ex) {
            System.out.println("Error Also");
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
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}





















