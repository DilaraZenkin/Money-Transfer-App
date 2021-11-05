package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import java.math.BigDecimal;



    @RestController
    public class AccountController {

        @Autowired
        private AccountDao dao;

        public AccountController(AccountDao dao) {
            this.dao = dao;
        }

        //    BigDecimal getBalance(long accountID);
        @RequestMapping( path = "/balance/{id}", method = RequestMethod.GET)
        public BigDecimal getBalance(@PathVariable int id) {
            BigDecimal balance = dao.getBalance(id);
            return balance;
        }
        //  BigDecimal increaseBalance(BigDecimal addMoney, long accountID);
        @RequestMapping(path = "/balance/{id}", method = RequestMethod.PUT)
        public BigDecimal update(@RequestBody BigDecimal addMoney, @PathVariable int id) {
            return dao.increaseBalance(addMoney, id);
        }
        //   BigDecimal decreaseBalance(BigDecimal subtractMoney,long accountID);
        @RequestMapping(path = "/balance/{id}", method = RequestMethod.PUT)
        public BigDecimal update2(@RequestBody BigDecimal subtractMoney, @PathVariable int id) {
            return dao.decreaseBalance(subtractMoney, id);
        }

    }


