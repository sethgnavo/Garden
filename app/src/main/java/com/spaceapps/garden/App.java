package com.spaceapps.garden;

import android.app.Application;

import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

public class App extends Application {
    private static final String APP_ID = "4F5286C8-F285-4FD2-BE6B-315EBC73CFB5"; // app id
    public static final String VERSION = "3.0.24";
    @Override
    public void onCreate() {
        super.onCreate();
        SendBird.init(APP_ID, getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);

        SendBird.connect("hh", new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if (e != null) {
                    // Error.
                    return;
                }
            }
        });
    }
}
