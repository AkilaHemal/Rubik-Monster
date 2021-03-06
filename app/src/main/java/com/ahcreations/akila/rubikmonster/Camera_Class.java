package com.ahcreations.akila.rubikmonster;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Camera_Class extends Fragment implements CameraBridgeViewBase.CvCameraViewListener2 {

    View view;

    Button butFlash;
    Button butCapture;

    Color_Analyzer color_analizer;

    Bitmap resultBitmap;

    Mat resultMat;
    Mat mRgba;
    Point[][] recEndPoints = new Point[3][3];
    Point[][] recStartPoints = new Point[3][3];
    Scalar RectColor = new Scalar(0, 255, 0, 255);

    Context context;

    public Camera_Class(Context context) {
        this.context = context;
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(context) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    mOpenCVCameraView.enableView();
                    break;
                }
                default: {
                    super.onManagerConnected(status);
                }
            }

        }
    };
    private JavaCameraView mOpenCVCameraView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.camera_view, container, false);

        initCameraView();
        initComponents();
        initComponentsListeners();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOpenCVCameraView != null) {
            mOpenCVCameraView.disableView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, view.getContext(), mLoaderCallback);
    }

    private void initComponentsListeners() {
        intiFlashOnChanged();
        initCaptureOnClick();
    }

    private void initComponents() {
        //Switchers
        butFlash = (Button) view.findViewById(R.id.butFlash);

        //Buttons
        butCapture = (Button) view.findViewById(R.id.butCapture);
    }

    //Components Listeners
    private void intiFlashOnChanged() {
        butFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenCVCameraView.isFlashLightON) {
                    mOpenCVCameraView.setupCameraFlashLight(false);
                } else {
                    mOpenCVCameraView.setupCameraFlashLight(true);
                }

            }
        });

    }

    int sideNumber = 0;

    private void initCaptureOnClick() {
        butCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Convert Mat to Bitmap
                /*resultBitmap = Bitmap.createBitmap(mRgba.cols(), mRgba.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(mRgba, resultBitmap);

                try {
                    saveBitmapToInternalStorage(resultBitmap, String.valueOf(sideNumber));
                } catch (IOException e) {
                    Log.w("Saving Bitmap", e.getMessage());
                }*/
                LoadSavedImages();
                mOpenCVCameraView.disableView();
                /*resultBitmap = loadImageFromStorage("/", String.valueOf(sideNumber));

                //Disable Camera
                //mOpenCVCameraView.disableView();


                //Convert Bitmap to Mat
                resultMat = new Mat(resultBitmap.getHeight(), resultBitmap.getWidth(), CvType.CV_8U, new Scalar(4));
                Bitmap myBitmap32 = resultBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Utils.bitmapToMat(myBitmap32, resultMat);

                color_analizer.AddSide(resultMat, resultBitmap, sideNumber);
                Log.w("Camera Class", String.valueOf(sideNumber));
                Toast.makeText(context, "Side " + sideNumber + 1 + " Complete", Toast.LENGTH_SHORT).show();
                sideNumber++;
                if (sideNumber == 6) {
                    color_analizer.Analise();
                    mOpenCVCameraView.disableView();

                    Cube_Class fr = new Cube_Class(context, color_analizer.getCube());
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment, fr);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }*/
                //mOpenCVCameraView.enableView();
                /*Color_Detection_Class fr = new Color_Detection_Class(context, resultMat, recStartPoints, recEndPoints, resultBitmap);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fr);
                transaction.addToBackStack(null);
                transaction.commit();*/
            }
        });
    }


    private void initCameraView() {
        mOpenCVCameraView = (JavaCameraView) view.findViewById(R.id.show_camera_activity_java_surface_view);
        assert mOpenCVCameraView != null;
        mOpenCVCameraView.setVisibility(View.VISIBLE);
        mOpenCVCameraView.setCvCameraViewListener(this);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mOpenCVCameraView.setResolution(new Size(864, 480));
        mRgba = new Mat(height, width, CvType.CV_32FC1);

        getRecPoints();
        color_analizer = new Color_Analyzer(context, recStartPoints, recEndPoints);
    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        mRgba = drawNet(mRgba);
        return mRgba;
    }

    private Mat drawNet(Mat scr) {

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                Imgproc.rectangle(scr, recStartPoints[x][y], recEndPoints[x][y], RectColor, 2);
            }
        }
        return scr;
    }

    private void getRecPoints() {

        int scrWidth = mOpenCVCameraView.getWidth();
        int scrHeight = mOpenCVCameraView.getHeight();
        int recSide = scrHeight - 40;
        int pieceSide = recSide / 3;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                recStartPoints[y][x] = new Point(((scrWidth / 2) - (recSide / 2)) + ((recSide / 3) * x) + pieceSide / 3, ((scrHeight / 2) - (recSide / 2)) + ((recSide / 3) * y) + pieceSide / 3);
            }
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                recEndPoints[y][x] = new Point(((scrWidth / 2) - (recSide / 2)) + ((recSide / 3) * (x + 1)) - pieceSide / 3, ((scrHeight / 2) - (recSide / 2)) + ((recSide / 3) * (y + 1)) - pieceSide / 3);
            }
        }


    }

    private String saveBitmapToInternalStorage(Bitmap bitmapImage, String fileName) throws IOException {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File myPath = new File(directory, fileName + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myPath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert fos != null;
            fos.close();
        }
        return directory.getAbsolutePath();
    }

    private Bitmap loadImageFromStorage(String path, String fileName) {

        try {
            //File f = new File(path, "profile.jpg");
            ContextWrapper cw = new ContextWrapper(context);
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            // Create imageDir
            File f = new File(directory, fileName + ".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            //ImageView img=(ImageView)findViewById(R.id.imgPicker);
            //img.setImageBitmap(b);
            return b;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void LoadSavedImages() {
        for (int i = 0; i < 6; i++) {
            //Load saved images
            resultBitmap = loadImageFromStorage("/", String.valueOf(sideNumber));

            //Convert Bitmap to Mat
            resultMat = new Mat(resultBitmap.getHeight(), resultBitmap.getWidth(), CvType.CV_8U, new Scalar(4));
            Bitmap myBitmap32 = resultBitmap.copy(Bitmap.Config.ARGB_8888, true);
            Utils.bitmapToMat(myBitmap32, resultMat);

            color_analizer.AddSide(resultMat, resultBitmap, sideNumber);
            Log.w("Camera Class", String.valueOf(sideNumber));

            sideNumber++;
            if (sideNumber == 6) {
                color_analizer.Analise();
                mOpenCVCameraView.disableView();

                Cube_Class fr = new Cube_Class(context, color_analizer.getCube());
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fr);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }
        Toast.makeText(context, "Loading Images Completed", Toast.LENGTH_SHORT).show();

    }
}
