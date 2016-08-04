package com.ahcreations.akila.rubikmonster;

import android.util.Log;

public class Solve_Cube {

    //Original Cube
    char[] down_side = {'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y'};
    char[] up_side = {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'};
    char[] front_side = {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
    char[] right_side = {'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r'};
    char[] back_side = {'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g'};
    char[] left_side = {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'};

    // Cube simulation
    char[] r_down_side = {'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y', 'y'};
    char[] r_up_side = {'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w', 'w'};
    char[] r_front_side = {'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
    char[] r_right_side = {'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r'};
    char[] r_back_side = {'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g', 'g'};
    char[] r_left_side = {'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o'};

    char[] move_order = {'R', 'r', 'B', 'b', 'L', 'l', 'F', 'f', 'U', 'u', 'D', 'd'};
    String all_moves = "";

    public Solve_Cube(char[] left_side, char[] front_side, char[] right_side, char[] back_side, char[] up_side, char[] down_side) {
        System.arraycopy(down_side, 0, this.down_side, 0, 9);
        System.arraycopy(up_side, 0, this.up_side, 0, 9);
        System.arraycopy(front_side, 0, this.front_side, 0, 9);
        System.arraycopy(right_side, 0, this.right_side, 0, 9);
        System.arraycopy(back_side, 0, this.back_side, 0, 9);
        System.arraycopy(left_side, 0, this.left_side, 0, 9);

        Log.e("Test", String.valueOf(this.left_side));
        Log.e("Test", String.valueOf(this.front_side));
        Log.e("Test", String.valueOf(this.right_side));
        Log.e("Test", String.valueOf(this.back_side));
        Log.e("Test", String.valueOf(this.up_side));
        Log.e("Test", String.valueOf(this.down_side));

    }

    //Cube Movements
    public void rotate_cube() {

        all_moves = all_moves + 'Q';

        System.arraycopy(front_side, 0, r_front_side, 0, 9);

        System.arraycopy(right_side, 0, front_side, 0, 9);
        System.arraycopy(back_side, 0, right_side, 0, 9);
        System.arraycopy(left_side, 0, back_side, 0, 9);
        System.arraycopy(r_front_side, 0, left_side, 0, 9);

        System.arraycopy(up_side, 0, r_up_side, 0, 9);

        up_side[0] = r_up_side[6];
        up_side[1] = r_up_side[3];
        up_side[2] = r_up_side[0];
        up_side[3] = r_up_side[7];
        up_side[4] = r_up_side[4];
        up_side[5] = r_up_side[1];
        up_side[6] = r_up_side[8];
        up_side[7] = r_up_side[5];
        up_side[8] = r_up_side[2];

        System.arraycopy(down_side, 0, r_down_side, 0, 9);

        down_side[0] = r_down_side[2];
        down_side[1] = r_down_side[5];
        down_side[2] = r_down_side[8];
        down_side[3] = r_down_side[1];
        down_side[4] = r_down_side[4];
        down_side[5] = r_down_side[7];
        down_side[6] = r_down_side[0];
        down_side[7] = r_down_side[3];
        down_side[8] = r_down_side[6];

    }

    public void flip_cube() {
        all_moves = all_moves + 'X';

        System.arraycopy(left_side, 0, r_left_side, 0, 9);
        System.arraycopy(right_side, 0, r_right_side, 0, 9);

        System.arraycopy(front_side, 0, r_front_side, 0, 9);
        System.arraycopy(up_side, 0, r_up_side, 0, 9);

        System.arraycopy(down_side, 0, up_side, 0, 9);
        System.arraycopy(r_up_side, 0, down_side, 0, 9);

        front_side[0] = back_side[8];
        front_side[1] = back_side[7];
        front_side[2] = back_side[6];
        front_side[3] = back_side[5];
        front_side[4] = back_side[4];
        front_side[5] = back_side[3];
        front_side[6] = back_side[2];
        front_side[7] = back_side[1];
        front_side[8] = back_side[0];

        back_side[0] = r_front_side[8];
        back_side[1] = r_front_side[7];
        back_side[2] = r_front_side[6];
        back_side[3] = r_front_side[5];
        back_side[4] = r_front_side[4];
        back_side[5] = r_front_side[3];
        back_side[6] = r_front_side[2];
        back_side[7] = r_front_side[1];
        back_side[8] = r_front_side[0];

        left_side[0] = r_left_side[8];
        left_side[1] = r_left_side[7];
        left_side[2] = r_left_side[6];
        left_side[3] = r_left_side[5];
        left_side[4] = r_left_side[4];
        left_side[5] = r_left_side[3];
        left_side[6] = r_left_side[2];
        left_side[7] = r_left_side[1];
        left_side[8] = r_left_side[0];

        right_side[0] = r_right_side[8];
        right_side[1] = r_right_side[7];
        right_side[2] = r_right_side[6];
        right_side[3] = r_right_side[5];
        right_side[4] = r_right_side[4];
        right_side[5] = r_right_side[3];
        right_side[6] = r_right_side[2];
        right_side[7] = r_right_side[1];
        right_side[8] = r_right_side[0];
    }

    public void left() {
        all_moves = all_moves + 'L';

        System.arraycopy(down_side, 0, r_down_side, 0, 9);
        System.arraycopy(front_side, 0, r_front_side, 0, 9);
        System.arraycopy(up_side, 0, r_up_side, 0, 9);
        System.arraycopy(back_side, 0, r_back_side, 0, 9);

        down_side[0] = r_front_side[0];
        down_side[3] = r_front_side[3];
        down_side[6] = r_front_side[6];

        front_side[0] = r_up_side[0];
        front_side[3] = r_up_side[3];
        front_side[6] = r_up_side[6];

        up_side[0] = r_back_side[8];
        up_side[3] = r_back_side[5];
        up_side[6] = r_back_side[2];

        back_side[8] = r_down_side[0];
        back_side[5] = r_down_side[3];
        back_side[2] = r_down_side[6];

        System.arraycopy(left_side, 0, r_left_side, 0, 9);

        left_side[0] = r_left_side[6];
        left_side[1] = r_left_side[3];
        left_side[2] = r_left_side[0];
        left_side[3] = r_left_side[7];
        left_side[4] = r_left_side[4];
        left_side[5] = r_left_side[1];
        left_side[6] = r_left_side[8];
        left_side[7] = r_left_side[5];
        left_side[8] = r_left_side[2];
    }

    public void left_inverted() {
        all_moves = all_moves + 'l';

        System.arraycopy(down_side, 0, r_down_side, 0, 9);
        System.arraycopy(front_side, 0, r_front_side, 0, 9);
        System.arraycopy(up_side, 0, r_up_side, 0, 9);
        System.arraycopy(back_side, 0, r_back_side, 0, 9);

        front_side[0] = r_down_side[0];
        front_side[3] = r_down_side[3];
        front_side[6] = r_down_side[6];

        up_side[0] = r_front_side[0];
        up_side[3] = r_front_side[3];
        up_side[6] = r_front_side[6];

        back_side[8] = r_up_side[0];
        back_side[5] = r_up_side[3];
        back_side[2] = r_up_side[6];

        down_side[0] = r_back_side[8];
        down_side[3] = r_back_side[5];
        down_side[6] = r_back_side[2];

        System.arraycopy(left_side, 0, r_left_side, 0, 9);

        left_side[0] = r_left_side[2];
        left_side[1] = r_left_side[5];
        left_side[2] = r_left_side[8];
        left_side[3] = r_left_side[1];
        left_side[4] = r_left_side[4];
        left_side[5] = r_left_side[7];
        left_side[6] = r_left_side[0];
        left_side[7] = r_left_side[3];
        left_side[8] = r_left_side[6];
    }

    public void right() {
        all_moves = all_moves + 'R';

        System.arraycopy(down_side, 0, r_down_side, 0, 9);
        System.arraycopy(front_side, 0, r_front_side, 0, 9);
        System.arraycopy(up_side, 0, r_up_side, 0, 9);
        System.arraycopy(back_side, 0, r_back_side, 0, 9);

        down_side[2] = r_back_side[6];
        down_side[5] = r_back_side[3];
        down_side[8] = r_back_side[0];

        front_side[2] = r_down_side[2];
        front_side[5] = r_down_side[5];
        front_side[8] = r_down_side[8];

        up_side[2] = r_front_side[2];
        up_side[5] = r_front_side[5];
        up_side[8] = r_front_side[8];

        back_side[6] = r_up_side[2];
        back_side[3] = r_up_side[5];
        back_side[0] = r_up_side[8];

        System.arraycopy(right_side, 0, r_right_side, 0, 9);

        // reassign colours on face
        right_side[0] = r_right_side[6];
        right_side[1] = r_right_side[3];
        right_side[2] = r_right_side[0];
        right_side[3] = r_right_side[7];
        right_side[4] = r_right_side[4];
        right_side[5] = r_right_side[1];
        right_side[6] = r_right_side[8];
        right_side[7] = r_right_side[5];
        right_side[8] = r_right_side[2];
    }

    public void right_inverted() {
        all_moves = all_moves + 'r';

        System.arraycopy(down_side, 0, r_down_side, 0, 9);
        System.arraycopy(front_side, 0, r_front_side, 0, 9);
        System.arraycopy(up_side, 0, r_up_side, 0, 9);
        System.arraycopy(back_side, 0, r_back_side, 0, 9);

        down_side[2] = r_front_side[2];
        down_side[5] = r_front_side[5];
        down_side[8] = r_front_side[8];

        front_side[2] = r_up_side[2];
        front_side[5] = r_up_side[5];
        front_side[8] = r_up_side[8];

        up_side[2] = r_back_side[6];
        up_side[5] = r_back_side[3];
        up_side[8] = r_back_side[0];

        back_side[6] = r_down_side[2];
        back_side[3] = r_down_side[5];
        back_side[0] = r_down_side[8];

        System.arraycopy(right_side, 0, r_right_side, 0, 9);

        // reassign colours on face
        right_side[0] = r_right_side[2];
        right_side[1] = r_right_side[5];
        right_side[2] = r_right_side[8];
        right_side[3] = r_right_side[1];
        right_side[4] = r_right_side[4];
        right_side[5] = r_right_side[7];
        right_side[6] = r_right_side[0];
        right_side[7] = r_right_side[3];
        right_side[8] = r_right_side[6];
    }

    public void down() {
        all_moves = all_moves + 'D';

        System.arraycopy(front_side, 0, r_front_side, 0, 9);
        System.arraycopy(left_side, 0, r_left_side, 0, 9);
        System.arraycopy(back_side, 0, r_back_side, 0, 9);
        System.arraycopy(right_side, 0, r_right_side, 0, 9);

        right_side[6] = r_front_side[6];
        right_side[7] = r_front_side[7];
        right_side[8] = r_front_side[8];

        back_side[6] = r_right_side[6];
        back_side[7] = r_right_side[7];
        back_side[8] = r_right_side[8];

        left_side[6] = r_back_side[6];
        left_side[7] = r_back_side[7];
        left_side[8] = r_back_side[8];

        front_side[6] = r_left_side[6];
        front_side[7] = r_left_side[7];
        front_side[8] = r_left_side[8];

        System.arraycopy(down_side, 0, r_down_side, 0, 9);

        down_side[0] = r_down_side[6];
        down_side[1] = r_down_side[3];
        down_side[2] = r_down_side[0];
        down_side[3] = r_down_side[7];
        down_side[4] = r_down_side[4];
        down_side[5] = r_down_side[1];
        down_side[6] = r_down_side[8];
        down_side[7] = r_down_side[5];
        down_side[8] = r_down_side[2];

    }

    public void down_inverted() {
        all_moves = all_moves + 'd';

        System.arraycopy(front_side, 0, r_front_side, 0, 9);
        System.arraycopy(left_side, 0, r_left_side, 0, 9);
        System.arraycopy(back_side, 0, r_back_side, 0, 9);
        System.arraycopy(right_side, 0, r_right_side, 0, 9);

        back_side[6] = r_left_side[6];
        back_side[7] = r_left_side[7];
        back_side[8] = r_left_side[8];

        left_side[6] = r_front_side[6];
        left_side[7] = r_front_side[7];
        left_side[8] = r_front_side[8];

        front_side[6] = r_right_side[6];
        front_side[7] = r_right_side[7];
        front_side[8] = r_right_side[8];

        right_side[6] = r_back_side[6];
        right_side[7] = r_back_side[7];
        right_side[8] = r_back_side[8];

        System.arraycopy(down_side, 0, r_down_side, 0, 9);

        down_side[0] = r_down_side[2];
        down_side[1] = r_down_side[5];
        down_side[2] = r_down_side[8];
        down_side[3] = r_down_side[1];
        down_side[4] = r_down_side[4];
        down_side[5] = r_down_side[7];
        down_side[6] = r_down_side[0];
        down_side[7] = r_down_side[3];
        down_side[8] = r_down_side[6];
    }

    public void up() {
        all_moves = all_moves + 'U';

        System.arraycopy(front_side, 0, r_front_side, 0, 9);
        System.arraycopy(left_side, 0, r_left_side, 0, 9);
        System.arraycopy(back_side, 0, r_back_side, 0, 9);
        System.arraycopy(right_side, 0, r_right_side, 0, 9);

        back_side[0] = r_left_side[0];
        back_side[1] = r_left_side[1];
        back_side[2] = r_left_side[2];

        right_side[0] = r_back_side[0];
        right_side[1] = r_back_side[1];
        right_side[2] = r_back_side[2];

        front_side[0] = r_right_side[0];
        front_side[1] = r_right_side[1];
        front_side[2] = r_right_side[2];

        left_side[0] = r_front_side[0];
        left_side[1] = r_front_side[1];
        left_side[2] = r_front_side[2];

        System.arraycopy(up_side, 0, r_up_side, 0, 9);

        up_side[0] = r_up_side[6];
        up_side[1] = r_up_side[3];
        up_side[2] = r_up_side[0];
        up_side[3] = r_up_side[7];
        up_side[4] = r_up_side[4];
        up_side[5] = r_up_side[1];
        up_side[6] = r_up_side[8];
        up_side[7] = r_up_side[5];
        up_side[8] = r_up_side[2];

    }

    public void up_inverted() {
        all_moves = all_moves + 'u';

        System.arraycopy(front_side, 0, r_front_side, 0, 9);
        System.arraycopy(left_side, 0, r_left_side, 0, 9);
        System.arraycopy(back_side, 0, r_back_side, 0, 9);
        System.arraycopy(right_side, 0, r_right_side, 0, 9);

        left_side[0] = r_back_side[0];
        left_side[1] = r_back_side[1];
        left_side[2] = r_back_side[2];

        back_side[0] = r_right_side[0];
        back_side[1] = r_right_side[1];
        back_side[2] = r_right_side[2];

        right_side[0] = r_front_side[0];
        right_side[1] = r_front_side[1];
        right_side[2] = r_front_side[2];

        front_side[0] = r_left_side[0];
        front_side[1] = r_left_side[1];
        front_side[2] = r_left_side[2];

        System.arraycopy(up_side, 0, r_up_side, 0, 9);

        up_side[0] = r_up_side[2];
        up_side[1] = r_up_side[5];
        up_side[2] = r_up_side[8];
        up_side[3] = r_up_side[1];
        up_side[4] = r_up_side[4];
        up_side[5] = r_up_side[7];
        up_side[6] = r_up_side[0];
        up_side[7] = r_up_side[3];
        up_side[8] = r_up_side[6];
    }

    public void front() {
        all_moves = all_moves + 'F';

        System.arraycopy(down_side, 0, r_down_side, 0, 9);
        System.arraycopy(left_side, 0, r_left_side, 0, 9);
        System.arraycopy(up_side, 0, r_up_side, 0, 9);
        System.arraycopy(right_side, 0, r_right_side, 0, 9);

        down_side[2] = r_right_side[0];
        down_side[1] = r_right_side[3];
        down_side[0] = r_right_side[6];

        right_side[0] = r_up_side[6];
        right_side[3] = r_up_side[7];
        right_side[6] = r_up_side[8];

        up_side[6] = r_left_side[8];
        up_side[7] = r_left_side[5];
        up_side[8] = r_left_side[2];

        left_side[2] = r_down_side[0];
        left_side[5] = r_down_side[1];
        left_side[8] = r_down_side[2];

        System.arraycopy(front_side, 0, r_front_side, 0, 9);

        front_side[0] = r_front_side[6];
        front_side[1] = r_front_side[3];
        front_side[2] = r_front_side[0];
        front_side[3] = r_front_side[7];
        front_side[4] = r_front_side[4];
        front_side[5] = r_front_side[1];
        front_side[6] = r_front_side[8];
        front_side[7] = r_front_side[5];
        front_side[8] = r_front_side[2];
    }

    public void front_inverted() {
        all_moves = all_moves + 'f';

        System.arraycopy(down_side, 0, r_down_side, 0, 9);
        System.arraycopy(left_side, 0, r_left_side, 0, 9);
        System.arraycopy(up_side, 0, r_up_side, 0, 9);
        System.arraycopy(right_side, 0, r_right_side, 0, 9);

        right_side[6] = r_down_side[0];
        right_side[3] = r_down_side[1];
        right_side[0] = r_down_side[2];

        up_side[6] = r_right_side[0];
        up_side[7] = r_right_side[3];
        up_side[8] = r_right_side[6];

        left_side[8] = r_up_side[6];
        left_side[5] = r_up_side[7];
        left_side[2] = r_up_side[8];

        down_side[2] = r_left_side[8];
        down_side[1] = r_left_side[5];
        down_side[0] = r_left_side[2];

        System.arraycopy(front_side, 0, r_front_side, 0, 9);

        front_side[0] = r_front_side[2];
        front_side[1] = r_front_side[5];
        front_side[2] = r_front_side[8];
        front_side[3] = r_front_side[1];
        front_side[4] = r_front_side[4];
        front_side[5] = r_front_side[7];
        front_side[6] = r_front_side[0];
        front_side[7] = r_front_side[3];
        front_side[8] = r_front_side[6];
    }

    public void back() {
        all_moves = all_moves + 'B';

        System.arraycopy(left_side, 0, r_left_side, 0, 9);
        System.arraycopy(up_side, 0, r_up_side, 0, 9);
        System.arraycopy(right_side, 0, r_right_side, 0, 9);
        System.arraycopy(down_side, 0, r_down_side, 0, 9);

        left_side[0] = r_up_side[2];
        left_side[3] = r_up_side[1];
        left_side[6] = r_up_side[0];

        down_side[6] = r_left_side[0];
        down_side[7] = r_left_side[3];
        down_side[8] = r_left_side[6];

        up_side[0] = r_right_side[2];
        up_side[1] = r_right_side[5];
        up_side[2] = r_right_side[8];

        right_side[2] = r_down_side[8];
        right_side[5] = r_down_side[7];
        right_side[8] = r_down_side[6];

        System.arraycopy(back_side, 0, r_back_side, 0, 9);

        back_side[0] = r_back_side[6];
        back_side[1] = r_back_side[3];
        back_side[2] = r_back_side[0];
        back_side[3] = r_back_side[7];
        back_side[4] = r_back_side[4];
        back_side[5] = r_back_side[1];
        back_side[6] = r_back_side[8];
        back_side[7] = r_back_side[5];
        back_side[8] = r_back_side[2];
    }

    void back_inverted() {
        all_moves = all_moves + 'b';

        System.arraycopy(left_side, 0, r_left_side, 0, 9);
        System.arraycopy(up_side, 0, r_up_side, 0, 9);
        System.arraycopy(right_side, 0, r_right_side, 0, 9);
        System.arraycopy(down_side, 0, r_down_side, 0, 9);

        up_side[0] = r_left_side[6];
        up_side[1] = r_left_side[3];
        up_side[2] = r_left_side[0];

        left_side[0] = r_down_side[6];
        left_side[3] = r_down_side[7];
        left_side[6] = r_down_side[8];

        right_side[2] = r_up_side[0];
        right_side[5] = r_up_side[1];
        right_side[8] = r_up_side[2];

        down_side[8] = r_right_side[2];
        down_side[7] = r_right_side[5];
        down_side[6] = r_right_side[8];

        System.arraycopy(back_side, 0, r_back_side, 0, 9);

        back_side[0] = r_back_side[2];
        back_side[1] = r_back_side[5];
        back_side[2] = r_back_side[8];
        back_side[3] = r_back_side[1];
        back_side[4] = r_back_side[4];
        back_side[5] = r_back_side[7];
        back_side[6] = r_back_side[0];
        back_side[7] = r_back_side[3];
        back_side[8] = r_back_side[6];
    }

    //get Colours of Side
    public char[] getSide(char side) {
        if (side == 'y') {
            return down_side;
        } else if (side == 'w') {
            return up_side;
        } else if (side == 'g') {
            return front_side;
        } else if (side == 'r') {
            return right_side;
        } else if (side == 'b') {
            return back_side;
        } else if (side == 'o') {
            return left_side;
        }
        return null;
    }

    //Pecies Second Colours
    private char getEdgeSecondColour(char color, int place) {
        if (color == 'g') {
            if (place == 1) {
                return up_side[7];
            }
            if (place == 3) {
                return left_side[5];
            }
            if (place == 5) {
                return right_side[3];
            }
            if (place == 7) {
                return down_side[1];
            }
        } else if (color == 'r') {
            if (place == 1) {
                return up_side[5];
            }
            if (place == 3) {
                return front_side[5];
            }
            if (place == 5) {
                return back_side[3];
            }
            if (place == 7) {
                return down_side[5];
            }
        } else if (color == 'b') {
            if (place == 1) {
                return up_side[1];
            }
            if (place == 3) {
                return right_side[5];
            }
            if (place == 5) {
                return left_side[3];
            }
            if (place == 7) {
                return down_side[7];
            }
        } else if (color == 'o') {
            if (place == 1) {
                return up_side[3];
            }
            if (place == 3) {
                return back_side[5];
            }
            if (place == 5) {
                return front_side[3];
            }
            if (place == 7) {
                return down_side[3];
            }
        } else if (color == 'w') {
            if (place == 1) {
                return back_side[1];
            }
            if (place == 3) {
                return left_side[1];
            }
            if (place == 5) {
                return right_side[1];
            }
            if (place == 7) {
                return front_side[1];
            }
        } else if (color == 'y') {
            if (place == 1) {
                return front_side[7];
            }
            if (place == 3) {
                return left_side[7];
            }
            if (place == 5) {
                return right_side[7];
            }
            if (place == 7) {
                return back_side[7];
            }
        }
        return 0;
    }

    private char getConnerSecondColours(char side, int place) {
        if (side == 'f') {
            if (place == 0) {
                return left_side[2];
            }
            if (place == 2) {
                return up_side[8];
            }
            if (place == 6) {
                return down_side[0];
            }
            if (place == 8) {
                return right_side[6];
            }
        } else if (side == 'r') {
            if (place == 0) {
                return front_side[2];
            }
            if (place == 2) {
                return up_side[2];
            }
            if (place == 6) {
                return down_side[2];
            }
            if (place == 8) {
                return back_side[6];
            }
        } else if (side == 'b') {
            if (place == 0) {
                return right_side[2];
            }
            if (place == 2) {
                return up_side[0];
            }
            if (place == 6) {
                return down_side[8];
            }
            if (place == 8) {
                return left_side[6];
            }
        } else if (side == 'l') {
            if (place == 0) {
                return back_side[2];
            }
            if (place == 2) {
                return up_side[6];
            }
            if (place == 6) {
                return down_side[6];
            }
            if (place == 8) {
                return front_side[6];
            }
        } else if (side == 'u') {
            if (place == 0) {
                return left_side[0];
            }
            if (place == 2) {
                return back_side[0];
            }
            if (place == 6) {
                return front_side[0];
            }
            if (place == 8) {
                return right_side[0];
            }
        } else if (side == 'd') {
            if (place == 0) {
                return left_side[8];
            }
            if (place == 2) {
                return front_side[8];
            }
            if (place == 6) {
                return back_side[8];
            }
            if (place == 8) {
                return right_side[8];
            }
        }
        return 0;
    }

    //Make Moves
    private void doMove(String code) {
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == 'L') {
                left();
            } else if (code.charAt(i) == 'l') {
                left_inverted();
            } else if (code.charAt(i) == 'R') {
                right();
            } else if (code.charAt(i) == 'r') {
                right_inverted();
            } else if (code.charAt(i) == 'U') {
                up();
            } else if (code.charAt(i) == 'u') {
                up_inverted();
            } else if (code.charAt(i) == 'D') {
                down();
            } else if (code.charAt(i) == 'd') {
                down_inverted();
            } else if (code.charAt(i) == 'F') {
                front();
            } else if (code.charAt(i) == 'f') {
                front_inverted();
            } else if (code.charAt(i) == 'B') {
                back();
            } else if (code.charAt(i) == 'b') {
                back_inverted();
            }
        }
    }

    //Get current Side colours
    private char getSideCenterColour(char side) {
        if (side == 'l') {
            return left_side[4];
        } else if (side == 'r') {
            return right_side[4];
        } else if (side == 'b') {
            return back_side[4];
        } else if (side == 'f') {
            return front_side[4];
        } else if (side == 'u') {
            return up_side[4];
        } else if (side == 'd') {
            return down_side[4];
        } else {
            return 0;
        }
    }

    public int solve_stage = 1;
    boolean cube_solved = true;

    //Solving Cube
    public void cube_decide_cross() {

        if (solve_stage == 1 && up_side[1] == 'w' && up_side[3] == 'w' && up_side[5] == 'w' && up_side[7] == 'w') {
            solve_stage = 2;
        } else if (solve_stage == 1) {
            if (front_side[3] == 'w') {
                if (getEdgeSecondColour('g', 3) == getSideCenterColour('l')) {
                    doMove("l");
                } else if (getEdgeSecondColour('g', 3) == getSideCenterColour('r')) {
                    doMove("UUlUU");
                } else if (getEdgeSecondColour('g', 3) == getSideCenterColour('f')) {
                    doMove("Ulu");
                } else if (getEdgeSecondColour('g', 3) == getSideCenterColour('b')) {
                    doMove("ulU");
                }
            } else if (front_side[5] == 'w') {
                if (getEdgeSecondColour('g', 5) == getSideCenterColour('l')) {
                    doMove("UURUU");
                } else if (getEdgeSecondColour('g', 5) == getSideCenterColour('r')) {
                    doMove("R");
                } else if (getEdgeSecondColour('g', 5) == getSideCenterColour('f')) {
                    doMove("uRU");
                } else if (getEdgeSecondColour('g', 5) == getSideCenterColour('b')) {
                    doMove("URu");
                }
            } else if (front_side[1] == 'w') {
                if (getEdgeSecondColour('g', 1) == getSideCenterColour('l')) {
                    doMove("fl");
                } else if (getEdgeSecondColour('g', 1) == getSideCenterColour('r')) {
                    doMove("FR");
                } else if (getEdgeSecondColour('g', 1) == getSideCenterColour('f')) {
                    doMove("FuRU");
                } else if (getEdgeSecondColour('g', 1) == getSideCenterColour('b')) {
                    doMove("FURu");
                }
            } else if (front_side[7] == 'w') {
                if (getEdgeSecondColour('g', 7) == getSideCenterColour('l')) {
                    doMove("Flf");
                } else if (getEdgeSecondColour('g', 7) == getSideCenterColour('r')) {
                    doMove("fRF");
                } else if (getEdgeSecondColour('g', 7) == getSideCenterColour('f')) {
                    doMove("FUlu");
                } else if (getEdgeSecondColour('g', 7) == getSideCenterColour('b')) {
                    doMove("FulUf");
                }
            } else if (down_side[3] == 'w') {
                if (getEdgeSecondColour('y', 3) == getSideCenterColour('l')) {
                    doMove("LL");
                } else if (getEdgeSecondColour('y', 3) == getSideCenterColour('r')) {
                    doMove("DDRR");
                } else if (getEdgeSecondColour('y', 3) == getSideCenterColour('f')) {
                    doMove("DFF");
                } else if (getEdgeSecondColour('y', 3) == getSideCenterColour('b')) {
                    doMove("dBB");
                }
            } else if (down_side[5] == 'w') {
                if (getEdgeSecondColour('y', 5) == getSideCenterColour('l')) {
                    doMove("DDLL");
                } else if (getEdgeSecondColour('y', 5) == getSideCenterColour('r')) {
                    doMove("RR");
                } else if (getEdgeSecondColour('y', 5) == getSideCenterColour('f')) {
                    doMove("dFF");
                } else if (getEdgeSecondColour('y', 5) == getSideCenterColour('b')) {
                    doMove("DBB");
                }
            } else if (down_side[1] == 'w') {
                if (getEdgeSecondColour('y', 1) == getSideCenterColour('l')) {
                    doMove("dLL");
                } else if (getEdgeSecondColour('y', 1) == getSideCenterColour('r')) {
                    doMove("DRR");
                } else if (getEdgeSecondColour('y', 1) == getSideCenterColour('f')) {
                    doMove("FF");
                } else if (getEdgeSecondColour('y', 1) == getSideCenterColour('b')) {
                    doMove("DDBB");
                }
            } else if (down_side[7] == 'w') {
                if (getEdgeSecondColour('y', 7) == getSideCenterColour('l')) {
                    doMove("DLL");
                } else if (getEdgeSecondColour('y', 7) == getSideCenterColour('r')) {
                    doMove("dRR");
                } else if (getEdgeSecondColour('y', 7) == getSideCenterColour('f')) {
                    doMove("DDFF");
                } else if (getEdgeSecondColour('y', 7) == getSideCenterColour('b')) {
                    doMove("BB");
                }
            } else {
                rotate_cube();
            }

        }
    }

    public void cube_decide_corners() {

        if (solve_stage == 2 && up_side[0] == 'w' && up_side[2] == 'w' && up_side[6] == 'w' && up_side[8] == 'w'
                && left_side[0] == getSideCenterColour('l') && left_side[2] == getSideCenterColour('l')
                && right_side[0] == getSideCenterColour('r') && right_side[2] == getSideCenterColour('r')) {
            solve_stage = 3;
            flip_cube();

        } else if (solve_stage == 2) {
            if (front_side[0] == 'w') {
                if (getConnerSecondColours('f', 0) == getSideCenterColour('l')) {
                    doMove("BfdbF");
                } else if (getConnerSecondColours('f', 0) == getSideCenterColour('r')) {
                    doMove("fDDFFdf");
                } else if (getConnerSecondColours('f', 0) == getSideCenterColour('f')) {
                    doMove("fdFDfdF");
                } else if (getConnerSecondColours('f', 0) == getSideCenterColour('b')) {
                    doMove("fRDDrF");
                }
            } else if (front_side[2] == 'w') {
                if (getConnerSecondColours('f', 2) == getSideCenterColour('l')) {
                    doMove("FlDDLf");
                } else if (getConnerSecondColours('f', 2) == getSideCenterColour('r')) {
                    doMove("FDfdFDf");
                } else if (getConnerSecondColours('f', 2) == getSideCenterColour('f')) {
                    doMove("FDFFDDF");
                } else if (getConnerSecondColours('f', 2) == getSideCenterColour('b')) {
                    doMove("FbDBf");
                }
            } else if (front_side[6] == 'w') {
                if (getConnerSecondColours('f', 6) == getSideCenterColour('l')) {
                    doMove("Bdb");
                } else if (getConnerSecondColours('f', 6) == getSideCenterColour('r')) {
                    doMove("DrdR");
                } else if (getConnerSecondColours('f', 6) == getSideCenterColour('f')) {
                    doMove("fdF");
                } else if (getConnerSecondColours('f', 6) == getSideCenterColour('b')) {
                    doMove("RDDr");
                }
            } else if (front_side[8] == 'w') {
                if (getConnerSecondColours('f', 8) == getSideCenterColour('l')) {
                    doMove("lDDL");
                } else if (getConnerSecondColours('f', 8) == getSideCenterColour('r')) {
                    doMove("drDR");
                } else if (getConnerSecondColours('f', 8) == getSideCenterColour('f')) {
                    doMove("dLDl");
                } else if (getConnerSecondColours('f', 8) == getSideCenterColour('b')) {
                    doMove("bDB");
                }
            } else if (down_side[0] == 'w') {
                if (getConnerSecondColours('d', 0) == getSideCenterColour('l')) {
                    doMove("dBdblDDL");
                } else if (getConnerSecondColours('d', 0) == getSideCenterColour('r')) {
                    doMove("DrDRRdr");
                } else if (getConnerSecondColours('d', 0) == getSideCenterColour('f')) {
                    doMove("LdlfDDF");
                } else if (getConnerSecondColours('d', 0) == getSideCenterColour('b')) {
                    doMove("DDRdrbDDB");
                }
            } else if (down_side[2] == 'w') {
                doMove("d");
            } else if (down_side[6] == 'w') {
                doMove("D");
            } else if (down_side[8] == 'w') {
                doMove("DD");
            } else {
                rotate_cube();
            }

        }
    }

    public void cube_decide_edges() {
        if (solve_stage == 3 && front_side[3] == getSideCenterColour('f') && front_side[5] == getSideCenterColour('f')
                && left_side[3] == getSideCenterColour('l') && left_side[5] == getSideCenterColour('l')
                && right_side[3] == getSideCenterColour('r') && right_side[5] == getSideCenterColour('r')
                && back_side[3] == getSideCenterColour('b') && back_side[5] == getSideCenterColour('b')) {
            solve_stage = 4;

        } else if (solve_stage == 3) {
            char upColor, downColor;
            if (front_side[1] != up_side[4] && up_side[7] != up_side[4]) {
                upColor = up_side[7];
                downColor = front_side[1];

                if (upColor == left_side[4]) {
                    up_inverted();
                } else if (upColor == right_side[4]) {
                    up();
                } else if (upColor == front_side[4]) {
                    up();
                    up();
                }
                while (downColor != front_side[4]) {
                    rotate_cube();
                }
                if (upColor == left_side[4]) {
                    doMove("lULUFuf");
                } else {
                    doMove("RurufUF");
                }
            } else if (left_side[1] != up_side[4] && up_side[3] != up_side[4]) {
                doMove("u");
                upColor = up_side[7];
                downColor = front_side[1];

                if (upColor == left_side[4]) {
                    up_inverted();
                } else if (upColor == right_side[4]) {
                    up();
                } else if (upColor == front_side[4]) {
                    up();
                    up();
                }
                while (downColor != front_side[4]) {
                    rotate_cube();
                }
                if (upColor == left_side[4]) {
                    doMove("lULUFuf");
                } else {
                    doMove("RurufUF");
                }
            } else if (right_side[1] != up_side[4] && up_side[5] != up_side[4]) {
                doMove("U");
                upColor = up_side[7];
                downColor = front_side[1];

                if (upColor == left_side[4]) {
                    up_inverted();
                } else if (upColor == right_side[4]) {
                    up();
                } else if (upColor == front_side[4]) {
                    up();
                    up();
                }
                while (downColor != front_side[4]) {
                    rotate_cube();
                }
                if (upColor == left_side[4]) {
                    doMove("lULUFuf");
                } else {
                    doMove("RurufUF");
                }
            } else if (back_side[1] != up_side[4] && up_side[1] != up_side[4]) {
                doMove("UU");
                upColor = up_side[7];
                downColor = front_side[1];

                if (upColor == left_side[4]) {
                    up_inverted();
                } else if (upColor == right_side[4]) {
                    up();
                } else if (upColor == front_side[4]) {
                    up();
                    up();
                }
                while (downColor != front_side[4]) {
                    rotate_cube();
                }
                if (upColor == left_side[4]) {
                    doMove("lULUFuf");
                } else {
                    doMove("RurufUF");
                }
            } else {
                while (front_side[5] == front_side[4] && left_side[3] == left_side[4]) {
                    rotate_cube();
                }
                doMove("RurufUF");
            }
        }
    }

    public void cube_decide_oll_cross() {
        if (solve_stage == 4 && up_side[1] == up_side[4] && up_side[3] == up_side[4] && up_side[5] == up_side[4] && up_side[7] == up_side[4]) {
            solve_stage = 5;
        } else if (solve_stage == 4) {
            if (up_side[1] != up_side[4] && up_side[3] != up_side[4] && up_side[5] != up_side[4] && up_side[7] != up_side[4]) {
                doMove("FRUruf");
                rotate_cube();
                rotate_cube();
                doMove("rufUFR");
            } else if (up_side[1] == up_side[4] && up_side[7] == up_side[4]) {
                rotate_cube();
                doMove("FRUruf");
            } else if (up_side[3] == up_side[4] && up_side[5] == up_side[4]) {
                doMove("FRUruf");
            } else {
                while (up_side[1] != up_side[4] && up_side[3] != up_side[4]) {
                    rotate_cube();
                }
                doMove("rufUFR");
            }
        }
    }

    public void cube_decide_oil_corners() {
        if (solve_stage == 5 && up_side[0] == up_side[4] && up_side[2] == up_side[4] && up_side[6] == up_side[4] && up_side[8] == up_side[4]) {
            solve_stage = 6;
        } else if (solve_stage == 5) {
            if (up_side[6] == up_side[4] && front_side[2] == up_side[4] && right_side[2] == up_side[4] && back_side[2] == up_side[4]) {
                doMove("RUrURUUr");
            } else if (up_side[2] == up_side[4] && front_side[0] == up_side[4] && right_side[0] == up_side[4] && left_side[0] == up_side[4]) {
                doMove("RUUruRur");
            } else if (up_side[0] == up_side[4] && up_side[6] == up_side[4] && front_side[2] == up_side[4] && back_side[0] == up_side[4]) {
                doMove("rfLFRflF");
            } else if (up_side[0] == up_side[4] && up_side[2] == up_side[4] && front_side[0] == up_side[4] && front_side[2] == up_side[4]) {
                doMove("RRDrUURdrUUr");
            } else if (left_side[0] == up_side[4] && left_side[2] == up_side[4] && right_side[0] == up_side[4] && right_side[2] == up_side[4]) {
                doMove("RUrURurURUUr");
            } else if (left_side[0] == up_side[4] && left_side[2] == up_side[4] && front_side[2] == up_side[4] && back_side[0] == up_side[4]) {
                doMove("RUURRuRRuRRUUR");
            } else if (up_side[0] == up_side[4] && up_side[8] == up_side[4] && back_side[0] == up_side[4] && left_side[2] == up_side[4]) {
                doMove("rflFRfLF");
            } else {
                rotate_cube();
            }
        }
    }

    public void cube_decide_pal_corners() {
        if (solve_stage == 6 && left_side[0] == left_side[2] && right_side[0] == right_side[2] && front_side[0] == front_side[2] && back_side[0] == back_side[2]) {
            while (front_side[0] != front_side[4]) {
                doMove("U");
            }
            solve_stage = 7;
        } else if (solve_stage == 6) {
            if (left_side[0] != left_side[2] && right_side[0] != right_side[2] && front_side[0] != front_side[2] && back_side[0] != back_side[2]) {
                doMove("rFrBBRfrBBRR");
            } else {
                while (back_side[0] != back_side[2]) {
                    rotate_cube();
                }
                doMove("rFrBBRfrBBRR");
            }
        }
    }

    public void cube_decide_pal_edges() {
        if (solve_stage == 7 && left_side[1] == left_side[4] && right_side[1] == right_side[4] && front_side[1] == front_side[4] && back_side[1] == back_side[4]) {
            solve_stage = 8;
        } else if (solve_stage == 7) {
            if (left_side[1] != left_side[4] && right_side[1] != right_side[4] && front_side[1] != front_side[4] && back_side[1] != back_side[4]) {
                doMove("RuRURURuruRR");
            } else {
                while (back_side[1] != back_side[4]) {
                    rotate_cube();
                }
                doMove("RuRURURuruRR");
            }
        }
    }

    public String Solve_Cube() {
        while (solve_stage != 8) {
            cube_decide_cross();
            cube_decide_corners();
            cube_decide_edges();
            cube_decide_oll_cross();
            cube_decide_oil_corners();
            cube_decide_pal_corners();
            cube_decide_pal_edges();
        }
        all_moves = Remove_Rotates();
        all_moves = Remove_Unwanted_Moves();
        AddSpaces();
        return all_moves;
    }

    private String Remove_Rotates() {
        String tempMoves = "";
        for (int i = 0; i < all_moves.length(); i++) {
            if (all_moves.charAt(i) == 'Q') {
                char[] first_two_colours = {'n', 'n'};
                first_two_colours[0] = move_order[0];
                first_two_colours[1] = move_order[1];

                move_order[0] = move_order[2];
                move_order[1] = move_order[3];
                move_order[2] = move_order[4];
                move_order[3] = move_order[5];
                move_order[4] = move_order[6];
                move_order[5] = move_order[7];
                move_order[6] = first_two_colours[0];
                move_order[7] = first_two_colours[1];
            } else if (all_moves.charAt(i) == 'X') {
                char[] first_two_colours = {'n', 'n'};
                first_two_colours[0] = move_order[2];
                first_two_colours[1] = move_order[3];

                move_order[2] = move_order[6];
                move_order[3] = move_order[7];
                move_order[6] = first_two_colours[0];
                move_order[7] = first_two_colours[1];

                first_two_colours[0] = move_order[8];
                first_two_colours[1] = move_order[9];

                move_order[8] = move_order[10];
                move_order[9] = move_order[11];
                move_order[10] = first_two_colours[0];
                move_order[11] = first_two_colours[1];
            } else {
                if (all_moves.charAt(i) == 'R') {
                    tempMoves = tempMoves + move_order[0];
                } else if (all_moves.charAt(i) == 'r') {
                    tempMoves = tempMoves + move_order[1];
                } else if (all_moves.charAt(i) == 'B') {
                    tempMoves = tempMoves + move_order[2];
                } else if (all_moves.charAt(i) == 'b') {
                    tempMoves = tempMoves + move_order[3];
                } else if (all_moves.charAt(i) == 'L') {
                    tempMoves = tempMoves + move_order[4];
                } else if (all_moves.charAt(i) == 'l') {
                    tempMoves = tempMoves + move_order[5];
                } else if (all_moves.charAt(i) == 'F') {
                    tempMoves = tempMoves + move_order[6];
                } else if (all_moves.charAt(i) == 'f') {
                    tempMoves = tempMoves + move_order[7];
                } else if (all_moves.charAt(i) == 'U') {
                    tempMoves = tempMoves + move_order[8];
                } else if (all_moves.charAt(i) == 'u') {
                    tempMoves = tempMoves + move_order[9];
                } else if (all_moves.charAt(i) == 'D') {
                    tempMoves = tempMoves + move_order[10];
                } else if (all_moves.charAt(i) == 'd') {
                    tempMoves = tempMoves + move_order[11];
                }
            }
        }
        return tempMoves;
    }

    private String Remove_Unwanted_Moves() {
        String temp_all_moves = "";
        for (int i = 0; i < all_moves.length(); i++) {
            if (i + 1 < all_moves.length() && all_moves.charAt(i) == 'R' && all_moves.charAt(i + 1) == 'r' || all_moves.charAt(i) == 'r' && all_moves.charAt(i + 1) == 'R')
                i++;
            else if (i + 1 < all_moves.length() && all_moves.charAt(i) == 'B' && all_moves.charAt(i + 1) == 'b' || all_moves.charAt(i) == 'b' && all_moves.charAt(i + 1) == 'B')
                i++;
            else if (i + 1 < all_moves.length() && all_moves.charAt(i) == 'L' && all_moves.charAt(i + 1) == 'l' || all_moves.charAt(i) == 'l' && all_moves.charAt(i + 1) == 'L')
                i++;
            else if (i + 1 < all_moves.length() && all_moves.charAt(i) == 'R' && all_moves.charAt(i + 1) == 'r' || all_moves.charAt(i) == 'r' && all_moves.charAt(i + 1) == 'R')
                i++;
            else if (i + 1 < all_moves.length() && all_moves.charAt(i) == 'U' && all_moves.charAt(i + 1) == 'u' || all_moves.charAt(i) == 'u' && all_moves.charAt(i + 1) == 'U')
                i++;
            else if (i + 1 < all_moves.length() && all_moves.charAt(i) == 'D' && all_moves.charAt(i + 1) == 'd' || all_moves.charAt(i) == 'd' && all_moves.charAt(i + 1) == 'D')
                i++;

            else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'R' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'r';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'r' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'R';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'B' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'b';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'b' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'B';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'L' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'l';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'l' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'L';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'F' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'f';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'f' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'F';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'U' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'u';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'u' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'U';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'D' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'd';
                i++;
                i++;
            } else if (i + 2 < all_moves.length() && all_moves.charAt(i) == 'd' && all_moves.charAt(i + 1) == all_moves.charAt(i) && all_moves.charAt(i + 2) == all_moves.charAt(i)) {
                temp_all_moves = temp_all_moves + 'D';
                i++;
                i++;
            } else {
                temp_all_moves = temp_all_moves + all_moves.charAt(i);
            }


        }
        return temp_all_moves;
    }

    private void AddSpaces() {
        String tempAllMoves = null;
        for (int i = 0; i < all_moves.length(); i++) {
            tempAllMoves = tempAllMoves + all_moves.charAt(i) + " ";
        }
        all_moves = tempAllMoves;
    }

}
