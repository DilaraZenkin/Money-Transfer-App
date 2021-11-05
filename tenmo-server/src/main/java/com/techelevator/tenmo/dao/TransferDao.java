package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {
        // (Corresponds to 5 in README -- can see transfers sent or received)
     List<Transfer> getAllTransfers(long accountID);
    Transfer getTransferById(long transferID);

        // (Corresponds to 6 in README -- retrieve details on any transfer with transferID)
    //long getTransferByID();

    //(Corresponds to 4 in README -- send a transfer with amount TO user)
    Transfer sendingMoneyTo(long userID, BigDecimal amount );

        // receive a transfer FROM user (Use case-7)
    Transfer receivingMoneyFrom(long userID, BigDecimal amount);

        // amount to transfer
   // BigDecimal amount();

}
