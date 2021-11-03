package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    Account getBalance(long accountID);

    Account increaseBalance(long accountID);

    Account decreaseBalance(long accountID);
}
