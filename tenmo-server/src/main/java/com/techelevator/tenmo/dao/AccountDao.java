package com.techelevator.tenmo.dao;

<<<<<<< HEAD
public interface AccountDao {


=======
import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    Account getBalance(long accountID);

    Account increaseBalance(long accountID);

    Account decreaseBalance(long accountID);
}
>>>>>>> 4a9af62f6a76853d626e05d56bd27705d4fea1d8
