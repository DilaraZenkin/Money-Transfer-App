package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;

@Component
public interface AccountDao {


    Account getAccountById (long accountID);
    BigDecimal getBalance(long accountID);
    BigDecimal increaseBalance(BigDecimal balance, long accountID);
    BigDecimal decreaseBalance(BigDecimal subtractMoney,long accountID);
}
