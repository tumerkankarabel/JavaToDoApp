package com.tumerkankarabel.javatodoapp.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tumerkankarabel.javatodoapp.data.entity.ToDos;
import com.tumerkankarabel.javatodoapp.data.repo.ToDosDaoRepository;

import java.io.Closeable;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ToDoHomePageViewModel extends ViewModel {
    public ToDosDaoRepository tdRepo;
    public MutableLiveData<List<ToDos>> toDosList;

    @Inject
    public ToDoHomePageViewModel(ToDosDaoRepository tdRepo) {
        this.tdRepo = tdRepo;
        loadToDos();
        toDosList = tdRepo.toDosList;
    }

    public void search(String searchingWord) {
        tdRepo.search(searchingWord);
    }

    public void delete(int todoId) {
        tdRepo.delete(todoId);
    }

    public void loadToDos() {
        tdRepo.loadToDos();
    }

}
