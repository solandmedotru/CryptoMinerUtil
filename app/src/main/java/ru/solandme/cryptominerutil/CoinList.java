package ru.solandme.cryptominerutil;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ru.solandme.cryptominerutil.pojo.Algo;
import ru.solandme.cryptominerutil.pojo.Coin;

public class CoinList extends AppCompatActivity implements ICoinListView {

    private ProgressDialog progressDialog;
    private RecyclerView coinList;
    private CoinListAdapter coinListAdapter;
    private List<Coin> coins;
    private List<Algo> algos;

    private ICoinListPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);

        initViews();

        if (presenter == null) {
            presenter = new CoinListPresenter();
        }

        presenter.attachView(this);
        presenter.viewIsReady();
    }

    private void initViews() {
        coinList = findViewById(R.id.coin_list_rv);
        coinList.setLayoutManager(new LinearLayoutManager(this));
        coinListAdapter = new CoinListAdapter(coins, algos);
        coinList.setAdapter(coinListAdapter);

        coinList.setHasFixedSize(true);

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

    @Override
    public void showCoinList(List<Coin> coins) {
        this.coins = coins;
        coinListAdapter.notifyDataSetChanged();

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
