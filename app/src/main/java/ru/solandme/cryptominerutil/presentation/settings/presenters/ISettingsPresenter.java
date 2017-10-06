package ru.solandme.cryptominerutil.presentation.settings.presenters;

import ru.solandme.cryptominerutil.presentation.settings.views.ISettingsView;

public interface ISettingsPresenter {
    void attachView(ISettingsView view);
    void detachView();
    void viewIsReady();
    void destroy();
}
