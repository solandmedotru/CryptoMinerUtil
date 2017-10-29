package ru.solandme.cryptominerutil.presentation.settings.views;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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

    private ISettingsPresenter presenter;

    private HashMap<String, CheckedTextView> checkedAlgos;
    private HashMap<String, EditText> editTextHashrates;

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


        checkedAlgos = new HashMap<>();


        CheckedTextView checkedBitcoreAlgo = findViewById(R.id.checkedBitcoreAlgo);
        CheckedTextView checkedBlake2sAlgo = findViewById(R.id.checkedBlake2sAlgo);
        CheckedTextView checkedBlakcoinAlgo = findViewById(R.id.checkedBlakcoinsAlgo);
        CheckedTextView checkedC11Algo = findViewById(R.id.checkedC11Algo);
        checkedAlgos.put("Bitcore", checkedBitcoreAlgo);
        checkedAlgos.put("Blake2s", checkedBlake2sAlgo);
        checkedAlgos.put("Blakcoin", checkedBlakcoinAlgo);
        checkedAlgos.put("C11", checkedC11Algo);

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
                        view.setChecked(true);
                        presenter.algoChecked(algoName.toLowerCase() + "_is_active", true);
                    }
                }
            });
        }

        EditText bitcoreEditHashrate = findViewById(R.id.bitcore_edit_hashrate);
        EditText blake2sEditHashrate = findViewById(R.id.blake2s_edit_hashrate);
        EditText blakcoinEditHashrate = findViewById(R.id.blakcoin_edit_hashrate);
        EditText c11EditHashrate = findViewById(R.id.c11_edit_hashrate);
        editTextHashrates = new HashMap<>();
        editTextHashrates.put("Bitcore", bitcoreEditHashrate);
        editTextHashrates.put("Blake2s", blake2sEditHashrate);
        editTextHashrates.put("Blakcoin", blakcoinEditHashrate);
        editTextHashrates.put("C11", c11EditHashrate);

        for (HashMap.Entry<String, EditText> entry : editTextHashrates.entrySet()) {
            final String algoName = entry.getKey();
            final EditText editText = entry.getValue();

            editText.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {

                    try {
                        long l = Long.parseLong(s.toString());
                        presenter.hashrateChanged(algoName.toLowerCase() + "_hr", l);
                    } catch (NumberFormatException nfe) {
                        editText.setText(0);
                        showError(nfe.getMessage());
                    }
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });
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
    public void showAlgos(HashMap<String, Algo> algos) {

        for (HashMap.Entry<String, EditText> entry : editTextHashrates.entrySet()) {
            final String algoName = entry.getKey();
            final EditText editText = entry.getValue();

            editText.setText(algos.get(algoName.toLowerCase()).getHashrate().toString());
        }
        for (HashMap.Entry<String, CheckedTextView> entry : checkedAlgos.entrySet()) {
            final String algoName = entry.getKey();
            final CheckedTextView view = entry.getValue();

            view.setChecked(algos.get(algoName.toLowerCase()).isActive());
        }

    }
}
