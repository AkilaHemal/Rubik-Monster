package com.ahcreations.akila.rubikmonster;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import org.opencv.core.Mat;
import org.opencv.core.Point;

public class Color_Analyzer {

    Context context;

    Point[][] recEndPoints = new Point[3][3];
    Point[][] recStartPoints = new Point[3][3];

    Mat cubeMats[] = new Mat[6];
    Bitmap cubeBitmaps[] = new Bitmap[6];

    //L F R B U D
    //0 1 2 3 4 5
    float cubeColours[][][] = new float[6][9][3];
    char detectedColours[][] = new char[6][9];

    public Color_Analyzer(Context context, Point[][] recStartPoints, Point[][] recEndPoints) {
        this.context = context;
        this.recStartPoints = recStartPoints;
        this.recEndPoints = recEndPoints;
    }

    public void AddSide(Mat resultMat, Bitmap resultBitmap, int side) {
        cubeMats[side] = resultMat;
        cubeBitmaps[side] = resultBitmap;
    }

    //return HSV colour
    private float[] getRealColorOfSquare(Mat tempMat, Point start, Point end) {
        double[] out = {0, 0, 0, 255};
        double pointCount = 0.0;
        for (int i = (int) start.x; i < end.x; i++) {
            for (int j = (int) start.y; j < end.y; j++) {
                out[0] += tempMat.get(j, i)[0];
                out[1] += tempMat.get(j, i)[1];
                out[2] += tempMat.get(j, i)[2];
                pointCount++;
            }
        }
        out[0] = Math.round((out[0] / pointCount) * 100.0) / 100.0;
        out[1] = Math.round((out[1] / pointCount) * 100.0) / 100.0;
        out[2] = Math.round((out[2] / pointCount) * 100.0) / 100.0;

        float[] hsv = new float[3];
        Color.RGBToHSV((int) out[0], (int) out[1], (int) out[2], hsv);
        return hsv;
    }

    private void AddHSVColoursToMatrix() {
        for (int i = 0; i < 6; i++) {
            cubeColours[i][0] = getRealColorOfSquare(cubeMats[i], recStartPoints[0][0], recEndPoints[0][0]);
            cubeColours[i][1] = getRealColorOfSquare(cubeMats[i], recStartPoints[0][1], recEndPoints[0][1]);
            cubeColours[i][2] = getRealColorOfSquare(cubeMats[i], recStartPoints[0][2], recEndPoints[0][2]);
            cubeColours[i][3] = getRealColorOfSquare(cubeMats[i], recStartPoints[1][0], recEndPoints[1][0]);
            cubeColours[i][4] = getRealColorOfSquare(cubeMats[i], recStartPoints[1][1], recEndPoints[1][1]);
            cubeColours[i][5] = getRealColorOfSquare(cubeMats[i], recStartPoints[1][2], recEndPoints[1][2]);
            cubeColours[i][6] = getRealColorOfSquare(cubeMats[i], recStartPoints[2][0], recEndPoints[2][0]);
            cubeColours[i][7] = getRealColorOfSquare(cubeMats[i], recStartPoints[2][1], recEndPoints[2][1]);
            cubeColours[i][8] = getRealColorOfSquare(cubeMats[i], recStartPoints[2][2], recEndPoints[2][2]);
        }
    }

    private float[][] getLayerHSV(int layer) {
        float tempLayer[][] = new float[6][9];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                tempLayer[i][j] = cubeColours[i][j][layer];
            }
        }
        return tempLayer;
    }

    int positions[][] = new int[9][2];

    private void getMaximumNINE(float[][] src) {
        float tempSRC[][] = src;
        float maxVal[] = new float[9];
        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 9; j++) {
                    if (maxVal[k] < tempSRC[i][j]) {
                        maxVal[k] = tempSRC[i][j];
                        positions[k][0] = i;
                        positions[k][1] = j;
                    }

                }
            }
            tempSRC[positions[k][0]][positions[k][1]] = -1;
        }
    }

    private void getMinimumNINE(float[][] src) {
        float tempSRC[][] = src;
        float maxVal[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
        for (int k = 0; k < 9; k++) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 9; j++) {
                    if (maxVal[k] > tempSRC[i][j]) {
                        maxVal[k] = tempSRC[i][j];
                        positions[k][0] = i;
                        positions[k][1] = j;
                    }

                }
            }
            tempSRC[positions[k][0]][positions[k][1]] = 2;
        }
    }

    private void AddColourToMatrix(char color) {
        for (int i = 0; i < 9; i++) {
            detectedColours[positions[i][0]][positions[i][1]] = color;
            cubeColours[positions[i][0]][positions[i][1]][0] = -1;
            cubeColours[positions[i][0]][positions[i][1]][1] = 2;
            cubeColours[positions[i][0]][positions[i][1]][2] = -1;
        }
    }

    private void detect_Colour() {
        getMinimumNINE(getLayerHSV(1));
        AddColourToMatrix('w');
        getMaximumNINE(getLayerHSV(0));
        AddColourToMatrix('b');
        getMaximumNINE(getLayerHSV(0));
        AddColourToMatrix('g');
        getMaximumNINE(getLayerHSV(0));
        AddColourToMatrix('y');
        getMaximumNINE(getLayerHSV(0));
        AddColourToMatrix('o');
        getMaximumNINE(getLayerHSV(0));
        AddColourToMatrix('r');
    }

    public void Analise() {

        AddHSVColoursToMatrix();
        PrintColours();
        detect_Colour();

    }

    private void PrintColours() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                //Log.w("Colours ", String.valueOf(detectedColours[i][j]));
            }
        }
    }

    public char[][] getCube() {
        return detectedColours;
    }

}
