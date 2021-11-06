package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TransferController {

    @Autowired
    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }
    // works in Postman
    @RequestMapping( path = "/transfers/getalltransfers/{id}", method = RequestMethod.GET)
    public List<Transfer> lists(@PathVariable int id) {
        List<Transfer> results = transferDao.getAllTransfers(id);

        return results;
    }
    // 200 OK in Postman but no data shown
    @RequestMapping( path = "/transfers/gettransfer/{id}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int id) {
        Transfer transfer = transferDao.getTransferById(id);
        return transfer;
    }


    @RequestMapping( path = "/transfers/sending/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Transfer transfer){
       // return transferDao.sendingMoneyTo(id, amount);
        transferDao.sendingMoneyTo(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
    }

    @RequestMapping( path = "/transfers/receiving/{id}", method = RequestMethod.PUT)
    public void update2(@RequestBody Transfer transfer){
        transferDao.receivingMoneyFrom(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
    }

    @RequestMapping( path = "/transfers/pending/{id}", method = RequestMethod.GET)
    public List<Transfer> list (@PathVariable long id) {
        List<Transfer> results = transferDao.pendingRequests(id);

        return results;
    }

}
