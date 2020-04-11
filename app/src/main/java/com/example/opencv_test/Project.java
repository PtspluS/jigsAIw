package com.example.opencv_test;


import java.util.List;


public class Project {
    private String name;
    private List<Piece> pieces;
    private String pathToGlobalImage;

    public Project(){
        this.name = Double.toString(Math.random()*(1000));
        this.pathToGlobalImage = null;
        this.pieces = null;
    }

    public Project (String name){
        this.name = name;
        this.pathToGlobalImage = null;
        this.pieces = null;
    }

    public Project(String name, String pathToGlobalImage){
        this.name = name;
        this.pathToGlobalImage = pathToGlobalImage;
        this.pieces = null;
    }

    public Project(String name, List<Piece> pieces){
        this.name = name;
        this.pathToGlobalImage = null;
        this.pieces = pieces;
    }

    public Project(String name, String pathToGlobalImage, List<Piece> pieces){
        this.name = name;
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
