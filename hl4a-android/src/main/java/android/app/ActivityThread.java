package android.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.IBinder;

public final class ActivityThread {

	private static final String message = "must call at android device";

	public ActivityThread() {
		throw new IllegalStateException(message);
	}

	public static ActivityThread currentActivityThread() {
		throw new IllegalStateException(message);
	}

	public static boolean isSystem() {
		throw new IllegalStateException(message);
	}

	public static String currentOpPackageName() {
		throw new IllegalStateException(message);
	}

	public static String currentPackageName() {
		throw new IllegalStateException(message);
	}

	public static String currentProcessName() {
		throw new IllegalStateException(message);
	}

	public static Application currentApplication() {
		throw new IllegalStateException(message);
	}

	public Instrumentation getInstrumentation() {
		throw new IllegalStateException(message);
	}

	public android.os.Looper getLooper() {
		throw new IllegalStateException(message);
	}

	public Application getApplication() {
		throw new IllegalStateException(message);
	}

	public String getProcessName() {
		throw new IllegalStateException(message);
	}

	public final ActivityInfo resolveActivityInfo(Intent intent) {
		throw new IllegalStateException(message);
	}

	public final Activity getActivity(IBinder token) {
		throw new IllegalStateException(message);
	}

	final Handler getHandler() {
		throw new IllegalStateException(message);
	}

	static final class ActivityClientRecord {

		Activity activity;
		android.view.Window window;
		boolean paused;
		boolean stopped;

		public ActivityClientRecord() {
			throw new IllegalStateException(message);
		}
	}

}
