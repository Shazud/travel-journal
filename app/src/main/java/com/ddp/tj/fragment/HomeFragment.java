package com.ddp.tj.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddp.tj.R;
import com.ddp.tj.activity.MainActivity;
import com.ddp.tj.trip.Trip;
import com.ddp.tj.trip.TripAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ArrayList<Trip> data;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter dataAdapter;

    public HomeFragment(ArrayList<Trip> data){
        super();
        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Set up recycler view
        recyclerView = (RecyclerView) view.findViewById(R.id.home_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new TripAdapter(data);
        recyclerView.setAdapter(dataAdapter);

        //Floating action button init
        FloatingActionButton fab = view.findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new EditFragment( new Trip(), data);
                ((MainActivity)getActivity()).changeCurrentFragment(fragment);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.refreshDrawableState();
    }
}