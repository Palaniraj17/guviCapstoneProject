package TestCases;


import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.*;

import BasePackage.baseclass;
import PagesClass.*;

@Listeners(TestReports.TestListener.class)
public class ChangePasswordPageTC extends baseclass{

	ChangePasswordPage cpp;
	String newPassword="Iambala6*";
	Properties prop;

	@BeforeMethod(description="TC_01")
	public void setupPageObjects() throws IOException 
	{
		preCondition();
		cpp=new ChangePasswordPage(driver);
	}
	
	@Test(enabled=false)
	public void ChangepwdforUser() throws IOException {
		cpp.changePwd(newPassword);//New Password
	}
	
	@Test(enabled=true,description="TC_012")
	public void backtorestconfigpassword() throws IOException {
		cpp.changePwd(newPassword);//New Password
		cpp.backtoDefpassword(newPassword);
	}


	
	
}

