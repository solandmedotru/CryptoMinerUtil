package ru.solandme.cryptominerutil;

import java.util.List;

import ru.solandme.cryptominerutil.pojo.Coin;

public interface IModel {

    void loadCoinList();

    void onDestroy();

    interface CallBack {
        void onSuccess(List<Coin> coins);
        void onError(String errorMessage);
    }
}
