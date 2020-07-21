package com.example.dynamicsapp1;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dynamicsapp1.databinding.FragmentLoginUploadBinding;
import com.example.dynamicsapp1.databinding.FragmentUploadsBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class LoginUpload extends Fragment {
    private static final int RESULT_OK = -1;
    FragmentLoginUploadBinding binding;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    Uri pdfUri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginUploadBinding.inflate(getLayoutInflater());
        return inflater.inflate(R.layout.fragment_login_upload, container, false);
    }


}