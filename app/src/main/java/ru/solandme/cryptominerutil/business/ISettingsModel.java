package ru.solandme.cryptominerutil.business;


import java.util.HashMap;

public interface ISettingsModel {
    void getAlgos();
    void saveSettings();

    interface CallBack {
        void onAlgosReceived(HashMap algos);
        void onError(String errorMessage);
    }
}
