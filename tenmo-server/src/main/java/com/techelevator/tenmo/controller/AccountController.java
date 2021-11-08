package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;


import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;


@RestController
    @PreAuthorize("permitAll")
    public class AccountController {


        private AccountDao accountDao;
        private UserDao userDao;

        public AccountController(AccountDao accountDao, UserDao userDao) {
            this.accountDao = accountDao;
            this.userDao = userDao;
        }

        @RequestMapping(path = "/accounts/{id}/owner", method = RequestMethod.GET)
        public User getUserByAccountId(@PathVariable int id) {
            return userDao.getUserByAccountId(id);
        }

        @RequestMapping(path = "/accounts/by-user/{userId}", method = RequestMethod.GET)
        public List<Account> getAccountsByUserId(@PathVariable int userId) {
            return accountDao.getAccountsByUserId(userId);
        }

        @RequestMapping( path = "/accounts/{id}", method = RequestMethod.GET)
        public Account getAccountByID(@PathVariable int id) {
            return accountDao.getAccountById(id);

        }


            //    BigDecimal getBalance(long accountID);
            @RequestMapping(path = "/accounts/balance/{id}", method = RequestMethod.GET)
            public BigDecimal getBalance ( @PathVariable int id){
                BigDecimal balance = accountDao.getBalance(id);
                return balance;
            }
            //  BigDecimal increaseBalance(BigDecimal addMoney, long accountID);
            @RequestMapping(path = "/accounts/balance/increase/{id}", method = RequestMethod.PUT)

            public BigDecimal update (@RequestBody BigDecimal addMoney,@PathVariable int id) {
                return accountDao.increaseBalance(addMoney, id);
            }

    @RequestMapping(path = "/accounts/balance/decrease/{id}", method = RequestMethod.PUT)
    public BigDecimal update2 (@RequestBody BigDecimal subtractMoney,@PathVariable int id){
        return accountDao.decreaseBalance(subtractMoney, id);
    }
    @RequestMapping(path = "listusers", method = RequestMethod.GET)
    public List <User> listUsers() {
        List <User> users = userDao.findAll();
        return users;
    }

            }



