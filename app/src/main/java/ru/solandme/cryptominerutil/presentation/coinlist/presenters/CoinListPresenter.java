package ru.solandme.cryptominerutil.presentation.coinlist.presenters;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import ru.solandme.cryptominerutil.business.CoinModel;
import ru.solandme.cryptominerutil.business.ICoinModel;
import ru.solandme.cryptominerutil.business.ISettingsModel;
import ru.solandme.cryptominerutil.business.SettingsModel;
import ru.solandme.cryptominerutil.business.pojo.Coin;
import ru.solandme.cryptominerutil.presentation.coinlist.views.ICoinListView;

public class CoinListPresenter implements ICoinListPresenter, ICoinModel.CallBack, ISettingsModel.CallBack {

    private ICoinListView view;
    private ICoinModel model;
    private ISettingsModel settingsModel;

    public CoinListPresenter() {
        settingsModel = new SettingsModel(this);
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
        settingsModel.getAlgos();
    }

    @Override
    public void loadCoinList(HashMap algos) {
        view.showProgress();

        model.loadCoinList(algos);
    }

    @Override
    public void destroy() {
        view.hideProgress();
        model.onDestroy();
    }

    @Override
    public void settingsClicked() {
        view.navigateToSettings();
    }

    @Override
    public void swipedLayout() {
        settingsModel.getAlgos();
    }

    @Override
    public void onCoinsListReceived(List<Coin> coins) {
        view.hideProgress();
        view.showCoinList(sortByProfit(coins));
    }

    @Override
    public void onAlgosReceived(HashMap algos) {
        loadCoinList(algos);
    }

    @Override
    public void onError(String errorMessage) {
        view.hideProgress();
    }

    private List<Coin> sortByProfit(List<Coin> coins) {
        Collections.sort(coins, new Comparator<Coin>() {
            @Override
            public int compare(Coin c1, Coin c2) {
                return c2.getDayBtc().compareTo(c1.getDayBtc());
            }
        });
        return coins;
    }
}
