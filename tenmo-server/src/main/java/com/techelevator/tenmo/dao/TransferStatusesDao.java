package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.TransferStatuses;

public interface TransferStatusesDao {

    TransferStatuses getTransferStatus(long transferTypeID);

}
