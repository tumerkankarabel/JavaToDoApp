package com.tumerkankarabel.javatodoapp.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.tumerkankarabel.javatodoapp.data.repo.ToDosDaoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ToDoRecordViewModel extends ViewModel {
    public ToDosDaoRepository tdRepo;

    @Inject
    public ToDoRecordViewModel(ToDosDaoRepository tdRepo) {
        this.tdRepo = tdRepo;
    }

    public void save(String toDoName) {
        tdRepo.save(toDoName);
    }
}
