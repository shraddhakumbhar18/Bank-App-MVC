package com.capgemini.bankapp.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.capgemini.bankapp.model.BankAccount;

public class BankMapper implements RowMapper<BankAccount>
{
	public BankAccount mapRow(ResultSet rs ,int rowNum) throws SQLException
	{
		long accountId = rs.getLong(1);
		String accountHolderName = rs.getString(2);
		String accountType = rs.getString(3);
		Double accountBalance = rs.getDouble(4);
		BankAccount account = new BankAccount(accountId, accountHolderName, accountType, accountBalance);
		return account;
	}
}
