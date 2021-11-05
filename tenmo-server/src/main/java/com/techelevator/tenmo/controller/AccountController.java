package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import java.math.BigDecimal;



    @RestController
    @PreAuthorize("isAuthenticated()")
    public class AccountController {


        private AccountDao accountDao;

        public AccountController(AccountDao accountDao) {
            this.accountDao = accountDao;
        }

        //    BigDecimal getBalance(long accountID);
        @RequestMapping( path = "/balance/{id}", method = RequestMethod.GET)
        public BigDecimal getBalance(@PathVariable int id) {
            BigDecimal balance = accountDao.getBalance(id);
            return balance;
        }
        //  BigDecimal increaseBalance(BigDecimal addMoney, long accountID);
        @RequestMapping(path = "/balance/increase/{id}", method = RequestMethod.PUT)
        public BigDecimal update(@RequestBody BigDecimal addMoney, @PathVariable int id) {
            return accountDao.increaseBalance(addMoney, id);
        }
        //   BigDecimal decreaseBalance(BigDecimal subtractMoney,long accountID);
        @RequestMapping(path = "/balance/decrease/{id}", method = RequestMethod.PUT)
        public BigDecimal update2(@RequestBody BigDecimal subtractMoney, @PathVariable int id) {
            return accountDao.decreaseBalance(subtractMoney, id);
        }
    }


