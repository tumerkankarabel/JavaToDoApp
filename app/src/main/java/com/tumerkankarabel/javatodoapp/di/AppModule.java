package com.tumerkankarabel.javatodoapp.di;

import android.content.Context;

import androidx.room.Room;

import com.tumerkankarabel.javatodoapp.data.repo.ToDosDaoRepository;
import com.tumerkankarabel.javatodoapp.room.AppDatabase;
import com.tumerkankarabel.javatodoapp.room.ToDosDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public ToDosDaoRepository provideToDosDaoRepository(ToDosDao tdDao) {
        return new ToDosDaoRepository(tdDao);
    }

    @Provides
    @Singleton
    public ToDosDao provideToDosDao(@ApplicationContext Context context) {
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class,"todos.sqlite")
                .createFromAsset("todos.sqlite").build();
        return db.getToDosDao();
    }
}
