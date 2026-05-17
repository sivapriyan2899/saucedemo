package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer {
	
	int count = 0;
	int maxcount = 2;
	
	@Override
	public boolean retry(ITestResult result) {
	
		if(maxcount>0) {
			maxcount--;
			return true;
		}
		return false;
	}

}
