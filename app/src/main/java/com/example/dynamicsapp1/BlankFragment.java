package com.example.dynamicsapp1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dynamicsapp1.databinding.FragmentBlankBinding;
import com.example.dynamicsapp1.ui.home.HomeFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BlankFragment extends Fragment {
    FragmentBlankBinding binding;
    DatabaseReference databaseReference;
    Recruit recruit;
    public BlankFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBlankBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recruit = new Recruit();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Recruitments");

        binding.registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = binding.userName.getText().toString();
                final String email = binding.useremail.getText().toString();
                final String phone = binding.phone.getText().toString();
                final String usn = binding.userUsn.getText().toString();
                final String year = binding.userYear.getText().toString();

                recruit.setUserName(name);
                recruit.setUserEmail(email);
                recruit.setContact(phone);
                recruit.setUsn(usn);
                recruit.setYear(year);

                if(name.isEmpty()){
                    binding.userName.setError("Please Enter Your Name");
                    binding.userName.requestFocus();
                } else if(email.isEmpty()){
                    binding.useremail.setError("Please Enter Your Email");
                    binding.useremail.requestFocus();
                } else if(phone.isEmpty()){
                    binding.phone.setError("Please Enter Your Contact No.");
                    binding.phone.requestFocus();
                } else if(usn.isEmpty()){
                    binding.userUsn.setError("Please Enter Your USN");
                    binding.userUsn.requestFocus();
                } else if(year.isEmpty()){
                    binding.userYear.setError("Please Enter Your Year");
                    binding.userYear.requestFocus();
                } else if(name.isEmpty() && email.isEmpty() && phone.isEmpty() && usn.isEmpty() && year.isEmpty()){
                    Toast.makeText(getContext(),"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                } else if(!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !usn.isEmpty() && !year.isEmpty()){
                    databaseReference.push().setValue(recruit);
                    Toast.makeText(getContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                    Fragment fragment = new HomeFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                } else{
                    Toast.makeText(getContext(),"!!Error Occurred!!",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}