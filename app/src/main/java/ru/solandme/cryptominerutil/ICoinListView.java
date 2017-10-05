package ru.solandme.cryptominerutil;

import java.util.List;

import ru.solandme.cryptominerutil.pojo.Coin;

public interface ICoinListView {
    void showProgress();
    void hideProgress();

    void showCoinList(List<Coin> coins);
}
