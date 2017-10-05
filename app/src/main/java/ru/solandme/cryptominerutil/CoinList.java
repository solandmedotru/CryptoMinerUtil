package ru.solandme.cryptominerutil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import ru.solandme.cryptominerutil.pojo.Algo;
import ru.solandme.cryptominerutil.pojo.Coin;

public class CoinList extends AppCompatActivity implements ICoinListView {

    private ProgressDialog progressDialog;
    private RecyclerView coinList;
    CoinListAdapter coinListAdapter;
    private List<Algo> algos = new ArrayList<>();

    private ICoinListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);

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
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        if (isFinishing()) {
            presenter.destroy();
        }
    }
}
