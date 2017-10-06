package ru.solandme.cryptominerutil.business;


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
import ru.solandme.cryptominerutil.data.network.ZPoolService;
import ru.solandme.cryptominerutil.business.pojo.Coin;

public class CoinModel implements IModel {

    private IModel.CallBack callBack;
    private List<String> excludeAlgo;

    public CoinModel(CallBack callBack) {
        this.callBack = callBack;
    }

    public void loadCoinList(List<String> excludeAlgo) {
        this.excludeAlgo = excludeAlgo;
        syncDb();
    }

    @Override
    public void onDestroy() {

    }

    private void syncDb() {

        Retrofit retrofit = getRetrofit();

        ZPoolService zpoolApi = retrofit.create(ZPoolService.class);

        Call<String> stringCoins = zpoolApi.coinList();

        stringCoins.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                List<Coin> coins = new ArrayList<>();
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

                                    if(excludeAlgo.contains(jsonObject.getJSONObject(key).getString("algo"))) continue; //Исключаем алго которые не нужно обрабатывать
                                    if(jsonObject.getJSONObject(key).getLong("workers") < 2.0) continue; //Исключаем алго которые не нужно обрабатывать

                                    Coin coin = new Coin();
                                    coin.setTag(key);

                                    coin.setAlgo(jsonObject.getJSONObject(key).getString("algo"));
                                    coin.setPort(jsonObject.getJSONObject(key).getLong("port"));
                                    coin.setName(jsonObject.getJSONObject(key).getString("name"));
                                    coin.setHeight(jsonObject.getJSONObject(key).getLong("height"));
                                    coin.setWorkers(jsonObject.getJSONObject(key).getLong("workers"));
                                    coin.setShares(jsonObject.getJSONObject(key).getLong("shares"));
                                    coin.setHashRate(jsonObject.getJSONObject(key).getLong("hashrate"));
                                    coin.setEstimate(jsonObject.getJSONObject(key).getString("estimate"));
                                    coin.setDayBlocks(jsonObject.getJSONObject(key).getLong("24h_blocks"));
                                    coin.setDayBtc(jsonObject.getJSONObject(key).getDouble("24h_btc"));
                                    coin.setLastBlock(jsonObject.getJSONObject(key).getLong("lastblock"));
                                    coin.setTimeSinceLast(jsonObject.getJSONObject(key).getLong("timesincelast"));

                                    coins.add(coin);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        callBack.onSuccess(coins);
                    } else {
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
