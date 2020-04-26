package com.example.opencv_test;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class AdapterManager extends Adapter<AdapterManager.ViewHolderManager> {

    File dir = new File(Environment.getExternalStorageDirectory()+"/jigsAIw");

    @NonNull
    @Override
    public ViewHolderManager onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.manage_activity_fragment, parent, false);


        return new ViewHolderManager(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterManager.ViewHolderManager holder, int position) {
        File[] dirs = dir.listFiles();

        holder.display(dirs[position]);
    }

    @Override
    public int getItemCount() {
        int count = dir.list().length;
        return count;
    }

    public class ViewHolderManager extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageButton btnEditName;
        private ImageButton btnDelete;

        public ViewHolderManager(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewProjectName);
            btnEditName = itemView.findViewById(R.id.btnEditName);
            btnDelete = itemView.findViewById(R.id.btnDeleteProject);

        }

        public void display(final File directory){
            try{
                // try to locale the project store @ dir path
                if(directory.isDirectory()){
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(directory+"/project.ser"));
                    Object obj = objectInputStream.readObject();
                    if (obj instanceof Project){
                        Project project = (Project) obj;

                        // if the name of the project is initialised now textView = project name
                        if(project.getName() != null){
                            textView.setText(project.getName());
                        }
                        // if not the name become the id of the project
                        else {
                            textView.setText(project.getId());
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                String path = String.valueOf(directory);
                String[] segments = path.split("/");
                String id = segments[segments.length-1];

                textView.setText(id);
            } finally {

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete(directory);
                    }
                });

            }
        }

        private boolean delete(File directory){
            File[] allContents = directory.listFiles();
            if (allContents != null) {
                for (File file : allContents) {
                    delete(file);
                }
            }
            return directory.delete();
        }
    }
}
