package com.tumerkankarabel.javatodoapp.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.tumerkankarabel.javatodoapp.data.entity.ToDos;
import com.tumerkankarabel.javatodoapp.databinding.CardDesignBinding;
import com.tumerkankarabel.javatodoapp.ui.fragment.ToDoHomePageFragmentDirections;
import com.tumerkankarabel.javatodoapp.ui.viewmodel.ToDoHomePageViewModel;

import java.util.List;

public class ToDosAdapter extends RecyclerView.Adapter<ToDosAdapter.CardDesignHolder> {
    private Context mContext;
    private List<ToDos> toDosList;
    private ToDoHomePageViewModel viewModel;

    public ToDosAdapter(Context mContext, List<ToDos> toDosList, ToDoHomePageViewModel viewModel) {
        this.mContext = mContext;
        this.toDosList = toDosList;
        this.viewModel = viewModel;
    }

    public class CardDesignHolder extends RecyclerView.ViewHolder {
        private CardDesignBinding design;

        public CardDesignHolder(CardDesignBinding design) {
            super(design.getRoot());
            this.design = design;
        }
    }

    @NonNull
    @Override
    public CardDesignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardDesignBinding binding =
                CardDesignBinding.inflate(LayoutInflater.from(mContext),parent,false);
        return new CardDesignHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDesignHolder holder, int position) {
        ToDos toDo = toDosList.get(position);
        CardDesignBinding d = holder.design;

        d.textViewToDo.setText(toDo.getName());

        d.imageViewEdit.setOnClickListener(view -> {
            ToDoHomePageFragmentDirections.PassingDetail passing =
                    ToDoHomePageFragmentDirections.passingDetail(toDo);
            Navigation.findNavController(view).navigate(passing);
        });

        d.imageViewDelete.setOnClickListener(view -> {
            Snackbar.make(view,"Should "+toDo.getName()+" be deleted ?",Snackbar.LENGTH_SHORT)
                    .setAction("YES",view1 -> {
                        viewModel.delete(toDo.getId());
                    }).show();
        });
    }

    @Override
    public int getItemCount() {
        return toDosList.size();
    }

}
