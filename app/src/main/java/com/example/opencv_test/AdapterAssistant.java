package com.example.opencv_test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.opengl.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

public class AdapterAssistant extends Adapter<AdapterAssistant.MyViewHolder> {

    Project project;

    public AdapterAssistant(Project p){
        this.project = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_view_assitant, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // get the piece from the project at the good position
        Piece piece = project.getPieces().get(position);
        // display the piece
        holder.display(piece);
    }

    @Override
    public int getItemCount() {
        return project.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private Button btnSearch;
        private Button btnPlace;

        private Piece piece;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageFrag);
            btnSearch = itemView.findViewById(R.id.searchBtn);
            btnPlace = itemView.findViewById(R.id.placeBtn);

            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    displaySearching(piece);
                }
            });
        }

        public void display(Piece p){
            piece = p;

            Bitmap img = BitmapFactory.decodeFile(p.getPathImage());

            imageView.setImageBitmap(img);

        }

        private void displaySearching(Piece p){
            Bitmap bitmap = project.drawposition(p);

            // TODO generate custom popup
        }
    }
}
