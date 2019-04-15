package com.capgemini.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.bankapp.dao.BankAccountDao;
import com.capgemini.bankapp.exception.BankAccountNotFoundException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;

@Service
public class BankAccountServiceImpl implements BankAccountService
{
	@Autowired
	 private BankAccountDao bankAccountDao;
		// static final Logger logger = Logger.getLogger(BankAccountServiceImpl.class);

	
		public double checkBalance(long accountId) throws BankAccountNotFoundException {

			double balance = bankAccountDao.getBalance(accountId);
			if (balance >= 0)
				return balance;
			throw new BankAccountNotFoundException("BankAccount doesn't exist..");
		}

		public double withdraw(long accountId, double amount) throws LowBalanceException, BankAccountNotFoundException {
			double balance = bankAccountDao.getBalance(accountId);
			if (balance < 0)
				throw new BankAccountNotFoundException("Bank Account doesn't exist..");
			else if (balance - amount >= 0) {
				balance = balance - amount;
				bankAccountDao.updateBalance(accountId, balance);
				return balance;
			} else {
				throw new LowBalanceException("You don't have sufficient fund");
			}
		}

		public double withdrawForFundTransfer(long accountId, double amount)
				throws LowBalanceException, BankAccountNotFoundException {
			double balance = bankAccountDao.getBalance(accountId);
			if (balance < 0)
				throw new BankAccountNotFoundException("Bank Account doesn't exist..");
			else if (balance - amount >= 0) {
				balance = balance - amount;
				bankAccountDao.updateBalance(accountId, balance);
				return balance;
			} else {
				throw new LowBalanceException("You don't have sufficient fund");
			}
		}

		public double deposit(long accountId, double amount) throws BankAccountNotFoundException {
			double balance = bankAccountDao.getBalance(accountId);
			if (balance < 0)
				throw new BankAccountNotFoundException("Bank Account doesn't exist");
			balance = balance + amount;
			bankAccountDao.updateBalance(accountId, balance);
			return balance;
		}

		public boolean addNewBankAccount(BankAccount account) {
			boolean result = bankAccountDao.addNewBankAccount(account);
			return result;
		}

		public boolean deleteBankAccount(long accountId) throws BankAccountNotFoundException {
			boolean result = bankAccountDao.deleteBankAccount(accountId);
			return result;
			//throw new BankAccountNotFoundException("Bank Account doesn't exist.");
		}
		public List<BankAccount> findAllBankAccounts() {
			return bankAccountDao.findAllBankAccounts();
		}
		public boolean updateAccount(long accountId, String accountHolderName, String accountType) throws BankAccountNotFoundException{
			boolean result = bankAccountDao.updateAccountDetails(accountId, accountHolderName, accountType);
			return result;
		}
		
		
		public BankAccount searchAccount(long accountId) throws BankAccountNotFoundException {
			BankAccount account = bankAccountDao.searchAccount(accountId);
			if (account == null)
				throw new BankAccountNotFoundException("Bank Account doesn't exist");
			return account;
		}
		
		@Transactional(rollbackFor=BankAccountNotFoundException.class)
		public double fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException, BankAccountNotFoundException 
		{
			try 
			{
				double newBalance = withdrawForFundTransfer(fromAccount, amount);
				deposit(toAccount, amount);
				//bankAccountDao.commit();
				return newBalance;
			} 
			catch (LowBalanceException  e) 
			{
				//bankAccountDao.rollback();
				// logger.error("Exception", e);
				throw e;
			}
			catch (BankAccountNotFoundException e) 
			{
				throw e;
			}
		}

		
}
