package com.tumerkankarabel.javatodoapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tumerkankarabel.javatodoapp.data.entity.ToDos;

@Database(entities = {ToDos.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ToDosDao getToDosDao();
}
