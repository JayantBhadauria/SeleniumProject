package TestComponents;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.*;

public class RetryAnalyzer implements IRetryAnalyzer{
	int currentCount=0,maxCount=1;
	Logger log=(Logger) LogManager.getLogger(RetryAnalyzer.class);
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		log.warn("Retrying test : "+result.getTestName());
		if(currentCount<maxCount) {
			currentCount++;
			return true;
		}
		return false;
	}

}
