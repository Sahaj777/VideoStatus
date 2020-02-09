package com.androappsolution.myvideomaker.adapter;

import android.content.Context;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androappsolution.myvideomaker.R;
import com.androappsolution.myvideomaker.activity.ShowVideoActivity;
import com.androappsolution.myvideomaker.model.Movie;
import com.androappsolution.myvideomaker.rest.ApiClient;
import com.androappsolution.myvideomaker.rest.ApiInterface;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    ApiInterface apiService;
    Bitmap thumb;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        ImageView iv;
        TextView data;
        TextView movieDescription;
        TextView rating;
        public CardView cardView;

        public MovieViewHolder(View v) {
            super(v);

            iv = (ImageView) v.findViewById(R.id.iv);
            data = (TextView) v.findViewById(R.id.txt);
            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

      // holder.data.setText(movies.get(position).getName());

        apiService = ApiClient.getClient().create(ApiInterface.class);

        Glide.with(context)
                .load(movies.get(position)
                        .getFileUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .fitCenter())
                .into(holder.iv);


        /*thumb = ThumbnailUtils.createVideoThumbnail(movies.get(position).getFileUrl(), MediaStore.Images.Thumbnails.MINI_KIND);

        holder.iv.setImageBitmap(thumb);*/


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String main = movies.get(position).getFileUrl();
                Intent intent = new Intent(context, ShowVideoActivity.class);
                intent.putExtra("VIDEO", main);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}