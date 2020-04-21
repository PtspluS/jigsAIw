package com.example.opencv_test;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.opencv.*;
import org.opencv.imgproc.Imgproc;

public class AnalyseImageThread extends Thread {

    private String pathSrc;
    private boolean globalImage;
    private Mat srcImage;
    private Mat dstImage;
    private int id;
    private ArrayList<String> pathNewImages;

    public AnalyseImageThread() {

    }

    /**
     * Function run when a picture is took
     * @param pathImage : path to the jpeg image
     * @param globalImage : if true means that it's the picture of the global jigsaw
     * @param id : id of the project
     * @return tab of all the new path for the new analysed images
     */
    public ArrayList<String> run(String pathImage, boolean globalImage, int id){
        globalImage = globalImage;
        pathSrc = pathImage;
        // create a matrix of the image
        srcImage = Imgcodecs.imread(pathImage);
        id = id;

        if(globalImage){
            dstImage = correctPerspective(srcImage);
        } else{

        }
        save();
        return pathNewImages;
    }

    private Mat correctPerspective(Mat src){
        // we put the image in gray, it's better for analyse
        Mat gray = new Mat(src.rows(), src.cols(), src.type());
        Mat thresh = new Mat(src.rows(), src.cols(), src.type());

        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        // we add a blur on the image to analyse it better again
        Size ksize = new Size(5,5);
        Imgproc.GaussianBlur(gray, gray,ksize,0 );
        Imgproc.threshold(gray, thresh, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY_INV);
        // now thresh is the matrix were the line are in 255. From here thresh is the src image
        // now trying to determined the contour of the image
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(thresh, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        double area;
        double maxArea =0;
        MatOfPoint maxContours = new MatOfPoint();
        double epsi;
        double peri;
        // try to dertermine the max area
        for(MatOfPoint c : contours){
            area = Imgproc.contourArea(c);
            if(area>maxArea){
                maxArea = area;
                maxContours = c;
            }
        }
        peri = Imgproc.arcLength(new MatOfPoint2f(maxContours.toArray()),true);
        MatOfPoint2f poly = new MatOfPoint2f();
        Imgproc.approxPolyDP(new MatOfPoint2f(maxContours.toArray()), poly, 0.02*peri, true);


        // delete this line after
        return new Mat();
    }

    private ArrayList<Mat> cutPieces(Mat src){
        return new ArrayList<Mat>();
    }

    private void save(){
        String path;
        if(globalImage) {
            path = "globalImage";
            File file = new File(pathSrc + "/modified_pieces/", path);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Imgcodecs.imwrite(String.valueOf(file), dstImage);
        }else{
            for(String p : pathNewImages){
                int index = pathNewImages.indexOf(p);
                path = "modified_piece_num"+index;
                File file = new File(pathSrc + "/modified_pieces/", path);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Imgcodecs.imwrite(String.valueOf(file), dstImage);
            }
        }
    }


}
