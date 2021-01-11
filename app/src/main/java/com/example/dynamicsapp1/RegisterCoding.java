package com.example.dynamicsapp1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dynamicsapp1.databinding.FragmentRegisterCodingBinding;
import com.example.dynamicsapp1.mail.SendMail;
import com.example.dynamicsapp1.ui.home.HomeFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class RegisterCoding extends Fragment implements AdapterView.OnItemSelectedListener {

    FragmentRegisterCodingBinding binding;
    DatabaseReference reference;
    UserEvent userEvent;

    public RegisterCoding() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterCodingBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_register_coding, container, false);
        return binding.getRoot();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reference = FirebaseDatabase.getInstance().getReference().child("Coding");
        userEvent = new UserEvent();
        Spinner spinner = (Spinner) binding.typeCodingEvent;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(), R.array.spinner_coding, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        binding.registercoding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = binding.userName.getText().toString();
                final String email = binding.useremail.getText().toString();
                final String phone = binding.phone.getText().toString();
                final String usn = binding.userUsn.getText().toString();
                final String year = binding.userYear.getText().toString();
                final String event = binding.typeCodingEvent.getSelectedItem().toString();

                userEvent.setUserName(name);
                userEvent.setUserEmail(email);
                userEvent.setContact(phone);
                userEvent.setEvent(event);
                userEvent.setUsn(usn);
                userEvent.setYear(year);

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
                } else if(event.equals("--EVENT--")){
                    binding.userYear.setError("Please Select the Event");
                    binding.userYear.requestFocus();
                } else if(name.isEmpty() && email.isEmpty() && phone.isEmpty() && usn.isEmpty() && year.isEmpty()){
                    Toast.makeText(getContext(),"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                } else if(!name.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !usn.isEmpty() && !year.isEmpty()){
                    reference.push().setValue(userEvent);
                    String subject = "Registration For: " + event;
                    String message = "Hello " + name + ", Thank you for registering to " + event + "\n" +
                            "Keep in touch to know more updates\n\n" +
                            "With Regards,\nDYNAMICS\n(Project Oriented Community)";
                    sendEmail(getContext(), email, subject, message);
//                    Toast.makeText(getContext(),"Registration Successful !! Check Mail",
//                            Toast.LENGTH_SHORT).show();

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

    private void sendEmail(Context context, String email, String subject, String message) {
        SendMail sendMail = new SendMail(context, email, subject, message);
        sendMail.execute();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}