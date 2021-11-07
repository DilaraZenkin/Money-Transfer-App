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
@PreAuthorize("isAuthenticated()")
public class TransferController {

    @Autowired
    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }

    @RequestMapping(path = "/transfers/getalltransfers/{id}", method = RequestMethod.GET)
    public List<Transfer> lists(@PathVariable int id) {
        List<Transfer> results = transferDao.getAllTransfers(id);

        return results;
    }

    @RequestMapping(path = "/transfers/gettransfer/{id}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int id) {
        Transfer transfer = transferDao.getTransferById(id);
        return transfer;
    }


    @RequestMapping(path = "/transfers/sending/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Transfer transfer) {
        // return transferDao.sendingMoneyTo(id, amount);
        transferDao.sendingMoneyTo(transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());
    }

    @RequestMapping(path = "/transfers/receiving/{id}", method = RequestMethod.PUT)
    public int update2(@PathVariable long accountFrom, @PathVariable long accountTo, @RequestBody BigDecimal amount) {
        return transferDao.receivingMoneyFrom(accountFrom, accountTo, amount);
    }

//    @RequestMapping( path = "/transfers/pending/{id}", method = RequestMethod.GET)
//    public List<Transfer> list (@PathVariable long id) {
//        List<Transfer> results = transferDao.pendingRequests(id);
//
//        return results;
//    }

    }

