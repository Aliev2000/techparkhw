package ru.techpark.techparkhw;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements MainFragment.OnNumberSelectedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new MainFragment())
                    .commit();
        }
    }

    @Override
    public void onNumberSelected(int numberValue, int numberColor) {
        NumberFragment numberFragment = new NumberFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("numberValue", numberValue);
        bundle.putInt("numberColor", numberColor);
        numberFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, numberFragment)
                .addToBackStack(NumberFragment.class.getSimpleName())
                .commit();
    }
}
