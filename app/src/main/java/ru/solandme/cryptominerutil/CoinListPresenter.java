package ru.solandme.cryptominerutil;

public class CoinListPresenter implements ICoinListPresenter {

    private ICoinListView view;
    private IModel model;

    public CoinListPresenter(IModel model) {
        this.model = model;
    }

    @Override
    public void attachView(ICoinListView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        model.onClose();
    }

    @Override
    public void viewIsReady() {
        loadCoinList();
    }

    @Override
    public void loadCoinList() {
        model.loadCoinList(); //TODO сделать загрузку данных из репозитория. Или с базы если недавно обновлялись или из сети.
    }
}
