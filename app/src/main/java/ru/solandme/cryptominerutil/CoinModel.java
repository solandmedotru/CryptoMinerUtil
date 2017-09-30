package ru.solandme.cryptominerutil;


import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import ru.solandme.cryptominerutil.pojo.Algo;
import ru.solandme.cryptominerutil.pojo.Coin;
import ru.solandme.cryptominerutil.pojo.Pool;

public class CoinModel implements IModel {
    private Realm db;
    private List<Pool> pools;


    @Override
    public void loadCoinList() {
        db = Realm.getDefaultInstance();
        pools = getPools();
        Log.i("pools", "onCreate: " + pools.toString());
    }

    @Override
    public void onClose() {
        db.close();
    }

    private List<Pool> getPools() {
        if (db.isEmpty()) {
            return syncDb();
        } else {
            return readLast();
        }
    }

    private List<Pool> readLast() {
        return null;
    }

    private List<Pool> syncDb() {
        Retrofit retrofit = getRetrofit();

        ZPoolService zpoolApi = retrofit.create(ZPoolService.class);

        final Pool zpool = db.createObject(Pool.class);

        Call<String> stringAlgos = zpoolApi.algoList();
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
                                    db.beginTransaction();
                                    Coin coin = db.createObject(Coin.class);
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

                                    zpool.getCoins().add(coin);
                                    db.commitTransaction();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

            }
        });

        stringAlgos.enqueue(new Callback<String>() {
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
                                    db.beginTransaction();
                                    Algo algo = db.createObject(Algo.class);
                                    algo.setName(jsonObject.getString("name"));
                                    algo.setPort(jsonObject.getLong("port"));
                                    algo.setCoins(jsonObject.getLong("coins"));
                                    algo.setFees(jsonObject.getLong("fees"));
                                    algo.setHashrate(jsonObject.getLong("hashrate"));
                                    algo.setWorkers(jsonObject.getLong("workers"));
                                    algo.setEstimateCurrent(jsonObject.getString("estimate_current"));
                                    algo.setEstimateLast24h(jsonObject.getString("estimate_last24h"));
                                    algo.setActualLast24h(jsonObject.getString("actual_last24h"));
                                    algo.setHashrateLast24h(jsonObject.getDouble("hashrate_last24h"));

                                    zpool.getAlgos().add(algo);
                                    db.commitTransaction();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

            }
        });
        return db.where(Pool.class).findAll();

    }

    @NonNull
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://zpool.ca/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
}
