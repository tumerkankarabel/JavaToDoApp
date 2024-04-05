package com.tumerkankarabel.javatodoapp.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.tumerkankarabel.javatodoapp.data.entity.ToDos;
import com.tumerkankarabel.javatodoapp.room.ToDosDao;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ToDosDaoRepository {
    public MutableLiveData<List<ToDos>> toDosList = new MutableLiveData<>();
    private ToDosDao tdDao;

    public ToDosDaoRepository(ToDosDao tdDao) {
        this.tdDao = tdDao;
    }

    public void save(String toDoName) {
        ToDos newToDo = new ToDos(0,toDoName);
        tdDao.save(newToDo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {}
                });

    }

    public void update(int toDoId, String toDoName) {
        ToDos updatedToDo = new ToDos(toDoId,toDoName);
        tdDao.update(updatedToDo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void search(String searchingWord) {
        tdDao.search(searchingWord).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ToDos>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<ToDos> toDos) {
                        toDosList.setValue(toDos);
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void delete(int toDoId) {
        ToDos deletedToDo = new ToDos(toDoId,"");
        tdDao.delete(deletedToDo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {
                        loadToDos();
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void loadToDos() {
        tdDao.loadToDos().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ToDos>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(List<ToDos> toDos) {
                        toDosList.setValue(toDos);
                    }

                    @Override
                    public void onError(Throwable e) {}
                });
    }
}
