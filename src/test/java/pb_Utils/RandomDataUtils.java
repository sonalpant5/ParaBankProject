package pb_Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * 
Letters + digits	random(8)
Letters only	random(8, true, false)
Digits only	random(6, false, true)
Only symbols	random(5, 33, 48, false, false)
Specific chars	random(5, "ABC123")
 */


public class RandomDataUtils {
	

	public static String randomAlphabets(int n)
	{
	@SuppressWarnings("deprecation")
	String randomAlpha = RandomStringUtils.random(n,true,false);
	return randomAlpha;
	}
	
	@SuppressWarnings("deprecation")
	public static String randomNumbers(int n)
	{
		String randomNum = RandomStringUtils.random(n, false, true);
		return randomNum;
	}
	
	@SuppressWarnings("deprecation")
	public static String randomAlphaNumeric(int n)
	{
		String randomAlphaNum = RandomStringUtils.randomAlphanumeric(n);
		return randomAlphaNum;
	}
	
	public static String randomEmail()
	{
		String remail = randomAlphabets(6)+randomNumbers(2)+"@gmail.com";
		return remail;
	}
	
	public static String randomPassword()
	{
		String randomPwd =randomAlphabets(1).toUpperCase()+randomAlphabets(10).toLowerCase()+randomNumbers(2);
		return randomPwd;
	}
	
	public static String randomSSN()
	{
		String SSN = RandomDataUtils.randomNumbers(3)+"-"+RandomDataUtils.randomNumbers(2)+"-"+RandomDataUtils.randomNumbers(4);
		return SSN;
	}
	
	public static String randomAddress()
	{
		String addr = RandomDataUtils.randomAlphabets(1).toUpperCase()+"-"+RandomDataUtils.randomNumbers(3)+"  "+RandomDataUtils.randomAlphabets(6)
		+" "+RandomDataUtils.randomAlphabets(6);
		return addr;
	}
	
	public static String randomLicense()
	{
		String license = "10"+RandomDataUtils.randomNumbers(6);
		return license;
	}
	
	public static Map<String, String> randomUserData()
	{
		Map<String, String> userData = new HashMap<>();
		userData.put("firstName", RandomDataUtils.randomAlphabets(7));
		userData.put("lastName", RandomDataUtils.randomAlphabets(7));
		userData.put("address", RandomDataUtils.randomAddress());
		userData.put("city", RandomDataUtils.randomAlphabets(5));
		userData.put("state", RandomDataUtils.randomAlphabets(5));
		userData.put("zipCode", RandomDataUtils.randomNumbers(5));
		userData.put("phoneNumber", RandomDataUtils.randomNumbers(10));
		userData.put("ssn", RandomDataUtils.randomSSN());
		userData.put("username", RandomDataUtils.randomAlphabets(6));
		userData.put("password", RandomDataUtils.randomPassword());
		userData.put("confirmPassword", userData.get("password"));
		
		return userData;
	}
	
}
