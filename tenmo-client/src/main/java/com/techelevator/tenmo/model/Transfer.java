package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {

    private long transferID;
    private long transferTypeID;
    private long transferStatusID;
    private long accountFrom;
    private long accountTo;
    private BigDecimal amount;

    public Transfer(long transferID, long transferTypeID, long transferStatusID, long accountFrom, long accountTo, BigDecimal amount) {
        this.transferID = transferID;
        this.transferTypeID = transferTypeID;
        this.transferStatusID = transferStatusID;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.amount = amount;
    }

    public Transfer() {
    }

    public long getTransferID() {
        return transferID;
    }

    public void setTransferID(long transferID) {
        this.transferID = transferID;
    }

    public long getTransferTypeID() {
        return transferTypeID;
    }

    public void setTransferTypeID(long transferTypeID) {
        this.transferTypeID = transferTypeID;
    }

    public long getTransferStatusID() {
        return transferStatusID;
    }

    public void setTransferStatusID(long transferStatusID) {
        this.transferStatusID = transferStatusID;
    }

    public long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(long accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "transferID=" + transferID +
                ", transferTypeID=" + transferTypeID +
                ", transferStatusID=" + transferStatusID +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", amount=" + amount +
                '}';
    }
}
