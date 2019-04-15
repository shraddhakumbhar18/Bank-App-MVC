package com.capgemini.bankapp.dao;

import java.util.List;

import com.capgemini.bankapp.exception.BankAccountNotFoundException;
import com.capgemini.bankapp.model.BankAccount;

public interface BankAccountDao 
{
	public double getBalance(long accountId);
	public void updateBalance(long accountId,double newBalance);
	public boolean addNewBankAccount(BankAccount account);
	public boolean deleteBankAccount(long accountId);
	public List<BankAccount> findAllBankAccounts();
	public BankAccount searchAccount(long accountId) throws BankAccountNotFoundException;
	public boolean updateAccountDetails(long accountId,String accountHolderName, String accountType);
}
