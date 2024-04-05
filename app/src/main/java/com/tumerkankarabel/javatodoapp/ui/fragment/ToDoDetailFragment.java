    package com.tumerkankarabel.javatodoapp.ui.fragment;

    import android.os.Bundle;

    import androidx.annotation.Nullable;
    import androidx.fragment.app.Fragment;
    import androidx.lifecycle.ViewModelProvider;

    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import com.tumerkankarabel.javatodoapp.R;
    import com.tumerkankarabel.javatodoapp.data.entity.ToDos;
    import com.tumerkankarabel.javatodoapp.databinding.FragmentTodoDetailBinding;
    import com.tumerkankarabel.javatodoapp.ui.viewmodel.ToDoDetailViewModel;

    import dagger.hilt.android.AndroidEntryPoint;

    @AndroidEntryPoint
    public class ToDoDetailFragment extends Fragment {

        private FragmentTodoDetailBinding binding;
        private ToDoDetailViewModel viewModel;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            binding = FragmentTodoDetailBinding.inflate(inflater, container, false);

            binding.toolbarToDoDetail.setTitle("To Do Detail");

            ToDoDetailFragmentArgs bundle = ToDoDetailFragmentArgs.fromBundle(getArguments());
            ToDos comingToDo = bundle.getTodo();

            binding.editTextToDoName.setText(comingToDo.getName());

            binding.buttonUpdate.setOnClickListener(view -> {
                String todoName= binding.editTextToDoName.getText().toString();

                viewModel.update(comingToDo.getId(),todoName);
            });

            return binding.getRoot();
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            viewModel = new ViewModelProvider(this).get(ToDoDetailViewModel.class);
        }
    }