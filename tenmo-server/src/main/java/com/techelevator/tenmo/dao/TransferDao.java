package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
    // (Corresponds to 5 in README -- can see transfers sent or received)
    List<Transfer> getAllTransfers(long accountID);

    // (Corresponds to 6 in README -- retrieve details on any transfer with transferID)
    Transfer getTransferById(long transferID);

    //(Corresponds to 4 in README -- send a transfer with amount TO user)
    int sendingMoneyTo(long userID, BigDecimal amount );

    // receive a transfer FROM user (Use case-7)
    int receivingMoneyFrom(long userID, BigDecimal amount);

    // pending requests (Use case 8 & 9)
    List<Transfer> pendingRequests(long transferID);
}