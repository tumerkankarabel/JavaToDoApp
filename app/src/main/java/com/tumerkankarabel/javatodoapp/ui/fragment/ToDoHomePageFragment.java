package com.tumerkankarabel.javatodoapp.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tumerkankarabel.javatodoapp.R;
import com.tumerkankarabel.javatodoapp.databinding.FragmentTodoHomepageBinding;
import com.tumerkankarabel.javatodoapp.ui.adapter.ToDosAdapter;
import com.tumerkankarabel.javatodoapp.ui.viewmodel.ToDoHomePageViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ToDoHomePageFragment extends Fragment {

    private FragmentTodoHomepageBinding binding;
    private ToDoHomePageViewModel viewModel;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentTodoHomepageBinding.inflate(inflater, container, false);

       binding.toolbarHomepage.setTitle("ToDos");

       binding.rv.setLayoutManager(new LinearLayoutManager(requireContext()));

       viewModel.toDosList.observe(getViewLifecycleOwner(), toDosList -> {
           ToDosAdapter toDosAdapter = new ToDosAdapter(requireContext(),toDosList,viewModel);
           binding.rv.setAdapter(toDosAdapter);
       });

       binding.fab.setOnClickListener(view -> {
           Navigation.findNavController(view).navigate(R.id.passingRecord);
       });

       binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               viewModel.search(query);
               return true;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               viewModel.search(newText);
               return true;
           }
       });

       return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ToDoHomePageViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.loadToDos();
    }
}