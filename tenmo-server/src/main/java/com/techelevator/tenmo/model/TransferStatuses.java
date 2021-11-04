package com.techelevator.tenmo.model;

public class TransferStatuses {
    private long transferStatusID;
    private String transferStatusDesc;

    public TransferStatuses(long transferStatusID, String transferStatusDesc) {
        this.transferStatusID = transferStatusID;
        this.transferStatusDesc = transferStatusDesc;
    }

    public TransferStatuses() {
    }

    public long getTransferStatusID() {
        return transferStatusID;
    }

    public void setTransferStatusID(long transferStatusID) {
        this.transferStatusID = transferStatusID;
    }

    public String getTransferStatusDesc() {
        return transferStatusDesc;
    }

    public void setTransferStatusDesc(String transferStatusDesc) {
        this.transferStatusDesc = transferStatusDesc;
    }

    @Override
    public String toString() {
        return "TransferStatuses{" +
                "transferStatusID=" + transferStatusID +
                ", transferStatusDesc='" + transferStatusDesc + '\'' +
                '}';
    }
}
