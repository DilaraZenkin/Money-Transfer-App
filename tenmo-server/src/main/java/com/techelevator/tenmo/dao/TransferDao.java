package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
        // (Corresponds to 5 in README -- can see transfers sent or received)
    List<Transfer> getAllTransfers();

        // (Corresponds to 6 in README -- retrieve details on any transfer with transferID)
    long getTransferByID();

        // (Corresponds to 4 in README -- send a transfer with amount TO user)
    long accountTo();

        // receive a transfer FROM user
    long accountFrom();

        // amount to transfer
    BigDecimal amount();

}
