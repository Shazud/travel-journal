package com.ddp.tj.trip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ddp.tj.R;
import com.ddp.tj.activity.MainActivity;
import com.ddp.tj.fragment.ViewFragment;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.TripViewHolder> {
    private ArrayList<Trip> data;
    private ArrayList<Trip> dataAll;
    private String currencySymbol;
    private MainActivity mainActivity;

    public TripAdapter(ArrayList<Trip> data, MainActivity mainActivity){
        this.data = data;
        this.dataAll = new ArrayList<Trip>();
        this.dataAll.addAll(data);
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_trip, parent, false);
        final TripViewHolder vh = new TripViewHolder(v);
        currencySymbol = parent.getResources().getString(R.string.currency_symbol);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ViewFragment(vh.getTrip(), data);
                mainActivity.changeCurrentFragment(fragment);
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, final int position) {
        holder.setTrip(data.get(position));
        holder.getTitle().setText(data.get(position).getTitle());
        holder.getDestination().setText(data.get(position).getDestination());
        if(data.get(position).isFavorite()) {
            holder.getFavorite().setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        }
        else{
            holder.getFavorite().setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
        }
        if(data.get(position).getPicture() != null) {
            holder.getImage().setImageBitmap(data.get(position).getPicture());
        }
        holder.getPrice().setText(currencySymbol + " " + data.get(position).getPrice().intValue());
        holder.getRating().setText(data.get(position).getRating().toString());

        holder.getFavorite().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean state = !data.get(position).isFavorite();
                data.get(position).setFavorite(state);
                if(state) {
                    v.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                }
                else{
                    v.setBackgroundResource(R.drawable.ic_favorite_border_black_24dp);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class TripViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView destination;
        private TextView price;
        private TextView rating;
        private ImageButton favorite;
        private ImageView image;
        private Trip trip;
        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.trip_title);
            this.destination = itemView.findViewById(R.id.trip_destination);
            this.price = itemView.findViewById(R.id.trip_price);
            this.rating = itemView.findViewById(R.id.trip_rating);
            this.favorite = itemView.findViewById(R.id.trip_favorite);
            this.image = itemView.findViewById(R.id.trip_image);


        }

        public void setTitle(TextView title) {
            this.title = title;
        }

        public void setDestination(TextView destination) {
            this.destination = destination;
        }

        public void setPrice(TextView price) {
            this.price = price;
        }

        public void setRating(TextView rating) {
            this.rating = rating;
        }

        public void setFavorite(ImageButton favorite) {
            this.favorite = favorite;
        }

        public void setImage(ImageView image) {
            this.image = image;
        }

        public Trip getTrip() {
            return trip;
        }

        public void setTrip(Trip trip) {
            this.trip = trip;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDestination() {
            return destination;
        }

        public TextView getPrice() {
            return price;
        }

        public TextView getRating() {
            return rating;
        }

        public ImageButton getFavorite() {
            return favorite;
        }

        public ImageView getImage() {
            return image;
        }
    }
}
