package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.view.ConsoleService;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.math.BigDecimal;


public class AccountService {

    private final String API_BASE_URL;
    private final ConsoleService console;
    private RestTemplate restTemplate = new RestTemplate();

    public AccountService(String apiURL, ConsoleService consoleService) {
        API_BASE_URL = apiURL;
        this.console = consoleService;
    }

    public Account getAccountById(long accountId) {
        Account account = null;
        try {
            account = restTemplate.getForObject(API_BASE_URL + "/accounts/{id}", Account.class);
        } catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return account;
    }
    public Account getBalance(long accountID) {
        Account account = null;
        try {
            account = restTemplate.getForObject(API_BASE_URL + "/accounts/balance/{id}", Account.class);
        } catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return account;
    }
    public Account increaseBalance(BigDecimal addMoney, long accountID) {
        Account account = null;
        try {
            account = restTemplate.getForObject(API_BASE_URL + "/accounts/balance/increase/{id}", Account.class);
        } catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return account;
    }
    public Account decreaseBalance(BigDecimal addMoney, long accountID) {
        Account account = null;
        try {
            account = restTemplate.getForObject(API_BASE_URL + "/accounts/balance/decrease/{id}", Account.class);
        } catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return account;
    }
    }

