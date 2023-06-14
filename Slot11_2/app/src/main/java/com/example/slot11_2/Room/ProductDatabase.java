package com.example.slot11_2.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Products.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDAO getDAO();

    public static ProductDatabase INSTANCE;

    public static ProductDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ProductDatabase.class, "ProductDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
