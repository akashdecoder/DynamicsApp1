package com.example.dynamicsapp1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dynamicsapp1.databinding.FragmentHomeBinding;
import com.example.dynamicsapp1.databinding.FragmentUploadsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.concurrent.Executor;


public class Uploads extends Fragment {

    FragmentUploadsBinding binding;
    EditText userName, userPassword;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    Snackbar snackbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUploadsBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_uploads, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if(firebaseUser != null){
                    Toast.makeText(getContext(), "You are Logged in", Toast.LENGTH_SHORT).show();
//                    usertext.setText(userName.getText().toString().trim());
                    Intent i = new Intent(getContext(), Login.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getContext(), "Please Login to enter..", Toast.LENGTH_SHORT).show();
                }
            }
        };

        binding.loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.username.getText().toString().trim();
                String pass = binding.userpassword.getText().toString().trim();
                String pass1 = "Github@2020";
                if(name.isEmpty()){
                    binding.username.setError("Enter your Username");
                    binding.username.requestFocus();
                } else if(pass.isEmpty()){
                    binding.userpassword.setError("Enter your password");
                    binding.userpassword.requestFocus();
                } else if(name.isEmpty() && pass.isEmpty()){
                    Toast.makeText(getActivity(), "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if(!name.isEmpty() && !pass.isEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(name, pass).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getContext(), "Login Failure", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "You are Logged in", Toast.LENGTH_SHORT).show();
                                Fragment fragment = new Login();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                        }
                    });
                } else{
                    Toast.makeText(getActivity(), "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ForgotPassword();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    //    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        storageReference = FirebaseStorage.getInstance().getReference();
//        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");
//
//        binding.Choose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
//                    selectPdf();
//                } else {
//                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
//                }
//            }
//        });
//
//        binding.Upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(pdfUri != null)
//                uploadFile(pdfUri);
//                else
//                Toast.makeText(getContext(), "Select a file", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//
//    private void uploadFile(Uri pdfUri){
//        final String filename = System.currentTimeMillis()+"";
//        storageReference.child("uploads").child(filename).putFile(pdfUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        String url = taskSnapshot.getStorage().getDownloadUrl().toString();
//                        databaseReference.child(filename).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if(task.isSuccessful()){
//                                    Toast.makeText(getContext(), "File Successfully Uploaded", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(getContext(), "File Not Successfully Uploaded", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getContext(), "File Successfully Uploaded", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                int currentProgress = (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
//            }
//        });
//    }
//
//    private void selectPdf(){
//        Intent intent = new Intent();
//        intent.setType("application/pdf ");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, 86);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 86 && resultCode == RESULT_OK && data != null) {
//            pdfUri = data.getData();
//            binding.textChoose.setText("A file is selected: " + data.getData().getLastPathSegment());
//        } else {
//            Toast.makeText(getContext(), "Please select a file", Toast.LENGTH_SHORT).show();
//        }
//    }

    //    private void selectFile() {
//        Intent intent = new Intent();
//        intent.setType("Application/pdf");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select File"), 1);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
//            uploadFile(data.getData());
//        }
//    }
//
//    private void uploadFile(Uri data) {
//        StorageReference reference = storageReference.child("uploads/"+System.currentTimeMillis()+"");
//        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
//                while(!uri.isComplete());
//                Uri url = uri.getResult();
//
//                UploadPdf uploadPdf = new UploadPdf(binding.textChoose.getText().toString(), url.toString());
//                databaseReference.child(databaseReference.push().getKey()).setValue(uploadPdf);
//                Toast.makeText(getContext(), "File Uploaded", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
//                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//            }
//        });
//    }
}