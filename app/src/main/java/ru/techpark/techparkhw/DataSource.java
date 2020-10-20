package ru.techpark.techparkhw;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private final static DataSource ourInstance = new DataSource();
    private final int INITIAL_CAPACITY = 100;
    private final List<MyModel> list;

    private DataSource() {
        list = new ArrayList<>(INITIAL_CAPACITY);
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            list.add(new MyModel(i + 1));
        }
    }

    void addItem() {
        list.add(new MyModel(list.size() + 1));
    }

    static DataSource getInstance() {
        return ourInstance;
    }

    public List<MyModel> getList() {
        return list;
    }

    public static class MyModel {
        private int mValue;
        private int mColor;

        public MyModel(int mValue) {
            this.mValue = mValue;
            this.mColor = mValue % 2 == 0 ? Color.RED : Color.BLUE;
        }

        public int getValue() {
            return mValue;
        }

        public int getColor() {
            return mColor;
        }
    }
}
