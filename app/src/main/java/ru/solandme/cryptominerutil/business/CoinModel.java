package ru.solandme.cryptominerutil.business;


import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.solandme.cryptominerutil.MyApp;
import ru.solandme.cryptominerutil.business.pojo.Algo;
import ru.solandme.cryptominerutil.business.pojo.Coin;
import ru.solandme.cryptominerutil.data.network.CoinApiHelpel;
import ru.solandme.cryptominerutil.data.network.ZPoolService;

public class CoinModel implements ICoinModel {

    private static final String TAG = CoinModel.class.getSimpleName();
    private ICoinModel.CallBack callBack;
    private HashMap<String, Algo> algos;

    public CoinModel(CallBack callBack) {
        this.callBack = callBack;
    }

    public void loadCoinList(HashMap<String, Algo> algos) {
        this.algos = algos;
        syncDb();
    }

    @Override
    public void onDestroy() {

    }

    private void syncDb() {

        CoinApiHelpel apiHelpel = new CoinApiHelpel();
        ZPoolService zpoolApi = apiHelpel.requestCoinList(MyApp.getContext(), "https://zpool.ca/api/").create(ZPoolService.class);

        Call<String> stringCoins = zpoolApi.coinList();

        stringCoins.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                List<Coin> coins = new ArrayList<>();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onCoinsListReceived", response.body());

                        String jsonString = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(jsonString);
                            Iterator<String> keys = jsonObject.keys();
                            while (keys.hasNext()) {
                                String key = keys.next();
                                if (jsonObject.get(key) instanceof JSONObject) {

                                    String algoKey = jsonObject.getJSONObject(key).getString("algo").toLowerCase().replace(" ", "_");


                                    if (algos.get(algoKey) == null) continue;

                                    if (!algos.get(algoKey).isActive())
                                        continue; //Исключаем алго которые не нужно обрабатывать
                                    if (jsonObject.getJSONObject(key).getLong("workers") < 2.0)
                                        continue; //Исключаем алго которые не нужно обрабатывать

                                    Coin coin = new Coin();
                                    coin.setTag(key);

                                    coin.setAlgo(jsonObject.getJSONObject(key).getString("algo"));
                                    coin.setPort(jsonObject.getJSONObject(key).getLong("port"));
                                    coin.setName(jsonObject.getJSONObject(key).getString("name"));
                                    coin.setHeight(jsonObject.getJSONObject(key).getLong("height"));
                                    coin.setWorkers(jsonObject.getJSONObject(key).getLong("workers"));
                                    coin.setShares(jsonObject.getJSONObject(key).getLong("shares"));
                                    coin.setHashRate(algos.get(algoKey).getHashrate());
                                    coin.setEstimate(jsonObject.getJSONObject(key).getString("estimate"));
                                    coin.setDayBlocks(jsonObject.getJSONObject(key).getLong("24h_blocks"));
                                    double dayPoolBTC = jsonObject.getJSONObject(key).getDouble("24h_btc");

                                    coin.setDayBtc(dayPoolBTC * getNormalizeHashrate(algoKey));
                                    coin.setLastBlock(jsonObject.getJSONObject(key).getLong("lastblock"));
                                    coin.setTimeSinceLast(jsonObject.getJSONObject(key).getLong("timesincelast"));

                                    coins.add(coin);
                                }
                            }
                        } catch (JSONException e) {
                            callBack.onError(e.getMessage());
                        }
                        callBack.onCoinsListReceived(coins);
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

    private Long getNormalizeHashrate(String algoKey) {
        switch (algoKey.toLowerCase()) {
            case "blake":
            case "decret":
            case "x11":
            case "quark":
            case "qubit":
                return algos.get(algoKey).getHashrate() / 1000000000000L;
            case "sha256":
                return algos.get(algoKey).getHashrate() / 1000000000000000L;
            case "equihash":
                return algos.get(algoKey).getHashrate() / 1000000L;
            default:
                return algos.get(algoKey).getHashrate() / 1000000000L;
        }
    }
}
