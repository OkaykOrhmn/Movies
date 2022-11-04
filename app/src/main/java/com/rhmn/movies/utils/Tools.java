package com.rhmn.movies.utils;

import com.rhmn.movies.api.ApiClient;
import com.rhmn.movies.api.ApiServices;

public class Tools {

    private static ApiServices apiServices = null ;

    public static ApiServices getApiServicesInstance (){

        if (apiServices == null ){
            //client
            apiServices = ApiClient.getClient().create(ApiServices.class);
        }

        return apiServices ;
    }
}
