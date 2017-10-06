package ru.solandme.cryptominerutil.presentation.settings.presenters;


import android.content.SharedPreferences;

import ru.solandme.cryptominerutil.business.ISettingsModel;
import ru.solandme.cryptominerutil.business.SettingsModel;
import ru.solandme.cryptominerutil.presentation.settings.views.ISettingsView;

public class SettingsPresenter implements ISettingsPresenter {

    private ISettingsView view;
    private ISettingsModel model;

    public SettingsPresenter() {
        model = new SettingsModel();
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
    }

    @Override
    public void destroy() {

    }
}
