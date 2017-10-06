package ru.solandme.cryptominerutil.presentation.settings.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import ru.solandme.cryptominerutil.R;
import ru.solandme.cryptominerutil.presentation.settings.presenters.ISettingsPresenter;
import ru.solandme.cryptominerutil.presentation.settings.presenters.SettingsPresenter;

public class SettingsActivity extends AppCompatActivity implements ISettingsView {

    private ISettingsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        if (presenter == null) {
            presenter = new SettingsPresenter();
        }
        presenter.attachView(this);
        presenter.viewIsReady();

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}
