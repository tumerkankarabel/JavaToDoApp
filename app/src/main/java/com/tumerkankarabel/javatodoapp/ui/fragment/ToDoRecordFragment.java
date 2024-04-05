package com.tumerkankarabel.javatodoapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tumerkankarabel.javatodoapp.databinding.FragmentTodoRecordBinding;
import com.tumerkankarabel.javatodoapp.ui.viewmodel.ToDoRecordViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ToDoRecordFragment extends Fragment {

    private FragmentTodoRecordBinding binding;
    private ToDoRecordViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTodoRecordBinding.inflate(inflater, container, false);

        binding.toolbarToDoRecord.setTitle("To Do Record");

        binding.buttonSave.setOnClickListener(view -> {
            String todoName = binding.editTextToDoName2.getText().toString();
            if (todoName.trim().isEmpty()) {
                Toast.makeText(requireContext(), "To do name cannot be empty!", Toast.LENGTH_SHORT).show();
            } else {
                viewModel.save(todoName);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ToDoRecordViewModel.class);
    }

}