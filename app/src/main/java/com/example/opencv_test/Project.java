package com.example.opencv_test;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Project implements Serializable {
    // id can not be moved it is used for link pictures
    private String id;
    // name can be changed
    private String name;
    private ArrayList<Piece> pieces;
    private String pathToGlobalImage;

    public Project(int id, String globalImagePathAnalysed, ArrayList<Piece> pieces){
        this.id = Double.toString(Math.random()*(1000));
        this.pathToGlobalImage = null;
        this.pieces = null;
    }

    public Project (String id){
        this.id = id;
        this.pathToGlobalImage = null;
        this.pieces = null;
    }

    public Project(String id, String pathToGlobalImage){
        this.id = id;
        this.pathToGlobalImage = pathToGlobalImage;
        this.pieces = null;
    }

    public Project(String id, ArrayList<Piece> pieces){
        this.id = id;
        this.pathToGlobalImage = null;
        this.pieces = pieces;
    }

    public Project(String id, String pathToGlobalImage, ArrayList<Piece> pieces){
        this.id = id;
        this.pathToGlobalImage = pathToGlobalImage;
        this.pieces = pieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public void setPiece(Piece piece){
        this.pieces.add(piece);
    }

    public String getPathToGlobalImage() {
        return pathToGlobalImage;
    }

    public void setPathToGlobalImage(String pathToGlobalImage) {
        this.pathToGlobalImage = pathToGlobalImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int size(){
        return this.pieces.size();
    }

    public Bitmap drawposition(Piece piece){
        return BitmapFactory.decodeFile(this.pathToGlobalImage);

        /*TODO
        * Read the image of the piece and use the function matchTemplate
        * to draw a square at the position of the piece
        * */
    }
}
