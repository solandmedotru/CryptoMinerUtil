package ru.solandme.cryptominerutil;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ZPoolService {
    @GET("currencies")
    Call<String> coinList();

}
