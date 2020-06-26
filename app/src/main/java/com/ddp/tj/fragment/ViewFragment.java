package com.ddp.tj.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ddp.tj.R;
import com.ddp.tj.activity.MainActivity;
import com.ddp.tj.trip.Trip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ViewFragment extends Fragment {

    private TextView titleView;
    private TextView destinationView;
    private TextView typeView;
    private TextView priceView;
    private TextView startDateView;
    private TextView endDateView;
    private RatingBar ratingBar;
    private ImageView imageView;
    private Trip trip;
    private FloatingActionButton editButton;
    private FloatingActionButton favoriteButton;
    private ArrayList<Trip> data;

    private String currencySymbol;

    public ViewFragment(Trip trip, ArrayList<Trip> trips){
        this.data = trips;
        this.trip = trip;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_tip, container, false);

        //get views
        titleView = view.findViewById(R.id.view_trip_title_text_view);
        destinationView = view.findViewById(R.id.view_trip_destination_text_view);
        typeView = view.findViewById(R.id.view_trip_type_text_view);
        priceView = view.findViewById(R.id.view_trip_price_text_view);
        startDateView = view.findViewById(R.id.view_trip_start_date_view);
        endDateView = view.findViewById(R.id.view_trip_end_date_text_view);
        ratingBar = view.findViewById(R.id.trip_view_rating_bar);
        imageView = view.findViewById(R.id.view_trip_image_view);
        editButton = view.findViewById(R.id.view_trip_edit_fab);
        favoriteButton = view.findViewById(R.id.view_trip_favorite_fab);

        //put data
        titleView.setText(trip.getTitle());
        destinationView.setText(trip.getDestination());
        typeView.setText(trip.getType());
        currencySymbol = getActivity().getResources().getString(R.string.currency_symbol);
        priceView.setText(currencySymbol + " " + trip.getPrice().intValue());
        startDateView.setText(new SimpleDateFormat(getActivity().getResources().getString(R.string.date_format)).format(trip.getStartDate().getTime()));
        endDateView.setText(new SimpleDateFormat(getActivity().getResources().getString(R.string.date_format)).format(trip.getEndDate().getTime()));
        ratingBar.setRating(trip.getRating().floatValue());
        if(trip.getPicture()==null) {
            imageView.setImageResource(R.drawable.ic_image_black_24dp);
        }
        else {
            imageView.setImageBitmap(trip.getPicture());
        }
        if(trip.isFavorite()){
            favoriteButton.setImageResource(R.drawable.ic_favorite_white_24dp);
        }
        else{
            favoriteButton.setImageResource(R.drawable.ic_favorite_border_white_24dp);
        }

        //buttons listeners

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new EditFragment(trip, data);
                ((MainActivity)getActivity()).changeCurrentFragment(fragment);
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean state = !trip.isFavorite();
                trip.setFavorite(state);
                if(state){
                    ((FloatingActionButton)v).setImageResource(R.drawable.ic_favorite_white_24dp);
                }
                else{
                    ((FloatingActionButton)v).setImageResource(R.drawable.ic_favorite_border_white_24dp);
                }
            }
        });


        return view;
    }
}
