package ru.techpark.techparkhw;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumberFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.big_number_fragment, container, false);
        if (getArguments() == null) {
            return v;
        }

        int mValue = getArguments().getInt("numberValue", -1);
        int mColor = getArguments().getInt("numberColor", Color.GREEN);

        TextView view = v.findViewById(R.id.big_number);
        view.setText(String.valueOf(mValue));
        view.setTextColor(mColor);

        return v;
    }
}

