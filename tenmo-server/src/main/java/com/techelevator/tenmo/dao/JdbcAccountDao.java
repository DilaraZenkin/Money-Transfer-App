package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccountById(long accountId) {
        Account account = null;
        String sql = "SELECT * FROM accounts " + "WHERE account_id = ?;";


        SqlRowSet results = null;
        results = jdbcTemplate.queryForRowSet(sql, accountId);
        if (results.next()) {
            account = mapRowToAccount(results);
        }

        return account;

    }

    @Override
    public BigDecimal getBalance(long accountID) {
        BigDecimal balance = null;
        String sql = "SELECT balance " +
                "FROM accounts " +
                "WHERE account_id = ?;";

        SqlRowSet results = null;
        results = jdbcTemplate.queryForRowSet(sql, accountID);
        if (results.next()) {
            balance = results.getBigDecimal("balance");
        }

        return balance;
    }

    public BigDecimal increaseBalance(BigDecimal addMoney, long accountId) {
        Account account = getAccountById(accountId);
        BigDecimal newBalance = account.getBalance().add(addMoney);
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

