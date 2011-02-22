package featureide.fm.util;

import org.junit.Test;

public class StopWatchTest {

	@Test
	public void testGetTimeString() {
		System.out.println("testGetTimeString");
		long time = 116806;
		System.out.println(StopWatch.getTimeString(time));

		time = 96806;
		System.out.println(StopWatch.getTimeLongString(time));
	}

	@Test
	public void testGetTimeLongString() {
		System.out.println("testGetTimeLongString");
		long time = 116806;
		System.out.println(StopWatch.getTimeLongString(time));

		time = 96806;
		System.out.println(StopWatch.getTimeLongString(time));
	}

}
