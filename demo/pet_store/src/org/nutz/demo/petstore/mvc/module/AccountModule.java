package org.nutz.demo.petstore.mvc.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.demo.petstore.domain.Account;
import org.nutz.demo.petstore.service.AccountService;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

@InjectName("accountModule")
@At("/mvc/account")
@Fail("json")
public class AccountModule {
	private AccountService accountService;
	@At
	@Ok("jsp:jsp.account.showallaccounts1")
	public List<Account> showAllAccounts1(){
		return accountService.getAllAccounts();
	}
	@At
	@Ok("jsp:jsp.account.showallaccounts")
	public void showAllAccounts(HttpServletRequest request){
		request.setAttribute("accounts", accountService.getAllAccounts());
	}
	@At
	@Ok("json")
	public Account createAccount(@Param("account")Account account){
		accountService.addAccount(account);
		return account;
	}
	@At
	@Ok("json")
	public void deleteAccount(@Param("userids")String userids[] ){
		if(userids!=null){
			for(String userid:userids){
				accountService.deleteAccountByUserid(userid);
			}
		}
	}
	@At
	@Ok("json")
	public Account showAccount(@Param("userid")String userid){
		return accountService.getAccountByUserid(userid);
	}
	@At
	@Ok("json")
	public void updateAccount(@Param("account")Account account, @Param("olduserid")String olduserid){
		accountService.updateAccount(account,olduserid);
	}
}
