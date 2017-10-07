package ru.solandme.cryptominerutil.business;

import java.util.HashMap;
import java.util.List;

import ru.solandme.cryptominerutil.business.pojo.Algo;
import ru.solandme.cryptominerutil.business.pojo.Coin;

public interface ICoinModel {

    void loadCoinList(HashMap<String, Algo> algos);

    void onDestroy();

    interface CallBack {
        void onCoinsListReceived(List<Coin> coins);
        void onError(String errorMessage);
    }
}
