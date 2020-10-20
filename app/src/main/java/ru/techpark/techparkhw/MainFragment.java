package ru.techpark.techparkhw;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.techpark.techparkhw.NumberFragment;
import ru.techpark.techparkhw.R;


public class MainFragment extends Fragment {
    private final int INITIAL_CAPACITY = 100;
    private final int PORTRAIT = 3;
    private final int LANDSCAPE = 4;
    private int count = INITIAL_CAPACITY;
    private MyAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
        }

        myAdapter = new MyAdapter(count);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.first_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.first_fragment);
        final int span = (getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                ? PORTRAIT : LANDSCAPE;

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), span));
        recyclerView.setAdapter(myAdapter);

        view.findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.addItem();
            }
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private final DataSource dataSource = DataSource.getInstance();

        public MyAdapter(int size) {
            final int from = dataSource.getList().size();
            for (int i = from; i < size; i++) {
                dataSource.addItem();
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.model_fragment, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
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
            count++;
            dataSource.addItem();
            myAdapter.notifyItemInserted(dataSource.getList().size() - 1);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mNumber;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mNumber = itemView.findViewById(R.id.model_number);
            mNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NumberFragment numberFragment = new NumberFragment();

                    Bundle bundle = new Bundle();
                    bundle.putInt("value", Integer.parseInt(((TextView) v).getText().toString()));
                    bundle.putInt("color", ((TextView) v).getCurrentTextColor());
                    numberFragment.setArguments(bundle);

                    if (getFragmentManager() != null)
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, numberFragment)
                                .addToBackStack(NumberFragment.class.getSimpleName())
                                .commit();
                }
            });
        }

        public TextView getNumber() {
            return mNumber;
        }
    }
}
