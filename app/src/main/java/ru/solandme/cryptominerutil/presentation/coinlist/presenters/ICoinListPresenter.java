package ru.solandme.cryptominerutil.presentation.coinlist.presenters;


import java.util.HashMap;

import ru.solandme.cryptominerutil.presentation.coinlist.views.ICoinListView;

public interface ICoinListPresenter {
    void attachView(ICoinListView view);
    void detachView();
    void viewIsReady();
    void loadCoinList(HashMap algos);
    void destroy();


    void settingsClicked();
}
