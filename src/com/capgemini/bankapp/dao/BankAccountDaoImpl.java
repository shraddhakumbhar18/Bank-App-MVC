package com.capgemini.bankapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.exception.BankAccountNotFoundException;
import com.capgemini.bankapp.mapper.BankMapper;
import com.capgemini.bankapp.model.BankAccount;

@Repository
public class BankAccountDaoImpl implements BankAccountDao
{
	@Autowired
	public JdbcTemplate jdbcTemplate;
	

	public double getBalance(long accountId) 
	{
		String query = "SELECT account_balance FROM bankaccount WHERE account_id= ?";
		double balance = -1;
		try  
		{
			 balance = jdbcTemplate.queryForObject(query ,new Object[] {accountId} , Double.class);	
		} 
		catch (Exception e) 
		{
			System.out.println("Low balance exception");
		}
                return balance;
	}


	public void updateBalance(long accountId, double newBalance) {
		String query = "UPDATE bankaccount SET account_balance='"+newBalance+"' WHERE account_id='"+accountId+"' ";
		int result = jdbcTemplate.update(query);

	}
	public boolean addNewBankAccount(BankAccount account) {

		String query = "INSERT INTO bankaccount(customer_name,account_type,account_balance) VALUES ('"+account.getAccountHolderName()+"','"+account.getAccountType()+"','"+account.getAccountBalance()+"')";
		int result=jdbcTemplate.update(query); 
		if(result==1)
		{
                  return true;
		}
                else
		{
                 return false;
		}
		
	}

	public boolean updateAccountDetails(long accountId, String accountHolderName, String accountType){
		String query = "UPDATE bankaccount SET customer_name='"+accountHolderName+"' ,account_type='"+accountType+"' WHERE account_id='"+accountId+"'";
		int result = jdbcTemplate.update(query);
		if(result == 1) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean deleteBankAccount(long accountId) {
		String query = "DELETE FROM bankaccount WHERE account_id=" +accountId ;
		int result = jdbcTemplate.update(query);
		if(result == 1) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public List<BankAccount> findAllBankAccounts() {
		String query = "SELECT * FROM bankaccount";
		List<BankAccount> accounts = jdbcTemplate.query(query,new BankMapper());
		return accounts;
	}

	public BankAccount searchAccount(long accountId) throws BankAccountNotFoundException{
	BankAccount details=null;
	try
	{
		String query = "SELECT * FROM bankaccount WHERE account_id=?";
		details=jdbcTemplate.queryForObject(query ,new Object[] {accountId} , (resultSet,rowNum)->{
		long accountId1 = resultSet.getLong(1);
		String accountHolderName = resultSet.getString(2);
		String accountType = resultSet.getString(3);
		double accountBalance = resultSet.getDouble(4);
		BankAccount account= new BankAccount(accountId1, accountHolderName, accountType, accountBalance);
		return account;
		});	
		
	}
	catch(EmptyResultDataAccessException ex)
	{	
		BankAccountNotFoundException re = new BankAccountNotFoundException("Bank Account Not Found");
		ex.initCause(re);
		throw re;
	}
	return details;
}

	
}
