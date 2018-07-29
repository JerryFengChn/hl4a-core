/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hl4a.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FileProvider extends ContentProvider {
	private static final String[] COLUMNS = {
			OpenableColumns.DISPLAY_NAME, OpenableColumns.SIZE};

	private static HashMap<String, PathStrategy> sCache = new HashMap<String, PathStrategy>();

	private PathStrategy mStrategy;

	/**
	 * Return {@link PathStrategy} for given authority, either by parsing or
	 * returning from cache.
	 */
	public static PathStrategy getPathStrategy(Context context, String authority) {
		PathStrategy strat;
		synchronized (sCache) {
			strat = sCache.get(authority);
			if (strat == null) {
				try {
					strat = parsePathStrategy(context, authority);
				} catch (Exception e) {}
				sCache.put(authority, strat);
			}
		}
		return strat;
	}

	/**
	 * Parse and return {@link PathStrategy} for given authority as defined in
	 * {@link #META_DATA_FILE_PROVIDER_PATHS} {@code <meta-data>}.
	 *
	 * @see #getPathStrategy(Context, String)
	 */
	private static PathStrategy parsePathStrategy(Context context, String authority) {
		final SimplePathStrategy strat = new SimplePathStrategy(authority);

		return strat;
	}

	/**
	 * Copied from ContentResolver.java
	 */
	private static int modeToMode(String mode) {
		int modeBits;
		if ("r".equals(mode)) {
			modeBits = ParcelFileDescriptor.MODE_READ_ONLY;
		} else if ("w".equals(mode) || "wt".equals(mode)) {
			modeBits = ParcelFileDescriptor.MODE_WRITE_ONLY
					| ParcelFileDescriptor.MODE_CREATE
					| ParcelFileDescriptor.MODE_TRUNCATE;
		} else if ("wa".equals(mode)) {
			modeBits = ParcelFileDescriptor.MODE_WRITE_ONLY
					| ParcelFileDescriptor.MODE_CREATE
					| ParcelFileDescriptor.MODE_APPEND;
		} else if ("rw".equals(mode)) {
			modeBits = ParcelFileDescriptor.MODE_READ_WRITE
					| ParcelFileDescriptor.MODE_CREATE;
		} else if ("rwt".equals(mode)) {
			modeBits = ParcelFileDescriptor.MODE_READ_WRITE
					| ParcelFileDescriptor.MODE_CREATE
					| ParcelFileDescriptor.MODE_TRUNCATE;
		} else {
			throw new IllegalArgumentException("Invalid mode: " + mode);
		}
		return modeBits;
	}

	private static String[] copyOf(String[] original, int newLength) {
		final String[] result = new String[newLength];
		System.arraycopy(original, 0, result, 0, newLength);
		return result;
	}

	private static Object[] copyOf(Object[] original, int newLength) {
		final Object[] result = new Object[newLength];
		System.arraycopy(original, 0, result, 0, newLength);
		return result;
	}

	@Override
	public boolean onCreate() {
		return true;
	}

	@Override
	public void attachInfo(Context context, ProviderInfo info) {
		super.attachInfo(context, info);
		if (!info.grantUriPermissions) {
			throw new SecurityException("xml配置必须设置android:grantUriPermissions=\"true\"");
		}
		mStrategy = getPathStrategy(context, info.authority);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
	                    String sortOrder) {
		// ContentProvider has already checked granted permissions
		final File file = mStrategy.getFileForUri(uri);

		if (projection == null) {
			projection = COLUMNS;
		}

		String[] cols = new String[projection.length];
		Object[] values = new Object[projection.length];
		int i = 0;
		for (String col : projection) {
			if (OpenableColumns.DISPLAY_NAME.equals(col)) {
				cols[i] = OpenableColumns.DISPLAY_NAME;
				values[i++] = file.getName();
			} else if (OpenableColumns.SIZE.equals(col)) {
				cols[i] = OpenableColumns.SIZE;
				values[i++] = file.length();
			}
		}

		cols = copyOf(cols, i);
		values = copyOf(values, i);

		final MatrixCursor cursor = new MatrixCursor(cols, 1);
		cursor.addRow(values);
		return cursor;
	}

	/**
	 * Returns the MIME type of a content URI returned by
	 * {@link #getUriForFile(Context, String, File) getUriForFile()}.
	 *
	 * @param uri A content URI returned by
	 *            {@link #getUriForFile(Context, String, File) getUriForFile()}.
	 * @return If the associated file has an extension, the MIME type associated with that
	 * extension; otherwise <code>application/octet-stream</code>.
	 */
	@Override
	public String getType(Uri uri) {
		// ContentProvider has already checked granted permissions
		final File file = mStrategy.getFileForUri(uri);

		final int lastDot = file.getName().lastIndexOf('.');
		if (lastDot >= 0) {
			final String extension = file.getName().substring(lastDot + 1);
			final String mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
			if (mime != null) {
				return mime;
			}
		}

		return "application/octet-stream";
	}

	/**
	 * By default, this method throws an {@link UnsupportedOperationException}. You must
	 * subclass FileProvider if you want to provide different functionality.
	 */
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		throw new UnsupportedOperationException("No external inserts");
	}

	/**
	 * By default, this method throws an {@link UnsupportedOperationException}. You must
	 * subclass FileProvider if you want to provide different functionality.
	 */
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		throw new UnsupportedOperationException("No external updates");
	}

	/**
	 * Deletes the file associated with the specified content URI, as
	 * returned by {@link #getUriForFile(Context, String, File) getUriForFile()}. Notice that this
	 * method does <b>not</b> throw an {@link IOException}; you must check its return value.
	 *
	 * @param uri           A content URI for a file, as returned by
	 *                      {@link #getUriForFile(Context, String, File) getUriForFile()}.
	 * @param selection     Ignored. Set to {@code null}.
	 * @param selectionArgs Ignored. Set to {@code null}.
	 * @return 1 if the delete succeeds; otherwise, 0.
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// ContentProvider has already checked granted permissions
		final File file = mStrategy.getFileForUri(uri);
		return file.delete() ? 1 : 0;
	}

	/**
	 * By default, FileProvider automatically returns the
	 * {@link ParcelFileDescriptor} for a file associated with a <code>content://</code>
	 * {@link Uri}. To get the {@link ParcelFileDescriptor}, call
	 * {@link android.content.ContentResolver#openFileDescriptor(Uri, String)
	 * ContentResolver.openFileDescriptor}.
	 * <p>
	 * To override this method, you must provide your own subclass of FileProvider.
	 *
	 * @param uri  A content URI associated with a file, as returned by
	 *             {@link #getUriForFile(Context, String, File) getUriForFile()}.
	 * @param mode Access mode for the file. May be "r" for read-only access, "rw" for read and
	 *             write access, or "rwt" for read and write access that truncates any existing file.
	 * @return A new {@link ParcelFileDescriptor} with which you can access the file.
	 */
	@Override
	public ParcelFileDescriptor openFile(Uri uri, String mode) {
		// ContentProvider has already checked granted permissions
		final File file = mStrategy.getFileForUri(uri);
		final int fileMode = modeToMode(mode);
		return ParcelFileDescriptor.open(file, fileMode);
	}

	/**
	 * Strategy for mapping between {@link File} and {@link Uri}.
	 * <p>
	 * Strategies must be symmetric so that mapping a {@link File} to a
	 * {@link Uri} and then back to a {@link File} points at the original
	 * target.
	 * <p>
	 * Strategies must remain consistent across app launches, and not rely on
	 * dynamic state. This ensures that any generated {@link Uri} can still be
	 * resolved if your process is killed and later restarted.
	 *
	 * @see SimplePathStrategy
	 */
	public interface PathStrategy {
		/**
		 * Return a {@link Uri} that represents the given {@link File}.
		 */
		Uri getUriForFile(File file);

		/**
		 * Return a {@link File} that represents the given {@link Uri}.
		 */
		File getFileForUri(Uri uri);
	}

	public static class SimplePathStrategy implements PathStrategy {
		private final String mAuthority;
		private final HashMap<String, File> mRoots = new HashMap<String, File>();

		public SimplePathStrategy(String authority) {
			mAuthority = authority;
		}

		/**
		 * Add a mapping from a name to a filesystem root. The provider only offers
		 * access to files that live under configured roots.
		 */
		public void addRoot(String name, File root) {
			if (TextUtils.isEmpty(name)) {
				throw new IllegalArgumentException("Name must not be empty");
			}

			try {
				// Resolve to canonical path to keep path checking fast
				root = root.getCanonicalFile();
			} catch (IOException e) {
				throw new IllegalArgumentException(
						"Failed to resolve canonical path for " + root, e);
			}

			mRoots.put(name, root);
		}

		@Override
		public Uri getUriForFile(File file) {
			String path;
			try {
				path = file.getCanonicalPath();
			} catch (IOException e) {
				throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
			}

            /*

            // Find the most-specific root path
            Map.Entry<String, File> mostSpecific = null;
            for (Map.Entry<String, File> root : mRoots.entrySet()) {
                final String rootPath = root.getValue().getPath();
                if (path.startsWith(rootPath) && (mostSpecific == null
                        || rootPath.length() > mostSpecific.getValue().getPath().length())) {
                    mostSpecific = root;
                }
            }

            if (mostSpecific == null) {
                throw new IllegalArgumentException(
                        "Failed to find configured root that contains " + path);
            }

            // Start at first char of path under root
            final String rootPath = mostSpecific.getValue().getPath();
            if (rootPath.endsWith("/")) {
                path = path.substring(rootPath.length());
            } else {
                path = path.substring(rootPath.length() + 1);
            }

            */

			// Encode the tag and path separately
			path = Uri.encode(path, "/");
			return new Uri.Builder().scheme("content")
					.authority(mAuthority).encodedPath(path).build();
		}

		@Override
		public File getFileForUri(Uri uri) {
			String path = uri.getEncodedPath();

			try {
				return new File(path).getCanonicalFile();
			} catch (IOException e) {}
			return null;

            /*

            final int splitIndex = path.indexOf('/', 1);
            final String tag = Uri.decode(path.substring(1, splitIndex));
            path = Uri.decode(path.substring(splitIndex + 1));

            final File root = mRoots.get(tag);
            if (root == null) {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }

            File file = new File(root, path);
            try {
                file = file.getCanonicalFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }

            if (!file.getPath().startsWith(root.getPath())) {
                throw new SecurityException("Resolved path jumped beyond configured root");
            }

            return file;

           */
		}
	}
}
