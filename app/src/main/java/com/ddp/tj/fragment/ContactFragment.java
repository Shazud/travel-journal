package com.ddp.tj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ddp.tj.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        FloatingActionButton fab = view.findViewById(R.id.contact_fragment_mail_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, "contact@ddp.traverjournal.com");
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Send using:"));
            }
        });
        return view;
    }
}
