package ru.solandme.cryptominerutil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
        List<String> arrayList = Arrays.asList("scrypt", "sha256", "x11"); //TODO сделать список изменяемым и бырать из SharedPrefs
        model.loadCoinList(arrayList); //TODO сделать загрузку данных из репозитория. Или с базы если недавно обновлялись или из сети.
    }

    @Override
    public void destroy() {
        view.hideProgress();
        model.onDestroy();
    }

    @Override
    public void onSuccess(List<Coin> coins) {
        view.hideProgress();
        view.showCoinList(sortByProfit(coins));
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
