package com.example.dictionary;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.dictionary.response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class requestmanager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public requestmanager(Context context) {
        this.context = context;
    }

    public void getwordmeaning(final onfetchdatalistener onfetchdatalistener, String word){

        new AsyncTask<Void, Void, Void>(){
            String message;
            List<response>responses;
            @Override
            protected Void doInBackground(Void... voids) {
                //Calling the RestAPI
                calldictionary calldictionary = retrofit.create(requestmanager.calldictionary.class);
                Call<List<response>> call = calldictionary.callmeanings(word);
                try {
                    Response<List<response>> response = call.execute();
                    if (!response.isSuccessful()) {
                        message =  "Error: Word(s) not found";
                        return null;
                    }
                    message = response.message();
                    responses = response.body();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                onfetchdatalistener.onfetchdata(responses.get(0),message);
            }
        }.execute();

    }
    public interface calldictionary {
        @GET("entries/en/{word}")
        Call<List<response>> callmeanings(
                @Path("word") String word
        );
    }
}