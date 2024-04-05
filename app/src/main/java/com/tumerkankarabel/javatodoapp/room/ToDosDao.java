package com.tumerkankarabel.javatodoapp.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tumerkankarabel.javatodoapp.data.entity.ToDos;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ToDosDao {
    @Query("SELECT * FROM toDos")
    Single<List<ToDos>> loadToDos();

    @Insert
    Completable save(ToDos toDo);

    @Update
    Completable update(ToDos toDo);

    @Delete
    Completable delete(ToDos toDo);

    @Query("SELECT * FROM toDos WHERE name like '%' || :searchingWord || '%'")
    Single<List<ToDos>> search(String searchingWord);
}
