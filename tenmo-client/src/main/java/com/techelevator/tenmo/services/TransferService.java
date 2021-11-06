package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.view.ConsoleService;
import org.springframework.web.client.RestTemplate;
import com.techelevator.tenmo.model.Account;
import com.techelevator.view.ConsoleService;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.math.BigDecimal;
import java.util.List;


public class TransferService {

    private final String API_BASE_URL;
    private final ConsoleService console;
    private RestTemplate restTemplate = new RestTemplate();

    public TransferService(String apiURL, ConsoleService consoleService) {
        API_BASE_URL = apiURL;
        this.console = consoleService;
    }
    public Transfer[] getAllTransfers(long AccountID) {
        Transfer[] transfers = null;
        try {
            transfers = restTemplate.getForObject(API_BASE_URL + "/transfers/getalltransfers/{id}", Transfer[].class);
        } catch (RestClientResponseException ex) {
            console.printError(ex.getRawStatusCode() + " : " + ex.getStatusText());
        } catch (ResourceAccessException ex) {
            console.printError(ex.getMessage());
        }
        return transfers;
    }
}
