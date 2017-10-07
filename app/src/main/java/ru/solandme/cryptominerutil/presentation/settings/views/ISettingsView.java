package ru.solandme.cryptominerutil.presentation.settings.views;

import java.util.HashMap;

public interface ISettingsView {

    void showError(String message);

    void showHashrates(HashMap hashrates);
}
