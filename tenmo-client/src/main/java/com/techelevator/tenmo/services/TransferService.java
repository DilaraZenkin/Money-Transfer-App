package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class TransferService {
    private final String API_BASE_URL;
    private final AuthenticatedUser console;
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;

    public TransferService(String api_base_url, AuthenticatedUser console) {
        API_BASE_URL = api_base_url;
        this.console = console;
    }

    public Transfer[] getAllTransfers(long AccountID) {
        Transfer[] transfers = null;
        try{
            transfers = restTemplate.getForObject(API_BASE_URL + "/transfers/getalltransfers/"
                    + AccountID, Transfer[].class);
        } catch (RestClientResponseException ex) {
            //console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            //console.printError(ex.getMessage());
        }
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
           // console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
           // console.printError(ex.getMessage());
        }
        return transfer;
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