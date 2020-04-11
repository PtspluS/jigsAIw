package com.example.opencv_test;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class AnalyseImageThread extends Thread {

    Mat srcImage;
    Mat dstImage;

    /**
     * Function run when a picture is took
     * @param pathImage : path to the jpeg image
     * @param globalImage : if true means that it's the picture of the global jigsaw
     */
    public void run(String pathImage, boolean globalImage){
        // create a matrix of the image
        srcImage = Imgcodecs.imread(pathImage);
        // create a new matrix with the same dimension of the referent one
        dstImage = new Mat(srcImage.rows(),srcImage.cols(), srcImage.type());

        if(!globalImage){

        }
    }

    private void correctPerspective(){

    }


}
