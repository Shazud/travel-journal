package com.ddp.tj.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ddp.tj.R;
import com.ddp.tj.trip.Trip;
import com.ddp.tj.trip.TripAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ArrayList<Trip> data;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter dataAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Set up recycler view
        this.data = readData();
        recyclerView = (RecyclerView) view.findViewById(R.id.home_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new TripAdapter(data);
        recyclerView.setAdapter(dataAdapter);



        return view;
    }

    private ArrayList<Trip> readData() {
        ArrayList<Trip> data = new ArrayList<>();
        //TODO: implement local storage
        data.add(new Trip("Travel around the glove", "Hanoi", null, 1499.99, 4.65, false));
        data.add(new Trip("Go home gi", "USA", null, 123, 0, false));
        data.add(new Trip("Take out Viet Cong", "Vietnam", null, 3400, 3.21, false));
        data.add(new Trip("Lol lmao", "Hanoi", null, 1499.99, 2.5, false));
        data.add(new Trip("Soaskdokasd", "aksdoaskdokaoskd", null, 1499.99, 3, false));
        data.add(new Trip("Sad lamba", "Hugga Bugga", null, 1499.99, 4.5, false));
        data.add(new Trip("Go to the moon", "Moon moon", null, 2000000, 4, true));
        data.add(new Trip("Go to mars", "Planet Mars", null, 1000000000, 5, true));
        return data;
    }
}