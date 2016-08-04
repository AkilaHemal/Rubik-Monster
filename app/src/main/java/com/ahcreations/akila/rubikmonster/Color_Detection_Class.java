package com.ahcreations.akila.rubikmonster;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.List;

public class Color_Detection_Class extends Fragment {

    View view;
    Context context;

    Button butAnaliseColours, butPutColours, butNext;
    ImageView ivScreenShot;
    RelativeLayout rlCamera, rlOptions;
    TextView tvColourValues1, tvColourValues2, tvColourValues3;

    List<double[]> sideColours = new ArrayList<>();


    Mat resultMat;
    Point[][] recEndPoints = new Point[3][3];
    Point[][] recStartPoints = new Point[3][3];
    Scalar RectColor = new Scalar(0, 255, 0, 255);

    Bitmap resultBitmap;

    //L F R B U D
    //0 1 2 3 4 5
    Mat cubeMats[] = new Mat[6];
    Bitmap cubeBitmaps[] = new Bitmap[6];
    float cubeColours[][][] = new float[6][9][3];

    public Color_Detection_Class(Context context, Point[][] recStartPoints, Point[][] recEndPoints) {
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
            cubeColours[i][1] = getRealColorOfSquare(cubeMats[i], recStartPoints[1][0], recEndPoints[1][0]);
            cubeColours[i][2] = getRealColorOfSquare(cubeMats[i], recStartPoints[2][0], recEndPoints[2][0]);
            cubeColours[i][3] = getRealColorOfSquare(cubeMats[i], recStartPoints[0][1], recEndPoints[0][1]);
            cubeColours[i][4] = getRealColorOfSquare(cubeMats[i], recStartPoints[1][1], recEndPoints[1][1]);
            cubeColours[i][5] = getRealColorOfSquare(cubeMats[i], recStartPoints[2][1], recEndPoints[2][1]);
            cubeColours[i][6] = getRealColorOfSquare(cubeMats[i], recStartPoints[0][2], recEndPoints[0][2]);
            cubeColours[i][7] = getRealColorOfSquare(cubeMats[i], recStartPoints[1][2], recEndPoints[1][2]);
            cubeColours[i][8] = getRealColorOfSquare(cubeMats[i], recStartPoints[2][2], recEndPoints[2][2]);
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.color_detection_view, container, false);

        initComponents();
        initComponentsListeners();

        ivScreenShot.setImageBitmap(resultBitmap);

