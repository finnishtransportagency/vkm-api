import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) throws InterruptedException {
	int min = 1;
	int max = 10000;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	for (int i=min; i<=max; i++) {
		
		if (i == min) {
			System.out.println("START");
		}
		
		LocalDateTime now = LocalDateTime.now();
		Result result = JUnitCore.runClasses(TestError.class);

		for (Failure failure : result.getFailures()) {
			String s = failure.toString();
			if (s.contains("Unrecognized field")) {
			  System.out.println(i + ": " + dtf.format(now));
			  System.out.println(failure.toString());
			}
		}
		
		//System.out.println(i + ": " + result.wasSuccessful());
		if (i == max) {
			System.out.println("END");
		}
		else if (i % 10 == 0)
			System.out.println(i);
		//TimeUnit.SECONDS.sleep(10);
	}
		
	}
}