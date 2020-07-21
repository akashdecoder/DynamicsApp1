package com.example.dynamicsapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dynamicsapp1.databinding.FragmentContactusBinding;
import com.example.dynamicsapp1.databinding.FragmentSlideshowBinding;


public class contactus extends Fragment {


    public contactus() {
        // Required empty public constructor
    }
    FragmentContactusBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentContactusBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_contactus, container, false);
        return binding.getRoot();
    }
}