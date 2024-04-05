package com.tumerkankarabel.javatodoapp.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.tumerkankarabel.javatodoapp.data.repo.ToDosDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ToDoDetailViewModel extends ViewModel {
    public ToDosDaoRepository tdRepo;

    @Inject
    public ToDoDetailViewModel(ToDosDaoRepository tdRepo) {
        this.tdRepo = tdRepo;
    }

    public void update(int toDoId, String toDoName) {
        tdRepo.update(toDoId, toDoName);
    }
}
