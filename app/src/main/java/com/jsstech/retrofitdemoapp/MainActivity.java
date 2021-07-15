package com.jsstech.retrofitdemoapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView textView;
String url="https://jsonplaceholder.typicode.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//fetch json data by retrofit
        //in gradle module file put retrofit dependency
        //for dependency refer this link https://square.github.io/retrofit/
        //create model class for json data by using this link https://jsonplaceholder.typicode.com/posts
        //UserId,id.etc
        //create interface for json for call(Call<List<model>> call getModels();)
        //then in mainActivity create Retrofit object
        textView=findViewById(R.id.tv);
        textView.setText("");

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myapi api=retrofit.create(myapi.class);
        Call<List<model>> call=api.getmodels();
        call.enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call,Response<List<model>> response) {
                List<model> data=response.body();
                for(int i=0;i<data.size();i++)
                {
                    textView.append("SL No:" +data.get(i).getId()+" \n Title:"+data.get(i).getTitle()+ "\n\n\n");
                }
            }

            @Override
            public void onFailure(Call<List<model>> call,Throwable t) {

            }
        });


    }
}