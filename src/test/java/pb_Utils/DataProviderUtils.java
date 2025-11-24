package pb_Utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {
	
//	@DataProvider(name = "browserTypeProvider")
	public Object[][] browserTypeProvider() {
        return new Object[][] {
            {"chrome"},
            {"firefox"}
        };
    }

}
