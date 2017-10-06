package ru.solandme.cryptominerutil.presentation.coinlist.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ru.solandme.cryptominerutil.R;
import ru.solandme.cryptominerutil.business.pojo.Algo;
import ru.solandme.cryptominerutil.business.pojo.Coin;
import ru.solandme.cryptominerutil.presentation.coinlist.presenters.CoinListPresenter;
import ru.solandme.cryptominerutil.presentation.coinlist.presenters.ICoinListPresenter;
import ru.solandme.cryptominerutil.presentation.settings.views.SettingsActivity;

public class CoinListActivity extends AppCompatActivity implements ICoinListView {

    private ProgressDialog progressDialog;
    private RecyclerView coinList;
    private CoinListAdapter coinListAdapter;
    private List<Algo> algos = new ArrayList<>();

    private ICoinListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(false);
        }

        //Временнве данные, TODO сделать чтение из SharedPrefs
        Algo scrypt = new Algo();
        scrypt.setName("scrypt");
        scrypt.setHashrate((long) 1000.0);
        algos.add(scrypt);
        //----------------


        initViews();

        if (presenter == null) {
            presenter = new CoinListPresenter();
        }

        presenter.attachView(this);
        presenter.viewIsReady();
    }

    private void initViews() {
        coinList = findViewById(R.id.coin_list_rv);
        coinList.setHasFixedSize(true);
        coinList.setLayoutManager(new LinearLayoutManager(this));
    }

    //Методы вызывается только из презентера
    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(this, "", "Please, wait.");
    }

    //Методы вызывается только из презентера
    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    //Методы вызывается только из презентера
    @Override
    public void showCoinList(List<Coin> coins) {
        coinListAdapter = new CoinListAdapter(this, coins, algos);
        coinList.setAdapter(coinListAdapter);
    }

    @Override
    public void navigateToSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            presenter.destroy();
        }
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                presenter.settingsClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}