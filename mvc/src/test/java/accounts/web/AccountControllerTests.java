package accounts.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;

import rewards.internal.account.Account;
import accounts.internal.StubAccountManager;

/**
 * A JUnit test case testing the AccountController. The AccountController has
 * two handler methods, therefore, two tests.
 */
public class AccountControllerTests {

	private AccountController controller;

	@Before
	public void setUp() throws Exception {
		controller = new AccountController(new StubAccountManager());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testHandleListRequest() {
		ExtendedModelMap model = new ExtendedModelMap();
		String viewName = controller.accountList(model);

		List<Account> accounts = (List<Account>) model.get("accounts");
		assertNotNull(accounts);
		assertEquals(1, accounts.size());
		assertEquals(Long.valueOf(0), accounts.get(0).getEntityId());
		assertEquals("accountList", viewName);
	}

	public void testAccountDetails(){
		ExtendedModelMap model = new ExtendedModelMap();
		long id = Long.valueOf(0);
		String view = controller.accountDetails(id, model);
		Account acct = (Account) model.get("account");
		
		assertEquals("accountDetails", view);
		assertNotNull(acct);
		assertEquals(Long.valueOf(id), acct.getEntityId());
	}

}
