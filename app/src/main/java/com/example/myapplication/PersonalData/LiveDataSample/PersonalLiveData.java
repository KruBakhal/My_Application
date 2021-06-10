package com.example.myapplication.PersonalData.LiveDataSample;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.PersonalData.Client.PersonalClient;
import com.example.myapplication.PersonalData.Model.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalLiveData extends ViewModel {

    MutableLiveData<Example> exampleMutableLiveData;

    public MutableLiveData<Example> getVasuResponse() {

        if (exampleMutableLiveData == null) {
            exampleMutableLiveData = new MutableLiveData<>();
            fetchData();
        }
        return exampleMutableLiveData;
    }

    private void fetchData() {
        Call<Example> exampleCall = PersonalClient.getClient().getVasuData("com.latest.status.message.text.jokes.funny");
        exampleCall.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                exampleMutableLiveData.postValue(response.body());

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });
    }
}
