package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfer> getAllTransfers(long accountID) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount " +
        "FROM transfers " +
        "JOIN accounts ON transfers.account_from = accounts.account_id " +
        "WHERE account_to = account_id OR account_from = account_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Transfer getAllTransfers = mapRowToTransfer(results);
            transfers.add(getAllTransfers);

        }
        return transfers;
    }

    @Override
    public Transfer getTransferByID(long transferID) {
        Transfer transfer = null;
        String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount" +
                "FROM transfer" +
                "WHERE transfer_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferID);
        if(results.next()) {
            transfer = mapRowToTransfer(results);
        } else {
//            throw new TransferNotFoundException();
        }
        return transfer;
    }

    @Override
    public long accountTo() {
        return 0;
    }

    @Override
    public long accountFrom() {
        return 0;
    }

    @Override
    public BigDecimal amount() {
        return null;
    }

    private Transfer mapRowToTransfer(SqlRowSet rowSet) {
        Transfer transfer = new Transfer();
        transfer.setTransferID(rowSet.getLong("transfer_id"));
        transfer.setTransferTypeID(rowSet.getLong("transfer_type_id"));
        transfer.setTransferStatusID(rowSet.getLong("transfer_status_id"));
        transfer.setAccountFrom(rowSet.getLong("account_from"));
        transfer.setAccountTo(rowSet.getLong("account_to"));
        transfer.setAmount(rowSet.getBigDecimal("amount"));
        return transfer;
    }
}
