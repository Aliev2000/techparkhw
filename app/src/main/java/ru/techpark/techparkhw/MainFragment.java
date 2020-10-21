package ru.techpark.techparkhw;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {
    private final int INITIAL_CAPACITY = 100;
    private final int PORTRAIT = 3;
    private final int LANDSCAPE = 4;
    private int currentListSize = INITIAL_CAPACITY;
    private NumbersAdapter numbersAdapter;
    static OnNumberSelectedListener onNumberSelectedListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            currentListSize = savedInstanceState.getInt("currentListSize");
        }

        numbersAdapter = new NumbersAdapter(currentListSize);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.first_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.first_fragment);
        final int span = (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                ? PORTRAIT : LANDSCAPE;

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), span));
        recyclerView.setAdapter(numbersAdapter);

        view.findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentListSize++;
                numbersAdapter.addItem();
                numbersAdapter.notifyItemInserted(currentListSize - 1);
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentListSize", currentListSize);
    }

    public interface OnNumberSelectedListener {
        void onNumberSelected(int numberValue, int numberColor);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        try {
            onNumberSelectedListener = (OnNumberSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }
}
