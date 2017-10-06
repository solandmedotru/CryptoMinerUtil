package ru.solandme.cryptominerutil.presentation.coinlist.views;

import java.util.List;

import ru.solandme.cryptominerutil.business.pojo.Coin;

public interface ICoinListView {
    void showProgress();
    void hideProgress();

    void showCoinList(List<Coin> coins);

    void navigateToSettings();
}
