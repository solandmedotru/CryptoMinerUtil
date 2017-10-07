package ru.solandme.cryptominerutil.presentation.settings.views;

import java.util.HashMap;

import ru.solandme.cryptominerutil.business.pojo.Algo;

public interface ISettingsView {

    void showError(String message);

    void showAlgos(HashMap<String, Algo> algos);
}
