package com.androappsolution.myvideomaker.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androappsolution.myvideomaker.R;
import com.androappsolution.myvideomaker.adapter.MoviesAdapter;
import com.androappsolution.myvideomaker.model.Movie;
import com.androappsolution.myvideomaker.model.MoviesResponse;
import com.androappsolution.myvideomaker.rest.ApiClient;
import com.androappsolution.myvideomaker.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    SweetAlertDialog pDialog;
    ArrayList<Movie> movies;
    private static final String TAG = MainActivity.class.getSimpleName();


    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);


        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#45000000"));
        pDialog.setTitleText("Loading..");
        pDialog.setCancelable(false);
        pDialog.show();

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);

        Log.e("lnk","link male chhe ? :: "+call);

        call.enqueue(new Callback<MoviesResponse>() {

            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                pDialog.dismiss();
                int statusCode = response.code();

              /*  Log.e("res:","respoins male chhe ?" +response);

             List<Movie> movies = response.body().g;
                Log.e(">>>>:", "<<" + response.toString());
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
     */
                if(response.isSuccessful()) {

                    movies = (ArrayList<Movie>) response.body().getData();
                    recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));

                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}
