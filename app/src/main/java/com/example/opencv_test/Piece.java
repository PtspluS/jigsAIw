package com.example.opencv_test;

import java.io.Serializable;

public class Piece implements Serializable {
    private String pathImage;

    public Piece(){
        this.pathImage = null;
    }

    public Piece(String path){
        this.pathImage = path;
    }

    public String getPathImage() {
        return pathImage;
    }
}