        return view;
    }

    private void initComponentsListeners() {
        initAnaliseColoursOnClick();
        initNextOnClick();
    }

    private void initAnaliseColoursOnClick() {
        butAnaliseColours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivScreenShot.setImageBitmap(captureBitmap(showColourTags(resultMat)));
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        AnaliseColours();
        ApplySideToCube();
        ShowColours();
    }

    private void initNextOnClick() {
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.sideComplete != 6) {
                    Camera_Class fr = new Camera_Class(context);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment, fr);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else {
                    Toast.makeText(view.getContext(), "Cube Complete", Toast.LENGTH_LONG).show();

                    Cube_Class fr = new Cube_Class(context, MainActivity.CubeColours);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment, fr);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });
    }

    private void initComponents() {
        //Buttons
        butAnaliseColours = (Button) view.findViewById(R.id.butAnaliseColours);
        butPutColours = (Button) view.findViewById(R.id.butPutColours);
        butNext = (Button) view.findViewById(R.id.butNext);

        //Image Viewers
        ivScreenShot = (ImageView) view.findViewById(R.id.ivScreenShot);

        //Relative Layouts
        rlCamera = (RelativeLayout) view.findViewById(R.id.rlCamera);
        rlOptions = (RelativeLayout) view.findViewById(R.id.rlOptions);

        //TextViews
        tvColourValues1 = (TextView) view.findViewById(R.id.tvColourValues1);
        tvColourValues2 = (TextView) view.findViewById(R.id.tvColourValues2);
        tvColourValues3 = (TextView) view.findViewById(R.id.tvColourValues3);
    }


    private double[] getColorOfSquare(Mat tempMat, Point start, Point end) {
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
        out[0] = out[0] / pointCount;
        out[1] = out[1] / pointCount;
        out[2] = out[2] / pointCount;

        return colorTag2RGB(guessColour(out));
    }

    private Mat ShowSquareColour(Mat scr, Point start, Point end, double[] color) {

        for (int i = 0; i < scr.width(); i++) {
            for (int j = 0; j < scr.height(); j++) {
                if (i > start.x && i < end.x) {
                    if (j > start.y && j < end.y) {
                        scr.put(j, i, color);
                    }
                }

            }
        }
        return scr;
    }

    private Bitmap captureBitmap(Mat temp) {
        Bitmap bitmap;
        try {
            bitmap = Bitmap.createBitmap(temp.cols(), temp.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(temp, bitmap);
            return bitmap;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private char guessColour(double[] color) {
        float[] hsv = new float[3];
        Color.RGBToHSV((int) color[0], (int) color[1], (int) color[2], hsv);
        int yellow = 78;
        int green = 150;
        int blue = 190;
        int orange = 48;
        int red = 25;

        if (hsv[1] < 0.25) {
            return 'w';
        } else if (hsv[0] > (green - 20) && hsv[0] < (green + 20)) {
            return 'g';
        } else if (hsv[0] > (blue - 20) && hsv[0] < (blue + 20)) {
            return 'b';
        } else if (hsv[0] > (orange - 15) && hsv[0] < (orange + 15)) {
            return 'o';
        } else if (hsv[0] > (red - 20) && hsv[0] < (red + 20)) {
            return 'r';
        } else if (hsv[0] > (yellow - 15) && hsv[0] < (yellow + 15)) {
            return 'y';
        } else {
            return 'u';
        }

    }

    private double[] colorTag2RGB(char colorLabel) {
        double[] out = new double[4];
        if (colorLabel == 'y') {
            out[0] = 232;
            out[1] = 232;
            out[2] = 38;
        } else if (colorLabel == 'g') {
            out[0] = 70;
            out[1] = 232;
            out[2] = 38;
        } else if (colorLabel == 'b') {
            out[0] = 38;
            out[1] = 38;
            out[2] = 232;
        } else if (colorLabel == 'o') {
            out[0] = 232;
            out[1] = 102;
            out[2] = 38;
        } else if (colorLabel == 'r') {
            out[0] = 232;
            out[1] = 38;
            out[2] = 38;
        } else if (colorLabel == 'w') {
            out[0] = 255;
            out[1] = 255;
            out[2] = 255;
        } else {
            out[0] = 0;
            out[1] = 0;
            out[2] = 0;
        }
        out[3] = 255;
        return out;
    }

    private Mat showColourTags(Mat src) {
        tvColourValues1.setText("");
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                float[] rgbColour = getRealColorOfSquare(src, recStartPoints[x][y], recEndPoints[x][y]);
                float[] hsvColour = new float[3];
                Color.RGBToHSV((int) rgbColour[0], (int) rgbColour[1], (int) rgbColour[2], hsvColour);
                tvColourValues1.setText(tvColourValues1.getText() + "\n" + String.valueOf(Math.round(hsvColour[0] * 100.0) / 100.0));
                tvColourValues2.setText(tvColourValues2.getText() + "\n" + String.valueOf(Math.round(hsvColour[1] * 100.0) / 100.0));
                tvColourValues3.setText(tvColourValues3.getText() + "\n" + String.valueOf(Math.round(hsvColour[2] * 100.0) / 100.0));
            }
            tvColourValues1.setText(tvColourValues1.getText() + "\n");
            tvColourValues2.setText(tvColourValues2.getText() + "\n");
            tvColourValues3.setText(tvColourValues3.getText() + "\n");

        }

        return src;
    }

    private void ShowColours() {
        resultMat = ShowSquareColour(resultMat, recStartPoints[0][0], recEndPoints[0][0], sideColours.get(0));
        resultMat = ShowSquareColour(resultMat, recStartPoints[1][0], recEndPoints[1][0], sideColours.get(1));
        resultMat = ShowSquareColour(resultMat, recStartPoints[2][0], recEndPoints[2][0], sideColours.get(2));
        resultMat = ShowSquareColour(resultMat, recStartPoints[0][1], recEndPoints[0][1], sideColours.get(3));
        resultMat = ShowSquareColour(resultMat, recStartPoints[1][1], recEndPoints[1][1], sideColours.get(4));
        resultMat = ShowSquareColour(resultMat, recStartPoints[2][1], recEndPoints[2][1], sideColours.get(5));
        resultMat = ShowSquareColour(resultMat, recStartPoints[0][2], recEndPoints[0][2], sideColours.get(6));
        resultMat = ShowSquareColour(resultMat, recStartPoints[1][2], recEndPoints[1][2], sideColours.get(7));
        resultMat = ShowSquareColour(resultMat, recStartPoints[2][2], recEndPoints[2][2], sideColours.get(8));
        ivScreenShot.setImageBitmap(captureBitmap(resultMat));
    }

    private void AnaliseColours() {
        sideColours.add(0, getColorOfSquare(resultMat, recStartPoints[0][0], recEndPoints[0][0]));
        sideColours.add(1, getColorOfSquare(resultMat, recStartPoints[1][0], recEndPoints[1][0]));
        sideColours.add(2, getColorOfSquare(resultMat, recStartPoints[2][0], recEndPoints[2][0]));
        sideColours.add(3, getColorOfSquare(resultMat, recStartPoints[0][1], recEndPoints[0][1]));
        sideColours.add(4, getColorOfSquare(resultMat, recStartPoints[1][1], recEndPoints[1][1]));
        sideColours.add(5, getColorOfSquare(resultMat, recStartPoints[2][1], recEndPoints[2][1]));
        sideColours.add(6, getColorOfSquare(resultMat, recStartPoints[0][2], recEndPoints[0][2]));
        sideColours.add(7, getColorOfSquare(resultMat, recStartPoints[1][2], recEndPoints[1][2]));
        sideColours.add(8, getColorOfSquare(resultMat, recStartPoints[2][2], recEndPoints[2][2]));
    }


    private void ApplySideToCube() {
        if (MainActivity.sideComplete == 0) {
            for (int i = 0; i < 9; i++) {
                MainActivity.CubeColours[0][i] = guessColour(sideColours.get(i));
            }
        } else if (MainActivity.sideComplete == 1) {
            for (int i = 0; i < 9; i++) {
                MainActivity.CubeColours[0][i] = guessColour(sideColours.get(i));
            }
        } else if (MainActivity.sideComplete == 2) {
            for (int i = 0; i < 9; i++) {
                MainActivity.CubeColours[0][i] = guessColour(sideColours.get(i));
            }
        } else if (MainActivity.sideComplete == 3) {
            for (int i = 0; i < 9; i++) {
                MainActivity.CubeColours[0][i] = guessColour(sideColours.get(i));
            }
        } else if (MainActivity.sideComplete == 4) {
            for (int i = 0; i < 9; i++) {
                MainActivity.CubeColours[0][i] = guessColour(sideColours.get(i));
            }
        } else if (MainActivity.sideComplete == 5) {
            for (int i = 0; i < 9; i++) {
                MainActivity.CubeColours[0][i] = guessColour(sideColours.get(i));
            }
        }
        MainActivity.sideComplete++;
    }


}
