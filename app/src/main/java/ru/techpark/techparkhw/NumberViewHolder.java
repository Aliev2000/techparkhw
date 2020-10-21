package ru.techpark.techparkhw;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumberViewHolder extends RecyclerView.ViewHolder {
    private TextView mNumber;
    public NumberViewHolder(@NonNull View itemView) {
        super(itemView);
        mNumber = itemView.findViewById(R.id.model_number);
        mNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment.onNumberSelectedListener.onNumberSelected(
                        Integer.parseInt(((TextView) v).getText().toString()),
                        ((TextView) v).getCurrentTextColor());
            }
        });
    }

    public TextView getNumber() {
        return mNumber;
    }
}