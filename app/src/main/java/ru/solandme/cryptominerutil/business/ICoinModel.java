package ru.solandme.cryptominerutil.business;

import java.util.List;

import ru.solandme.cryptominerutil.business.pojo.Coin;

public interface ICoinModel {

    void loadCoinList(List<String> arrayList);

    void onDestroy();

    interface CallBack {
        void onCoinsListReceived(List<Coin> coins);
        void onError(String errorMessage);
    }
}
