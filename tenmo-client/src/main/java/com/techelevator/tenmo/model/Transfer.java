package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {

    private long transferID;
    private long transferTypeID;
    private long transferStatusID;
    private long accountFrom;
    private long accountTo;
    private BigDecimal amount;
    private String transferTypeId;
    private String transferStatusId;
    private String accountFroms;
    private String accountTos;

//    public Transfer(long transferID, long transferTypeID, long transferStatusID, long accountFrom, long accountTo, BigDecimal amount) {
//        this.transferID = transferID;
//        this.transferTypeID = transferTypeID;
//        this.transferStatusID = transferStatusID;
//        this.accountFrom = accountFrom;
//        this.accountTo = accountTo;
//        this.amount = amount;
//    }
//
//    public Transfer() {
//    }
//
//    public long getTransferID() {
//        return transferID;
//    }
//
//    public void setTransferID(long transferID) {
//        this.transferID = transferID;
//    }
//
//    public long getTransferTypeID() {
//        return transferTypeID;
//    }
//
//    public void setTransferTypeID(long transferTypeID) {
//        this.transferTypeID = transferTypeID;
//    }
//
//    public long getTransferStatusID() {
//        return transferStatusID;
//    }
//
//    public void setTransferStatusID(long transferStatusID) {
//        this.transferStatusID = transferStatusID;
//    }
//
//    public long getAccountFrom() {
//        return accountFrom;
//    }
//
//    public void setAccountFrom(long accountFrom) {
//        this.accountFrom = accountFrom;
//    }
//
//    public long getAccountTo() {
//        return accountTo;
//    }
//
//    public void setAccountTo(long accountTo) {
//        this.accountTo = accountTo;
//    }
//
//    public BigDecimal getAmount() {
//        return amount;
//    }
//
//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }
//
//    @Override
//    public String toString() {
//        return "Transfer{" +
//                "transferID=" + transferID +
//                ", transferTypeID=" + transferTypeID +
//                ", transferStatusID=" + transferStatusID +
//                ", accountFrom=" + accountFrom +
//                ", accountTo=" + accountTo +
//                ", amount=" + amount +
//                '}';
//    }

    //  TESTING NEW WAY ------------------------------------------------------------


//    public Transfer(long transferID, long transferTypeID, long transferStatusID, long accountFrom, long accountTo, BigDecimal amount, String transferId, String transferTypeId, String transferStatusId, String accountFroms, String accountTos) {
//        this.transferID = transferID;
//        this.transferTypeID = transferTypeID;
//        this.transferStatusID = transferStatusID;
//        this.accountFrom = accountFrom;
//        this.accountTo = accountTo;
//        this.amount = amount;
//        this.transferId = transferId;
//        this.transferTypeId = transferTypeId;
//        this.transferStatusId = transferStatusId;
//        this.accountFroms = accountFroms;
//        this.accountTos = accountTos;
//    }

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

    public String getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(String transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public String getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(String transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public String getAccountFroms() {
        return accountFroms;
    }

    public void setAccountFroms(String accountFroms) {
        this.accountFroms = accountFroms;
    }

    public String getAccountTos() {
        return accountTos;
    }

    public void setAccountTos(String accountTos) {
        this.accountTos = accountTos;
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
                ", transferId='" + transferID + '\'' +
                ", transferTypeId='" + transferTypeId + '\'' +
                ", transferStatusId='" + transferStatusId + '\'' +
                ", accountFroms='" + accountFroms + '\'' +
                ", accountTos='" + accountTos + '\'' +
                '}';
    }
}
