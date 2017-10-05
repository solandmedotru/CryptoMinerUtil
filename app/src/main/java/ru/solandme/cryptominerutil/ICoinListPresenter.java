package ru.solandme.cryptominerutil;


public interface ICoinListPresenter {
    void attachView(ICoinListView view);
    void detachView();
    void viewIsReady();
    void loadCoinList();
    void destroy();


}
