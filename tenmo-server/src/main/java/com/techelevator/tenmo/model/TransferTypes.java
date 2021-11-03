package com.techelevator.tenmo.model;

public class TransferTypes {
    private long transferTypeID;
    private String transferTypeDesc;

    public TransferTypes(long transferTypeID, String transferTypeDesc) {
        this.transferTypeID = transferTypeID;
        this.transferTypeDesc = transferTypeDesc;
    }

    public TransferTypes() {
    }

    public long getTransferTypeID() {
        return transferTypeID;
    }

    public void setTransferTypeID(long transferTypeID) {
        this.transferTypeID = transferTypeID;
    }

    public String getTransferTypeDesc() {
        return transferTypeDesc;
    }

    public void setTransferTypeDesc(String transferTypeDesc) {
        this.transferTypeDesc = transferTypeDesc;
    }

    @Override
    public String toString() {
        return "TransferTypes{" +
                "transferTypeID=" + transferTypeID +
                ", transferTypeDesc='" + transferTypeDesc + '\'' +
                '}';
    }
}
