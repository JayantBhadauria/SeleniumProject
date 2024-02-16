package TestComponents;

import org.testng.*;

public class RetryAnalyzer implements IRetryAnalyzer{
	int currentCount=0,maxCount=1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(currentCount<maxCount) {
			currentCount++;
			return true;
		}
		return false;
	}

}
