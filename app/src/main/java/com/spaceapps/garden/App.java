package com.spaceapps.garden;

import android.app.Application;
import android.widget.Toast;

import com.sendbird.android.GroupChannel;
import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;
import com.sendbird.android.User;
import com.spaceapps.garden.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

public class App extends Application {
    public static final String VERSION = "3.0.24";
    private static final String APP_ID = "4F5286C8-F285-4FD2-BE6B-315EBC73CFB5"; // app id

    @Override
    public void onCreate() {
        super.onCreate();
        SendBird.init(APP_ID, getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(config);
        final List<String> users = new ArrayList<>();
        users.add("sethgnavo");
        users.add("melchior");

        SendBird.connect("sethgnavo", new SendBird.ConnectHandler() {
            @Override
            public void onConnected(User user, SendBirdException e) {
                if (e != null) {
                    // Error.
                    Toast.makeText(App.this, "Meh2 happened!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    GroupChannel.createChannelWithUserIds(users, true, new GroupChannel
                            .GroupChannelCreateHandler() {
                        @Override
                        public void onResult(GroupChannel groupChannel, SendBirdException e) {
                            PreferenceUtils.setUrl(App.this, groupChannel.getUrl());
                            if (e != null) {
                                // Error.
                                Toast.makeText(App.this, "Meh2 happened!", Toast.LENGTH_SHORT)
                                        .show();
                                return;
                            }
                        }
                    });
                }
            }
        });

    }
}
