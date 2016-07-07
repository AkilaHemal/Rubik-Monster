package com.ahcreations.akila.rubikmonster;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

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

public class Camera_Class extends Fragment implements CameraBridgeViewBase.CvCameraViewListener2 {

    View view;

    Switch swFlash;
    Button butCapture;

    Bitmap resultBitmap;

    Mat resultMat;
    Mat mRgba;
    Point[][] recEndPoints = new Point[3][3];
    Point[][] recStartPoints = new Point[3][3];
    Scalar RectColor = new Scalar(0, 255, 0, 255);

    Context context;

    public Camera_Class(Context context){
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
        swFlash = (Switch) view.findViewById(R.id.swFlash);

        //Buttons
        butCapture = (Button) view.findViewById(R.id.butCapture);
    }

    //Components Listeners
    private void intiFlashOnChanged() {
        swFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mOpenCVCameraView.setupCameraFlashLight(true);
                } else {
                    mOpenCVCameraView.setupCameraFlashLight(false);
                }
            }
        });
    }

    private void initCaptureOnClick() {
        butCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Convert Mat to Bitmap
                resultBitmap = Bitmap.createBitmap(mRgba.cols(), mRgba.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(mRgba, resultBitmap);

                //Disable Camera
                mOpenCVCameraView.disableView();


                //Convert Bitmap to Mat
                resultMat = new Mat(resultBitmap.getHeight(), resultBitmap.getWidth(), CvType.CV_8U, new Scalar(4));
                Bitmap myBitmap32 = resultBitmap.copy(Bitmap.Config.ARGB_8888, true);
                Utils.bitmapToMat(myBitmap32, resultMat);


                Color_Detection_Class fr = new Color_Detection_Class(context,resultMat,recStartPoints,recEndPoints,resultBitmap);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment, fr);
                transaction.addToBackStack(null);
                transaction.commit();
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

        int scrWidth = mOpenCVCameraView.getWidth();;// - 58;
        int scrHeight = mOpenCVCameraView.getHeight();// - 58;
        int recSide = scrHeight - 40;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                recStartPoints[y][x] = new Point(((scrWidth / 2) - (recSide / 2)) + ((recSide / 3) * x), ((scrHeight / 2) - (recSide / 2)) + ((recSide / 3) * y));
            }
        }
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                recEndPoints[y][x] = new Point(((scrWidth / 2) - (recSide / 2)) + ((recSide / 3) * (x + 1)), ((scrHeight / 2) - (recSide / 2)) + ((recSide / 3) * (y + 1)));
            }
        }
    }
}
