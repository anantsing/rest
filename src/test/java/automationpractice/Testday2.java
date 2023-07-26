package automationpractice;

//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Testday2 {
    @Test(groups="smoke")
    
	public void loginValidDetail() {
		System.out.println("login valid");
		System.out.println(Thread.currentThread().getId());
	}
	@Test
	public void loginWithInValidDetail() {
		
		System.out.println("Test invalid user");
	
	}
}
