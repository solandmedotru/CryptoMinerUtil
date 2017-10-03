package ru.solandme.cryptominerutil;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CoinList extends AppCompatActivity implements ICoinListView {

    private ProgressDialog progressDialog;

    private ICoinListPresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);

        if (presenter == null) {
            presenter = new CoinListPresenter();
        }

        presenter.attachView(this);
        presenter.viewIsReady();
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        if (isFinishing()) {
            presenter.destroy();
        }
    }
}
