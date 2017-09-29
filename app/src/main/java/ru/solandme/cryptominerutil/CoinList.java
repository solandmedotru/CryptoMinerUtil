package ru.solandme.cryptominerutil;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import ru.solandme.cryptominerutil.pojo.Coin;

public class CoinList extends AppCompatActivity {
    Realm db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);
        db = Realm.getDefaultInstance();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://zpool.ca/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ZPoolService zpoolApi = retrofit.create(ZPoolService.class);
        Call<String> stringCoins = zpoolApi.coinList();
        stringCoins.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.i("Response", response.body());
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body());

                        String responce = response.body();
                        try {
                            JSONObject jsonObject = new JSONObject(responce);
                            Iterator<String> keys = jsonObject.keys();
                            while (keys.hasNext()){
                                String key = keys.next();
                                if ( jsonObject.get(key) instanceof JSONObject ) {
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
