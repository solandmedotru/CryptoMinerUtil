package ru.solandme.cryptominerutil.business;


import java.util.HashMap;

import ru.solandme.cryptominerutil.business.pojo.Algo;

public interface ISettingsModel {
    void getAlgos();
    void saveAlgoActive(String algo, boolean isActive);

    void saveHashrate(String algoName, String hashrate);

    interface CallBack {
        void onAlgosReceived(HashMap<String, Algo> algos);
        void onError(String errorMessage);
    }
}
