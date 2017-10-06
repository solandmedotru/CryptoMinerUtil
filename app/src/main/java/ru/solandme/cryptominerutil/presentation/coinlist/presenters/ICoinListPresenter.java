package ru.solandme.cryptominerutil.presentation.coinlist.presenters;


import ru.solandme.cryptominerutil.presentation.coinlist.views.ICoinListView;

public interface ICoinListPresenter {
    void attachView(ICoinListView view);
    void detachView();
    void viewIsReady();
    void loadCoinList();
    void destroy();


    void settingsClicked();
}
