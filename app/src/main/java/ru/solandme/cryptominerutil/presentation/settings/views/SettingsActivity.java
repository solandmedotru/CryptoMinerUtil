package ru.solandme.cryptominerutil.presentation.settings.views;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

        List<String> algoNames = Arrays.asList(
                "Bitcore", "Blake2s", "Blakcoin", "C11", "Decred", "Equihash", "Groestl",
                "Hmq1725", "Jha", "Keccak", "Lbry", "Lyra2v2", "Lyra2z", "M7m",
                "Myr-gr", "Neoscrypt", "Nist5", "Quark", "Scrypt", "Sha256", "Sib", "Skein",
                "Skunk", "Timetravel", "Tribus", "Veltor", "X11", "X11evo", "X13", "X14", "X15",
                "X17", "Xevan", "Yescrypt");


        HashMap<String, CheckedTextView> checkedAlgos = new HashMap<>();

        checkedBitcoreAlgo = findViewById(R.id.checkedBitcoreAlgo);
        checkedAlgos.put("Bitcore", checkedBitcoreAlgo);

        for (HashMap.Entry<String, CheckedTextView> entry : checkedAlgos.entrySet()) {
            final String algoName = entry.getKey();
            final CheckedTextView view = entry.getValue();

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (view.isChecked()) {
                        view.setChecked(false);
                        presenter.algoChecked(algoName.toLowerCase() + "_is_active", false);
                    } else {
                        checkedBitcoreAlgo.setChecked(true);
                        presenter.algoChecked(algoName.toLowerCase() + "_is_active", true);
                    }
                }
            });
        }

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
