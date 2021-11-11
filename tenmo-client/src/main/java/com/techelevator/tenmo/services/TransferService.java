package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.apiguardian.api.API;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Scanner;

public class TransferService {
    private final String API_BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;


    public TransferService(String api_base_url, AuthenticatedUser currentUser) {
        API_BASE_URL = api_base_url;
        this.currentUser = currentUser;
    }

    public Transfer[] getAllTransfers() {
        Transfer[] transfers = null;
//        try{
            transfers = restTemplate.exchange(API_BASE_URL + "/transfers/getalltransfers/"
                    + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
//        } catch (RestClientResponseException ex) {
//            System.out.println("Error");
//        } catch (ResourceAccessException ex) {
//            System.out.println("Also Error");
//        }
        return transfers;

    }


    public  Transfer getTransferById(long transferID) {
        Transfer transfer = null;
        try{
            ResponseEntity<Transfer> response= restTemplate.exchange(API_BASE_URL +
                            "/transfers/gettransfer/" + transferID, HttpMethod.GET, makeAuthEntity(),
                    Transfer.class);
            transfer = response.getBody();
        }catch (RestClientResponseException ex) {
            System.out.println("Error");
        } catch (ResourceAccessException ex) {
            System.out.println("Error Also");
        }
        return transfer;
    }

    public void sendBucks() {
        User[] users = null;
        Transfer transfer = new Transfer();
        try {
            Scanner scanner = new Scanner(System.in);
            users = restTemplate.exchange(API_BASE_URL + "listusers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
            System.out.println("-------------------------------------------\r\n" +
                    "Users\r\n" +
                    "ID\t\tName\r\n" +
                    "-------------------------------------------");
            for (User i : users) {
                if (i.getId() != currentUser.getUser().getId()) {
                    System.out.println(i.getId() + "\t\t" + i.getUsername());
                }
            }
            System.out.print("-------------------------------------------\r\n" +
                    "Enter ID of user you are sending to (0 to cancel): ");
            transfer.setAccountTo(Integer.parseInt(scanner.nextLine()));
            transfer.setAccountFrom(currentUser.getUser().getId());
            if (transfer.getAccountTo() != 0) {
                System.out.print("Enter amount: ");
                try {
                    transfer.setAmount(new BigDecimal(Double.parseDouble(scanner.nextLine())));
                } catch (NumberFormatException e) {
                    System.out.println("Error when entering amount");
                }
                String output = restTemplate.exchange(API_BASE_URL + "/transfers/sending/", HttpMethod.POST, makeTransferEntity(transfer), String.class).getBody();
                System.out.println(output);
            }
        } catch (Exception e) {
            System.out.println("Bad input.");
        }
    }
        public void requestBucks() {
            User[] users = null;
            Transfer transfer = new Transfer();
            try {
                Scanner scanner = new Scanner(System.in);
                users = restTemplate.exchange(API_BASE_URL + "listusers", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
                System.out.println("-------------------------------------------\r\n" +
                        "Users\r\n" +
                        "ID\t\tName\r\n" +
                        "-------------------------------------------");
                for (User i : users) {
                    if (i.getId() != currentUser.getUser().getId()) {
                        System.out.println(i.getId() + "\t\t" + i.getUsername());
                    }
                }
                System.out.print("-------------------------------------------\r\n" +
                        "Enter ID of user you are requesting from (0 to cancel): ");
                transfer.setAccountTo(currentUser.getUser().getId());
                transfer.setAccountFrom(Integer.parseInt(scanner.nextLine()));
                if (transfer.getAccountTo() != 0) {
                    System.out.print("Enter amount: ");
                    try {
                        transfer.setAmount(new BigDecimal(Double.parseDouble(scanner.nextLine())));
                    } catch (NumberFormatException e) {
                        System.out.println("Error when entering amount");
                    }
                    String output = restTemplate.exchange(API_BASE_URL + "/transfers/receiving/", HttpMethod.POST, makeTransferEntity(transfer), String.class).getBody();
                    System.out.println(output);
                }
            } catch (Exception e) {
                System.out.println("Bad input.");
            }
        }



    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        return entity;
    }
    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        return new HttpEntity<>(headers);
    }
}