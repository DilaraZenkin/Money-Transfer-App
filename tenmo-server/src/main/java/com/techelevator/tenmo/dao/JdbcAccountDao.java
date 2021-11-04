package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;

public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getBalance(long accountID) {
       Account account = null;
       String sql = "SELECT account_id, user_id, balance " +
               "FROM accounts " +
               "WHERE account_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountID);
        if (results.next()) {
            account = mapRowToAccount(results);

        }
        return account;
    }

    @Override
    public Account increaseBalance(long accountID) {
        return null;
    }

    @Override
    public Account decreaseBalance(long accountID) {
        return null;
    }

    private Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountID(rowSet.getLong("account_id"));
        account.setUserID(rowSet.getLong("user_id"));
        account.setBalance(rowSet.getBigDecimal("balance"));
        return account;
    }

}