package ru.techpark.techparkhw;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersAdapter extends RecyclerView.Adapter<NumberViewHolder> {
    private final DataSource dataSource = DataSource.getInstance();

    public NumbersAdapter(int size) {
        final int from = dataSource.getList().size();
        for (int i = from; i < size; i++) {
            dataSource.addItem();
        }
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.model_fragment, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        int mValue = dataSource.getList().get(position).getValue();
        int mColor = dataSource.getList().get(position).getColor();
        holder.getNumber().setText(String.valueOf(mValue));
        holder.getNumber().setTextColor(mColor);
    }

    @Override
    public int getItemCount() {
        return dataSource.getList().size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    void addItem() {
        dataSource.addItem();
    }
}
