package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {


    Account getAccountById (long accountID);
    BigDecimal getBalance(long accountID);
    BigDecimal increaseBalance(BigDecimal addMoney, long accountID);
    BigDecimal decreaseBalance(BigDecimal subtractMoney,long accountID);
}
