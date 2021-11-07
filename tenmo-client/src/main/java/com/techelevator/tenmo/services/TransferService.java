package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class TransferService {
    private final String API_BASE_URL;
    private final Account console;
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;

    public TransferService(String api_base_url, Account console) {
        API_BASE_URL = api_base_url;
        this.console = console;
    }

    public Transfer[] getAllTransfers() {
        Transfer[] transfers = null;
        try{
            transfers = restTemplate.getForObject(API_BASE_URL + "/transfers/getalltransfers/"
            + console.getAccountID(), Transfer[].class);
        } catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return transfers;
        }



    public  Transfer getTransferById() {
        Transfer transfer = null;
        try{
            ResponseEntity<Transfer> response= restTemplate.exchange(API_BASE_URL +
                    "/transfers/gettransfer/" + console.getAccountID(), HttpMethod.GET, makeAuthEntity(),
                    Transfer.class);
            transfer = response.getBody();
        }catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return transfer;
    }

    public boolean sendingMoneyTo(Transfer transfer) {
        boolean success = false;
        String url = API_BASE_URL + "/transfers/sending/" + transfer.getTransferID();
        try {
            restTemplate.put(url,makeEntity(transfer));
            success = true;
        }catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return success;

    }

    public boolean receivingMoneyFrom(Transfer transfer) {
        boolean success = false;
        String url = API_BASE_URL + "/transfers/receiving/" + transfer.getTransferID();
        try {
            restTemplate.put(url,makeEntity(transfer));
            success = true;
        }catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return success;

    }


    private HttpEntity<Transfer> makeEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(transfer, headers);
    }
    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
