package com.ahcreations.akila.rubikmonster;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BlankClassForFragment extends Fragment {

    View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.blank_activity, container, false);
        return view;
    }
}
