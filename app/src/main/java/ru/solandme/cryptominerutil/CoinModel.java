package ru.solandme.cryptominerutil;


import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import ru.solandme.cryptominerutil.pojo.Coin;

public class CoinModel implements IModel {

    private IModel.CallBack callBack;

    CoinModel(CallBack callBack) {
        this.callBack = callBack;
    }

    public void loadCoinList() {
        List<Coin> coins = new ArrayList<>();
        syncDb(coins);
    }

    @Override
    public void onDestroy() {

    }

    private void syncDb(final List<Coin> coins) {
        Retrofit retrofit = getRetrofit();

        ZPoolService zpoolApi = retrofit.create(ZPoolService.class);

        Call<String> stringCoins = zpoolApi.coinList();

        stringCoins.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.i("Response", response.body());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body());

                        String jsonString = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(jsonString);
                            Iterator<String> keys = jsonObject.keys();
                            while (keys.hasNext()) {
                                String key = keys.next();
                                if (jsonObject.get(key) instanceof JSONObject) {

                                    Coin coin = new Coin();
                                    coin.setTag(key);
                                    coin.setAlgo(jsonObject.getString("algo"));
                                    coin.setPort(jsonObject.getLong("port"));
                                    coin.setName(jsonObject.getString("name"));
                                    coin.setHeight(jsonObject.getLong("height"));
                                    coin.setWorkers(jsonObject.getLong("workers"));
                                    coin.setShares(jsonObject.getLong("shares"));
                                    coin.setHashRate(jsonObject.getLong("hashrate"));
                                    coin.setEstimate(jsonObject.getString("estimate"));
                                    coin.setDayBlocks(jsonObject.getLong("24h_blocks"));
                                    coin.setDayBtc(jsonObject.getDouble("24h_btc"));
                                    coin.setLastBlock(jsonObject.getLong("lastblock"));
                                    coin.setTimeSinceLast(jsonObject.getLong("timesincelast"));

                                    coins.add(coin);
                                }

                                callBack.onSuccess(coins);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                        callBack.onError("Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                callBack.onError(t.getMessage());
            }
        });
    }

    @NonNull
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://zpool.ca/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
}
