package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {


private JdbcTemplate jdbcTemplate;
    @Autowired
    private AccountDao accountDAO;

public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}


    @Override
    public List<Transfer> getAllTransfers(long accountID) {
    List<Transfer> transferList = new ArrayList<>();
    String sql = "SELECT transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount "
            + "FROM transfers " + "JOIN accounts ON transfers.account_from = accounts.account.id " +
            "WHERE account_to = account_id OR account_from = account_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Transfer getAllTransfers = mapRowToTransfer(results);
            transferList.add(getAllTransfers);
        }
        return transferList;
    }

    @Override
    public Transfer getTransferById(long transferID) {

        Transfer transfer = null;
        String sql = " SELECT t.transfer_id, s.username AS sender, r.username AS receiver, tt.transfer_type_desc, ts.transfer_status_desc, t.amount " +
                " FROM transfers t " +
                " JOIN transfer_types tt ON t.transfer_type_id = tt.transfer_type_id " +
                " JOIN transfer_statuses ts ON t.transfer_status_id = ts.transfer_status_id " +
                " JOIN accounts a ON a.account_id = t.account_from " +
                " JOIN users r ON  a.account_id = r.user_id " +
                " JOIN accounts b ON b.account_id = t.account_to " +
                " JOIN users s ON b.account_id = s.user_id " +
                " WHERE transfer_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferID);
        if(results.next()) {
            transfer = mapRowToTransfer(results);
        } else {
//            throw new TransferNotFoundException();
        }
        return transfer;
    }
    @Override
    public int sendingMoneyTo(long accountFrom, long accountTo, BigDecimal amount) {
///        Transfer account = null;
        String sql = "INSERT INTO transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (2, 1, ?, ?, ?);";

        //return jdbcTemplate.update(sql, userID, amount);
        return jdbcTemplate.update(sql, accountFrom, accountTo, amount);
    }
    @Override
    public int receivingMoneyFrom(long accountFrom, long accountTo, BigDecimal amount) {
        Transfer transfer = null;
        String sql = "INSERT INTO transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                "VALUES (1, 1, ?, ?, ?);";

        return jdbcTemplate.update(sql, accountFrom, accountTo, amount);
    }

    @Override
    public List<Transfer> pendingRequests(long transferID) {
        List<Transfer> requests = new ArrayList<>();
        String sql = "SELECT t.transfer_id, r.username AS receiver, ts.transfer_status_desc, t.amount " +
                "FROM transfers t " +
                "JOIN transfer_statuses ts ON t.transfer_status_id = ts.transfer_status_id " +
                "JOIN accounts a ON a.account_id = t.account_from " +
                "JOIN users r ON  a.account_id = r.user_id " +
                "WHERE ts.transfer_status_id = 1 AND t.transfer_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Transfer getAllPendingRequests = mapRowToTransfer(results);
            requests.add(getAllPendingRequests);
        }
        return requests;
    }

//    @Override
//public Transfer sendingMoneyTo(long userID, BigDecimal amount) {
//return null;
//}
//    @Override
//    public Transfer receivingMoneyFrom(long userID, BigDecimal amount) {
//return null;
//    }


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
