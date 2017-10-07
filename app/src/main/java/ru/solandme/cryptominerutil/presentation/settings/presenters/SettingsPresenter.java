package ru.solandme.cryptominerutil.presentation.settings.presenters;


import java.util.HashMap;

import ru.solandme.cryptominerutil.business.ISettingsModel;
import ru.solandme.cryptominerutil.business.SettingsModel;
import ru.solandme.cryptominerutil.presentation.settings.views.ISettingsView;

public class SettingsPresenter implements ISettingsPresenter, ISettingsModel.CallBack {

    private ISettingsView view;
    private ISettingsModel model;

    public SettingsPresenter() {
        model = new SettingsModel(this);
    }

    @Override
    public void attachView(ISettingsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void viewIsReady() {
        loadSettings();

    }

    private void loadSettings() {
        model.getAlgos();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onAlgosReceived(HashMap hashrates) {
        view.showHashrates(hashrates);
    }

    @Override
    public void onError(String errorMessage) {
        view.showError(errorMessage);
    }
}
