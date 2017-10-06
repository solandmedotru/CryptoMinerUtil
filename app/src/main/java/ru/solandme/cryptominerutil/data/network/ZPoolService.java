package ru.solandme.cryptominerutil.data.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ZPoolService {
    @GET("currencies")
    Call<String> coinList();

}
