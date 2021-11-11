package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.List;

@Component
public interface AccountDao {

    Account getAccountById(long accountId);
    List<Account> getAccountsByUserId (long userID);
    BigDecimal getBalance(long userID);
    BigDecimal increaseBalance(BigDecimal amountToAdd, long userID);
    BigDecimal decreaseBalance(BigDecimal subtractMoney,long userID);
}
