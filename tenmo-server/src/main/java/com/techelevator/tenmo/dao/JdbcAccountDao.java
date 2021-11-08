package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> getAccountsByUserId(long userID) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts " +
                "WHERE user_id = ?;";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userID);
        while (results.next()) {
            accounts.add(mapRowToAccount(results));
        }
        return accounts;
    }

    @Override
    public Account getAccountById(long accountId) {
        String sql = "SELECT * FROM accounts " +
                "WHERE account_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);

        Account account = null;
        if(results.next()) {
            account = mapRowToAccount(results);
        }
        return account;
    }

    @Override
    public BigDecimal getBalance(long userID) {
        BigDecimal balance = null;
        String sql = "SELECT balance " +
                "FROM accounts " +
                "WHERE user_id = ?;";

        SqlRowSet results = null;
        results = jdbcTemplate.queryForRowSet(sql, userID);
        if (results.next()) {
            balance = results.getBigDecimal("balance");
        }

        return balance;
    }

    public BigDecimal increaseBalance(BigDecimal balance, long accountId) {
        Account account = getAccountById(accountId);
        BigDecimal newBalance = account.getBalance().add(balance);
        String sql = "UPDATE accounts SET balance = ? "
                + "WHERE account_id = ?;";
        jdbcTemplate.update(sql, newBalance, accountId);
        return account.getBalance();
    }


    @Override
    public BigDecimal decreaseBalance(BigDecimal subtractMoney,long accountID) {
        Account account = getAccountById(accountID);
        BigDecimal newBalance = account.getBalance().subtract(subtractMoney);
        String sql = "UPDATE accounts SET balance = ? "
                + "WHERE account_id = ?;";
        jdbcTemplate.update(sql, newBalance, accountID);
        return account.getBalance();
    }


    private Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountID(rowSet.getLong("account_id"));
        account.setUserID(rowSet.getLong("user_id"));
        account.setBalance(rowSet.getBigDecimal("balance"));
        return account;
    }

}

