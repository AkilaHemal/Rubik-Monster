package com.ahcreations.akila.rubikmonster;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Cube_Class extends Fragment {

    View view;
    Context context;

    Button butSolve;
    TextView tvMoves;

    FrameLayout flLeft0, flLeft1, flLeft2, flLeft3, flLeft4, flLeft5, flLeft6, flLeft7, flLeft8;
    FrameLayout flFront0, flFront1, flFront2, flFront3, flFront4, flFront5, flFront6, flFront7, flFront8;
    FrameLayout flRight0, flRight1, flRight2, flRight3, flRight4, flRight5, flRight6, flRight7, flRight8;
    FrameLayout flBack0, flBack1, flBack2, flBack3, flBack4, flBack5, flBack6, flBack7, flBack8;
    FrameLayout flUp0, flUp1, flUp2, flUp3, flUp4, flUp5, flUp6, flUp7, flUp8;
    FrameLayout flDown0, flDown1, flDown2, flDown3, flDown4, flDown5, flDown6, flDown7, flDown8;


    char[] down_side = {'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y'};
    char[] up_side = {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'};
    char[] front_side = {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
    char[] right_side = {'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r'};
    char[] back_side = {'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g'};
    char[] left_side = {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'};

    public Cube_Class(Context context, char cubeColours[][]) {
        left_side = cubeColours[0];
        front_side = cubeColours[1];
        right_side = cubeColours[2];
        back_side = cubeColours[3];
        up_side = cubeColours[4];
        down_side = cubeColours[5];

        this.context = context;
        Log.e("Test", String.valueOf(left_side));
        Log.e("Test", String.valueOf(front_side));
        Log.e("Test", String.valueOf(right_side));
        Log.e("Test", String.valueOf(back_side));
        Log.e("Test", String.valueOf(up_side));
        Log.e("Test", String.valueOf(down_side));

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cube_view, container, false);

        initComponent();
        refreshCubeView();
        //flLeft0.setBackgroundColor(Color.BLACK);
        initbutSolveOnClickListener();
        return view;
    }

    private void initComponent() {
        butSolve = (Button) view.findViewById(R.id.butSolve);
        tvMoves = (TextView)view.findViewById(R.id.tvMoves);

        flLeft0 = (FrameLayout) view.findViewById(R.id.flLeft0);
        flLeft1 = (FrameLayout) view.findViewById(R.id.flLeft1);
        flLeft2 = (FrameLayout) view.findViewById(R.id.flLeft2);
        flLeft3 = (FrameLayout) view.findViewById(R.id.flLeft3);
        flLeft4 = (FrameLayout) view.findViewById(R.id.flLeft4);
        flLeft5 = (FrameLayout) view.findViewById(R.id.flLeft5);
        flLeft6 = (FrameLayout) view.findViewById(R.id.flLeft6);
        flLeft7 = (FrameLayout) view.findViewById(R.id.flLeft7);
        flLeft8 = (FrameLayout) view.findViewById(R.id.flLeft8);

        flFront0 = (FrameLayout) view.findViewById(R.id.flFront0);
        flFront1 = (FrameLayout) view.findViewById(R.id.flFront1);
        flFront2 = (FrameLayout) view.findViewById(R.id.flFront2);
        flFront3 = (FrameLayout) view.findViewById(R.id.flFront3);
        flFront4 = (FrameLayout) view.findViewById(R.id.flFront4);
        flFront5 = (FrameLayout) view.findViewById(R.id.flFront5);
        flFront6 = (FrameLayout) view.findViewById(R.id.flFront6);
        flFront7 = (FrameLayout) view.findViewById(R.id.flFront7);
        flFront8 = (FrameLayout) view.findViewById(R.id.flFront8);

        flRight0 = (FrameLayout) view.findViewById(R.id.flRight0);
        flRight1 = (FrameLayout) view.findViewById(R.id.flRight1);
        flRight2 = (FrameLayout) view.findViewById(R.id.flRight2);
        flRight3 = (FrameLayout) view.findViewById(R.id.flRight3);
        flRight4 = (FrameLayout) view.findViewById(R.id.flRight4);
        flRight5 = (FrameLayout) view.findViewById(R.id.flRight5);
        flRight6 = (FrameLayout) view.findViewById(R.id.flRight6);
        flRight7 = (FrameLayout) view.findViewById(R.id.flRight7);
        flRight8 = (FrameLayout) view.findViewById(R.id.flRight8);

        flBack0 = (FrameLayout) view.findViewById(R.id.flBack0);
        flBack1 = (FrameLayout) view.findViewById(R.id.flBack1);
        flBack2 = (FrameLayout) view.findViewById(R.id.flBack2);
        flBack3 = (FrameLayout) view.findViewById(R.id.flBack3);
        flBack4 = (FrameLayout) view.findViewById(R.id.flBack4);
        flBack5 = (FrameLayout) view.findViewById(R.id.flBack5);
        flBack6 = (FrameLayout) view.findViewById(R.id.flBack6);
        flBack7 = (FrameLayout) view.findViewById(R.id.flBack7);
        flBack8 = (FrameLayout) view.findViewById(R.id.flBack8);

        flUp0 = (FrameLayout) view.findViewById(R.id.flUp0);
        flUp1 = (FrameLayout) view.findViewById(R.id.flUp1);
        flUp2 = (FrameLayout) view.findViewById(R.id.flUp2);
        flUp3 = (FrameLayout) view.findViewById(R.id.flUp3);
        flUp4 = (FrameLayout) view.findViewById(R.id.flUp4);
        flUp5 = (FrameLayout) view.findViewById(R.id.flUp5);
        flUp6 = (FrameLayout) view.findViewById(R.id.flUp6);
        flUp7 = (FrameLayout) view.findViewById(R.id.flUp7);
        flUp8 = (FrameLayout) view.findViewById(R.id.flUp8);

        flDown0 = (FrameLayout) view.findViewById(R.id.flDown0);
        flDown1 = (FrameLayout) view.findViewById(R.id.flDown1);
        flDown2 = (FrameLayout) view.findViewById(R.id.flDown2);
        flDown3 = (FrameLayout) view.findViewById(R.id.flDown3);
        flDown4 = (FrameLayout) view.findViewById(R.id.flDown4);
        flDown5 = (FrameLayout) view.findViewById(R.id.flDown5);
        flDown6 = (FrameLayout) view.findViewById(R.id.flDown6);
        flDown7 = (FrameLayout) view.findViewById(R.id.flDown7);
        flDown8 = (FrameLayout) view.findViewById(R.id.flDown8);

        flLeft0.setOnTouchListener(onClickListener);

    }

    private void initbutSolveOnClickListener() {
        butSolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Solving", "Starting to solve");
                solveCube();
            }
        });
    }

    private void refreshCubeView() {
        FrameLayout side[];

        side = new FrameLayout[]{flLeft0, flLeft1, flLeft2, flLeft3, flLeft4, flLeft5, flLeft6, flLeft7, flLeft8};
        setColoursInSide(side, 0);
        side = new FrameLayout[]{flRight0, flRight1, flRight2, flRight3, flRight4, flRight5, flRight6, flRight7, flRight8};
        setColoursInSide(side, 2);
        side = new FrameLayout[]{flUp0, flUp1, flUp2, flUp3, flUp4, flUp5, flUp6, flUp7, flUp8};
        setColoursInSide(side, 4);
        side = new FrameLayout[]{flDown0, flDown1, flDown2, flDown3, flDown4, flDown5, flDown6, flDown7, flDown8};
        setColoursInSide(side, 5);
        side = new FrameLayout[]{flFront0, flFront1, flFront2, flFront3, flFront4, flFront5, flFront6, flFront7, flFront8};
        setColoursInSide(side, 1);
        side = new FrameLayout[]{flBack0, flBack1, flBack2, flBack3, flBack4, flBack5, flBack6, flBack7, flBack8};
        setColoursInSide(side, 3);


    }

    private void setColoursInSide(FrameLayout[] x, int side) {

        char selectedSide[] = new char[9];
        if (side == 0) selectedSide = left_side;
        else if (side == 1) selectedSide = front_side;
        else if (side == 2) selectedSide = right_side;
        else if (side == 3) selectedSide = back_side;
        else if (side == 4) selectedSide = up_side;
        else if (side == 5) selectedSide = down_side;

        for (int i = 0; i < 9; i++) {
            if (selectedSide[i] == 'y') {
                x[i].setBackgroundColor(Color.YELLOW);
            } else if (selectedSide[i] == 'w') {
                x[i].setBackgroundColor(Color.WHITE);
            } else if (selectedSide[i] == 'b') {
                x[i].setBackgroundColor(Color.BLUE);
            } else if (selectedSide[i] == 'r') {
                x[i].setBackgroundColor(Color.RED);
            } else if (selectedSide[i] == 'g') {
                x[i].setBackgroundColor(Color.GREEN);
            } else if (selectedSide[i] == 'o') {
                x[i].setBackgroundColor(Color.MAGENTA);
            }
        }

    }

    private View.OnTouchListener onClickListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Select Colour");
            String[] colors = new String[]{
                    "Red",
                    "Green",
                    "Blue",
                    "Purple",
                    "Olive"
            };
            final boolean[] checkedColors = new boolean[]{
                    false, // Red
                    true, // Green
                    false, // Blue
                    true, // Purple
                    false // Olive

            };
            final List<String> colorsList = Arrays.asList(colors);
            dialog.setMultiChoiceItems(colors, checkedColors, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    // Update the current focused item's checked status
                    checkedColors[which] = isChecked;

                    // Get the current focused item
                    String currentItem = colorsList.get(which);

                    // Notify the current action
                    Toast.makeText(context, currentItem + " " + isChecked, Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when click positive button
                    for (int i = 0; i < checkedColors.length; i++) {
                        boolean checked = checkedColors[i];
                        if (checked) {
                            Toast.makeText(context, colorsList.get(i), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            AlertDialog builder = dialog.create();
            // Display the alert dialog on interface
            builder.show();
            return false;
        }

    };

    private void solveCube() {
        Solve_Cube solve_cube = new Solve_Cube(left_side, front_side, right_side, back_side, up_side, down_side);
        Log.w("Solving", "Cube Initializing complete");
        tvMoves.setText(solve_cube.Solve_Cube());
        tvMoves.setVisibility(View.VISIBLE);

        Log.w("Solving", "Solved");
    }
}
