package com.example.myapplication.PersonalData;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.PersonalData.LiveDataSample.PersonalLiveData;
import com.example.myapplication.PersonalData.Model.Example;
import com.example.myapplication.R;

public class PersonalDataActivity extends AppCompatActivity {

    private PersonalLiveData vasuLiveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        vasuLiveData = new ViewModelProvider(this).get(PersonalLiveData.class);

        vasuLiveData.getVasuResponse().observe(this, new Observer<Example>() {
            @Override
            public void onChanged(Example example) {

                Log.d("TAG", "onChanged: " + example.toString());

            }
        });

    }

    public void fecthCall(View view) {
        vasuLiveData.getVasuResponse().observe(this, new Observer<Example>() {
            @Override
            public void onChanged(Example example) {

                Log.d("TAG", "2) onChanged: " + example.toString());

            }
        });
    }
}