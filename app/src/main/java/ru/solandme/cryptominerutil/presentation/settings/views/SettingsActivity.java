package ru.solandme.cryptominerutil.presentation.settings.views;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import ru.solandme.cryptominerutil.R;
import ru.solandme.cryptominerutil.business.pojo.Algo;
import ru.solandme.cryptominerutil.presentation.settings.presenters.ISettingsPresenter;
import ru.solandme.cryptominerutil.presentation.settings.presenters.SettingsPresenter;

public class SettingsActivity extends AppCompatActivity implements ISettingsView {

    private CheckedTextView checkedBitcoreAlgo;
    private EditText bitcoreEditHashrate;
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

        checkedBitcoreAlgo = findViewById(R.id.checkedBitcoreAlgo);
        checkedBitcoreAlgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedBitcoreAlgo.isChecked()){
                    checkedBitcoreAlgo.setChecked(false);
                    presenter.algoChecked("bitcore_is_active", false);
                }
                else {
                    checkedBitcoreAlgo.setChecked(true);
                    presenter.algoChecked("bitcore_is_active", true);
                }
            }
        });
        bitcoreEditHashrate = findViewById(R.id.bitcore_edit_hashrate);

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
    public void showAlgos(HashMap<String, Algo> algos) {
        checkedBitcoreAlgo.setChecked(algos.get("bitcore").isActive());
        bitcoreEditHashrate.setText(algos.get("bitcore").getHashrate().toString());
    }
}
