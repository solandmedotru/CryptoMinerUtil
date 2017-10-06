package ru.solandme.cryptominerutil.business;

import android.content.Context;
import android.content.SharedPreferences;

import ru.solandme.cryptominerutil.MyApp;

public class SettingsModel implements ISettingsModel {

    private SharedPreferences preferences;
    private Context context;

    public SettingsModel() {
        this.context = MyApp.getContext();
        this.preferences = context.getSharedPreferences("settings",  Context.MODE_PRIVATE);
    }

    @Override
    public void getSettings() {

    }

    @Override
    public void saveSettings() {

    }
}
