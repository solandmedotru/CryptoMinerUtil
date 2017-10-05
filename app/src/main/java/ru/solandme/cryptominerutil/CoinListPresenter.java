package ru.solandme.cryptominerutil;

import android.util.Log;

import java.util.List;

import ru.solandme.cryptominerutil.pojo.Coin;

public class CoinListPresenter implements ICoinListPresenter, IModel.CallBack {

    private ICoinListView view;
    private IModel model;

    public CoinListPresenter() {
        model = new CoinModel(this);
    }

    @Override
    public void attachView(ICoinListView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void viewIsReady() {
        loadCoinList();
    }

    @Override
    public void loadCoinList() {
        view.showProgress();
        model.loadCoinList(); //TODO сделать загрузку данных из репозитория. Или с базы если недавно обновлялись или из сети.
    }

    @Override
    public void destroy() {
        view.hideProgress();
        model.onDestroy();
    }

    @Override
    public void onSuccess(List<Coin> coins) {
        view.hideProgress();
        view.showCoinList(coins);
    }

    @Override
    public void onError(String errorMessage) {
        view.hideProgress();
    }
}
