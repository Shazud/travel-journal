package com.ddp.tj.trip;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {
    private ArrayList<Trip> data;
    private ArrayList<Trip> dataAll;

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class TripViewHolder extends RecyclerView.ViewHolder {

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
