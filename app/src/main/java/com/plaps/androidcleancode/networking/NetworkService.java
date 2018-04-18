package com.plaps.androidcleancode.networking;


import com.plaps.androidcleancode.models.CityListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by ennur on 6/25/16.
 */
public interface NetworkService {

    @GET("v1/city")
    Single<CityListResponse> getCityList();

}
