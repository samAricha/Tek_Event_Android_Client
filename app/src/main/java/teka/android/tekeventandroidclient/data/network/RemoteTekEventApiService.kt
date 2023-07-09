package teka.android.tekeventandroidclient.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface RemoteTekEventApiService {
    companion object{
        var apiService:RemoteTekEventApiService ?= null
        fun getInstance(): RemoteTekEventApiService{

            if (apiService == null){
               val retrofit = Retrofit.Builder()
                    .baseUrl("remote url")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiService = retrofit.create(RemoteTekEventApiService::class.java)
            }
            return apiService!!
        }
    }

}