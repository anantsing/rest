 package automationpractice;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import com.beust.jcommander.Parameter;

public class Testday1 {
	@Test
	@Parameters({"uname","pswd"})
	
	public void validateCartIcon(String username, String password) {
		System.out.println("cart");
		System.out.println(username+password);
	System.out.println(Thread.currentThread().getId());
	}
	@Test(groups="smoke")
	public void validateSearch() {
		System.out.println("search");
		Assert.assertTrue(true);
		
	}
	@Test(dependsOnMethods = "validateSearch")
	public void validatePersonIcon() {
		System.out.println("personIcon");
	}

}
