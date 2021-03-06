/**
 * Copyright (C) 2013-2016 Stéphane Péchard.
 * <p>
 * This file is part of PhotoBackup.
 * <p>
 * PhotoBackup is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * PhotoBackup is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package fr.s13d.photobackup;

import android.app.Application;
import android.preference.PreferenceManager;

import java.util.Map;

import fr.s13d.photobackup.media.PBMediaStore;


/**
 * Application of the app
 */
public class PBApplication extends Application {

    private static final String LOG_TAG = "PBApplication";
    private static PBApplication app;
    private static PBMediaStore mediaStore;
    private static final Object lock = new Object();


    ///////////////
    // Constants //
    ///////////////
    public static final String PB_USER_AGENT = "PhotoBackup Android Client v" + BuildConfig.VERSION_NAME;
    public static final String PB_MEDIAS_SHARED_PREFS = "PB_MEDIAS_SHARED_PREFS";


    ////////////////
    // Life-cycle //
    ////////////////
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "Initializing app");
        setApp(this);

        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, "Default SharedPreferences:");
            final Map<String, ?> allPrefs = PreferenceManager.getDefaultSharedPreferences(this).getAll();
            for (final Map.Entry<String, ?> entry : allPrefs.entrySet()) {
                Log.d(LOG_TAG, entry.getKey() + "<" + entry.getValue().getClass().getSimpleName() + "> =  "
                        + entry.getValue().toString());
            }
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        trimMemory();
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        trimMemory();
    }


    private void trimMemory() {
        Log.d(LOG_TAG, "trimMemory");
        if (mediaStore != null) {
            mediaStore.close();
            nullifyMediaStore();
        }
    }


    /////////////////////
    // Getters/setters //
    /////////////////////
    public static PBApplication getApp() {
        return app;
    }


    private static void setApp(PBApplication application) {
        app = application;
    }


    public static PBMediaStore getMediaStore() {
        synchronized (lock) {
            if (mediaStore == null) {
                mediaStore = new PBMediaStore();
                mediaStore.sync();
            }
        }
        return mediaStore;
    }

    /**
     * Nullify the media store when destroying it.
     */
    public static void nullifyMediaStore() {
        synchronized (lock) {
            mediaStore = null;
        }
    }
}
