package com.ddp.tj.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.ddp.tj.R;
import com.ddp.tj.activity.MainActivity;
import com.ddp.tj.trip.Trip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditFragment extends Fragment {
    private EditText nameView;
    private EditText destinationView;
    private RadioGroup radioGroup;
    private EditText priceView;
    private EditText startDateView;
    private EditText endDateView;
    private RatingBar ratingBar;
    private Button galleryPhoto;
    private Button takePhoto;
    private ImageView imageView;
    private Trip trip;
    private FloatingActionButton saveButton;
    private FloatingActionButton deleteButton;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    private ArrayList<Trip> data;

    public EditFragment(Trip trip, ArrayList<Trip> data) {
        super();
        if(trip == null){
            this.trip = new Trip();
        }
        else{
            this.trip = trip;
        }

        this.data = data;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_trip, container, false);

        //initialize views
        nameView = view.findViewById(R.id.trip_edit_name_edit_text);
        destinationView = view.findViewById(R.id.trip_edit_destination_edit_text);
        radioGroup = view.findViewById(R.id.trip_edit_radio_group);
        priceView = view.findViewById(R.id.trip_edit_price_edit_text);
        startDateView = view.findViewById(R.id.trip_edit_start_date);
        endDateView = view.findViewById(R.id.trip_edit_end_date);
        ratingBar = view.findViewById(R.id.trip_edit_rating_bar);
        galleryPhoto = view.findViewById(R.id.trip_edit_gallery_photo_button);
        takePhoto = view.findViewById(R.id.trip_edit_take_photo_button);
        imageView = view.findViewById(R.id.trip_edit_image_view);
        saveButton = view.findViewById(R.id.trip_edit_save_fab);
        deleteButton = view.findViewById(R.id.trip_edit_delete_fab);

        //update info
        if(trip.getTitle() != null){
            nameView.setText(trip.getTitle());
        }

        if(trip.getDestination() != null){
            destinationView.setText(trip.getDestination());
        }

        if(trip.getType() != null){
            RadioButton rb;
            if(trip.getType().equals((rb = view.findViewById(R.id.trip_edit_radio_button_mountain)).getText().toString())){
                rb.setChecked(true);
            }
            else if(trip.getType().equals((rb = view.findViewById(R.id.trip_edit_radio_button_city_break)).getText().toString())){
                rb.setChecked(true);
            }
            else if(trip.getType().equals((rb = view.findViewById(R.id.trip_edit_radio_button_sea_side)).getText().toString())){
                rb.setChecked(true);
            }
        }

        if(trip.getPrice() != null){
            priceView.setText(trip.getPrice().toString());
        }
        else{
            trip.setPrice(new Double(0));
        }

        if(trip.getStartDate() != null){
            startDateView.setText(new SimpleDateFormat(getActivity().getResources().getString(R.string.date_format)).format(trip.getStartDate().getTime()));
        }
        else {
            trip.setStartDate(Calendar.getInstance());
        }

        if(trip.getEndDate() != null){
            endDateView.setText(new SimpleDateFormat(getActivity().getResources().getString(R.string.date_format)).format(trip.getEndDate().getTime()));
        }
        else{
            trip.setEndDate(Calendar.getInstance());
        }

        if(trip.getRating() != null){
            ratingBar.setNumStars(trip.getRating().intValue());
        }
        else{
            trip.setRating(new Double(0));
        }

        if(trip.getPicture() != null){
            imageView.setImageBitmap(trip.getPicture());
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(trip);
                ((MainActivity)getActivity()).returnToPreviousFragment();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).returnToPreviousFragment();
            }
        });


        nameView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                trip.setTitle(nameView.getText().toString());
                return true;
            }
        });

        nameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                trip.setTitle(s.toString());
            }
        });

        priceView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                trip.setPrice(Double.parseDouble(s.toString()));
            }
        });


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                trip.setRating(rating);
            }
        });

        destinationView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                trip.setDestination(s.toString());
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                trip.setType(((RadioButton)group.findViewById(checkedId)).getText().toString());
            }
        });

        final DatePickerDialog.OnDateSetListener startDp = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar tripCalendar = trip.getStartDate();
                tripCalendar.set(Calendar.YEAR, year);
                tripCalendar.set(Calendar.MONTH, month);
                tripCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                startDateView.setText(new SimpleDateFormat(getActivity().getResources().getString(R.string.date_format)).format(tripCalendar.getTime()));
            }
        };

        final DatePickerDialog.OnDateSetListener endDp = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar tripCalendar = trip.getEndDate();
                tripCalendar.set(Calendar.YEAR, year);
                tripCalendar.set(Calendar.MONTH, month);
                tripCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                endDateView.setText(new SimpleDateFormat(getActivity().getResources().getString(R.string.date_format)).format(tripCalendar.getTime()));
            }
        };

        startDateView.setKeyListener(null);
        endDateView.setKeyListener(null);

        startDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = trip.getStartDate();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), startDp, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        endDateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = trip.getEndDate();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), endDp, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });


        galleryPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }
                    else{
                        pickImageFromGallery();
                    }
                }
                else{
                    pickImageFromGallery();
                }
            }
        });

        return view;
    }

    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else{
                    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == getActivity().RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageView.setImageURI(data.getData());

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),data.getData());
                this.trip.setPicture(bitmap);
            }
            catch (Exception ignored){
                //Toast.makeText(getContext(), "exception",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
