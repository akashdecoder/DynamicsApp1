package com.example.dynamicsapp1.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dynamicsapp1.MainActivity;
import com.example.dynamicsapp1.R;
import com.example.dynamicsapp1.databinding.FragmentGalleryBinding;
import com.example.dynamicsapp1.databinding.FragmentUploadsBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    FragmentGalleryBinding binding;
    RecyclerView recyclerView;
    private DatabaseReference databaseReference;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(getLayoutInflater());
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        return binding.getRoot();
    }
}