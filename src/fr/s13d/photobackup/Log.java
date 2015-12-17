/**
 * Copyright (C) 2013-2015 Stéphane Péchard.
 *
 * This file is part of PhotoBackup.
 *
 * PhotoBackup is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PhotoBackup is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package fr.s13d.photobackup;


final public class Log {

	public static void d(String tag, String message) {
        if (BuildConfig.DEBUG) {
        	android.util.Log.d(tag, message);
        }
    }

	public static void e(String tag, String message) {
        if (BuildConfig.DEBUG) {
        	android.util.Log.e(tag, message);
        }
    }

	public static void i(String tag, String message) {
        if (BuildConfig.DEBUG) {
        	android.util.Log.i(tag, message);
        }
    }

	public static void v(String tag, String message) {
        if (BuildConfig.DEBUG) {
        	android.util.Log.v(tag, message);
        }
    }

	public static void w(String tag, String message) {
        if (BuildConfig.DEBUG) {
        	android.util.Log.w(tag, message);
        }
    }

}
