package ru.solandme.cryptominerutil.presentation.settings.views;


import android.content.Context;

public interface ISettingsView {

    void showError(String message);

    Context getContext();
}
