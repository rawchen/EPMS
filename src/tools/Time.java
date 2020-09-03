package tools;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.*;
import java.text.*;
import java.util.Date;

/**
 * 时间工具
 * @author 22219
 *
 */
public class Time {
	public static String times = "";

	public String Time() {
		TimerTask task = new TimerTask() {
			Time time = new Time();

			public void run() {
				String sdate;
				sdate = (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"))
						.format(new Date());
				times = sdate;
			}
		};
		Timer t = new Timer();
		t.scheduleAtFixedRate(task, new Date(), 1000);
		return times;
	}
}