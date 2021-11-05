package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TransferController {

    @Autowired
    private TransferDao dao;

    public TransferController(TransferDao dao) {
        this.dao = dao;
    }

    @RequestMapping( path = "/transfers/{id}", method = RequestMethod.GET)
    public List<Transfer> lists(@PathVariable int id) {
        List<Transfer> results = dao.getAllTransfers(id);

        return results;
    }

    @RequestMapping( path = "/transfers/{id}", method = RequestMethod.GET)
    public Transfer getTransfer(@PathVariable int id) {
        Transfer transfer = dao.getTransferById(id);
        return transfer;
    }


    @RequestMapping( path = "/transfers/{id}", method = RequestMethod.PUT)
    public int update(@PathVariable long id, @RequestBody BigDecimal amount){
        return dao.sendingMoneyTo(id, amount);
    }

    @RequestMapping( path = "/transfers/{id}", method = RequestMethod.PUT)
    public int update2(@PathVariable long id, @RequestBody BigDecimal amount){
        return dao.receivingMoneyFrom(id, amount);
    }

    @RequestMapping( path = "/transfers/{id}", method = RequestMethod.GET)
    public List<Transfer> list (@PathVariable long id) {
        List<Transfer> results = dao.pendingRequests(id);

        return results;
    }

}
