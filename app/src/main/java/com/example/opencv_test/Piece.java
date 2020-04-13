package com.example.opencv_test;

import android.media.Image;

import java.io.Serializable;

public class Piece implements Serializable {
    private String pathImage;
    private Image image;

    public Piece(){
        this.pathImage = null;
        this.image = null;
    }
}
