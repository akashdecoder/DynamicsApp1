package com.example.dynamicsapp1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dynamicsapp1.databinding.FragmentSoftwareArticleBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SoftwareArticle extends Fragment {
    private RecyclerView docList;
    private DatabaseReference databaseReference;
    FragmentSoftwareArticleBinding binding;

    public SoftwareArticle() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSoftwareArticleBinding.inflate(getLayoutInflater());
        View view = inflater.inflate(R.layout.fragment_software_article, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("uploads");
        databaseReference.keepSynced(true);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseRecyclerAdapter<UploadPdf, UploadViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<UploadPdf, UploadViewHolder>
//                (UploadPdf.class, R.layout.files_row, UploadViewHolder.class, databaseReference) {
//
//            @Override
//            public UploadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull UploadViewHolder holder, int position, @NonNull UploadPdf model) {
//                holder.setFilename(model.getName());
//            }
//        };
//        binding.recyclerView.setAdapter(firebaseRecyclerAdapter);
//    }

    public static class UploadViewHolder extends RecyclerView.ViewHolder{
        View view;
        public UploadViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
        public void setFilename(String name){
            TextView fname = (TextView)view.findViewById(R.id.doumentTile);
            fname.setText(name);
        }
        public void setAuthorname(String aname){
            TextView auname = (TextView)view.findViewById(R.id.doumentnAuthor);
            auname.setText(aname);
        }
    }
}