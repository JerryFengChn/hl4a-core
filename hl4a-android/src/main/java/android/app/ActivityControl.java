package android.app;

import android.app.ActivityThread.ActivityClientRecord;
import android.os.IBinder;
import android.util.ArrayMap;
import 间.收集.有序列表;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 对ActivityThread.ActivityClientRecord的操作 (同包下)
 *
 * @author MikaGuraN
 */

public enum ActivityControl {
	;

	private static Field mActivities;

	static {
		try {
			mActivities = ActivityThread.class.getDeclaredField("mActivities");
		} catch (Exception ignored) {
		}
	}

	public static Activity getCurrentActivity() {

		for (Map.Entry<IBinder, ActivityClientRecord> recoed : getActivityRecordsMap().entrySet()) {
			if (!recoed.getValue().stopped) {
				return recoed.getValue().activity;
			}
		}
		return null;
	}

	public static Activity[] getActivities() {

		有序列表<Activity> r = new 有序列表<>();
		for (Map.Entry<IBinder, ActivityClientRecord> recoed : getActivityRecordsMap().entrySet()) {
			r.添加(recoed.getValue().activity);
		}
		return r.到数组(Activity.class);
	}

	public static void finishAll() {

		for (Map.Entry<IBinder, ActivityClientRecord> recoed : getActivityRecordsMap().entrySet()) {
			recoed.getValue().activity.finish();
		}
	}

	@SuppressWarnings("unchecked")
	private static ArrayMap<IBinder, ActivityClientRecord> getActivityRecordsMap() {

		try {
			return (ArrayMap<IBinder, ActivityClientRecord>) mActivities.get(ActivityThread.currentActivityThread());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
