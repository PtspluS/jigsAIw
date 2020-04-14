package com.example.opencv_test;


import java.io.Serializable;
import java.util.List;


public class Project implements Serializable {
    // id can not be moved it is used for link pictures
    private String id;
    // name can be changed
    private String name;
    private List<Piece> pieces;
    private String pathToGlobalImage;

    public Project(){
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

    public Project(String id, List<Piece> pieces){
        this.id = id;
        this.pathToGlobalImage = null;
        this.pieces = pieces;
    }

    public Project(String id, String pathToGlobalImage, List<Piece> pieces){
        this.id = id;
        this.pathToGlobalImage = pathToGlobalImage;
        this.pieces = pieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
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
}
