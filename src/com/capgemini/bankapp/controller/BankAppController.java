package com.capgemini.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankapp.exception.BankAccountNotFoundException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;

@Controller
@RequestMapping("/BankAccount")
public class BankAppController 
{
	@Autowired
	private BankAccountService service;
	
	@RequestMapping("/home")
	public String homePage() 
	{
		return "index";
	}
	
	@RequestMapping("/home/new-acc")
	public String inputPage() 
	{
		return "new_acc";
	}
	
	@RequestMapping("/new")
	public String addNewBankAccount(@RequestParam("customer_name") String accountHolderName, 
			@RequestParam("account_type") String accountType, @RequestParam("account_balance") double accountBalance) 
	{
		
		service.addNewBankAccount(new BankAccount(accountHolderName,accountType,accountBalance));
		return "Success";

	}
	
	@RequestMapping("/home/Delete")
	public String deletePage() 
	{
		return "Delete";
	}
	
	@RequestMapping("/delete")
	@ExceptionHandler({BankAccountNotFoundException.class})
	public String deleteBankAccount(@RequestParam("account_id") long accountId) throws BankAccountNotFoundException 
	{
		boolean result = service.deleteBankAccount(accountId);
		if(result)
		{
			return "Success";
		}
		else
		{
			throw new BankAccountNotFoundException("Account Not Found");
		}
	}

	@RequestMapping("/home/Checkbal")
	public String checkBalancePage() 
	{
		return "Checkbal";
	}
	
	@RequestMapping("/Checkbal")
	public String checkBalance(@RequestParam("account_id") long accountId, Model model) 
	{
		try
		{
			double result = service.checkBalance(accountId);
			model.addAttribute("message", "Balance is:"+ result);
		}
		catch (BankAccountNotFoundException e) 
		{
			model.addAttribute("message", e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping("/home/withdrawn")
	public String withdrawPage() 
	{
		return "withdrawn";
	}
	
	@RequestMapping("/withdrawn")
	public String withdraw(@RequestParam("account_id") long accountId,@RequestParam("account_balance") double amount, Model model) 
	{
		try 
		{
			double result = service.withdraw(accountId,amount);
			model.addAttribute("message", "Amount withdraw successfully..!!");
		} 
		catch (LowBalanceException e) 
		{
			model.addAttribute("message", e.getMessage());
		} 
		catch (BankAccountNotFoundException e) 
		{
			model.addAttribute("message", e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping("/home/Deposite")
	public String depositePage() 
	{
		return "Deposite";
	}
	
	@RequestMapping("/Deposite")
	public String Deposite(@RequestParam("account_id") long accountId,@RequestParam("account_balance") double amount, Model model) 
	{
		try 
		{
			service.deposit(accountId, amount);
			model.addAttribute("message", "Amount Deposited successfully...!!");
		} 
		catch (BankAccountNotFoundException e) 
		{
			model.addAttribute("message", e.getMessage());
		}
		return "Success";
	}
	@RequestMapping("/home/Search")
	public String searchPage() 
	{
		return "Search";
	}
	
	@RequestMapping("/Search")
	public String search(@RequestParam("account_id") long accountId, Model model) 
	{
		try 
		{
			service.searchAccount(accountId);
			model.addAttribute("message", "Account searched successfully..!!");
		}
		catch (BankAccountNotFoundException e) 
		{
			model.addAttribute("message", e.getMessage());
		}
		return "Success";
	}

	@RequestMapping("home/Update")
	public String updateAcc() {
		return "Update";
	}
	
	@RequestMapping("/update-acc")
	public String searchForUpdateAccount(@RequestParam("account_id") long accountId,Model model) {
		try {
			BankAccount account= service.searchAccount(accountId);
			model.addAttribute("account", account);
		} catch (BankAccountNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		}
		return "accountInfo";

	}
	
	@RequestMapping("/updateaccount")
	public String updateAccount(@RequestParam("account_id") long accountId,
			@RequestParam("customer_name") String accountHolderName, @RequestParam("account_type") String accountType, Model model) {

		try {
			service.updateAccount(accountId, accountHolderName, accountType);
			model.addAttribute("message", "Account updated Successfully..");
		} catch (BankAccountNotFoundException e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		return "Success";

	}
	
	
	@RequestMapping("/home/Fund")
	public String fundTransferPage() 
	{
		return "Fund";
	}
	
	@RequestMapping("/Fund")
	public String fundTransfer(@RequestParam("from_account") long fromAccount,@RequestParam("to_account") long toAccount,@RequestParam("account_balance") double amount,  Model model) 
	{
		try 
		{
			service.fundTransfer(fromAccount, toAccount, amount);
			model.addAttribute("message", "Account transferred successfully..!!");
		} 
		catch (LowBalanceException e) 
		{
			model.addAttribute("message", e.getMessage());
		} 
		catch (BankAccountNotFoundException e) 
		{
			model.addAttribute("message", e.getMessage());
		}
		return "Success";
	}
	
	@RequestMapping("/home/Accdetails")
	public String AccountDetailsPage() 
	{
		return "Accdetails";
	}
	
	@RequestMapping("Accdetails")
	public String findAllBankAccounts(Model model)
	{
		List<BankAccount> accounts = service.findAllBankAccounts();
		model.addAttribute("accounts", accounts);
		return "Accdetails";
	}
}
