package com.example.todolistapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistapp.databinding.RvBinding;

public class RvAdapter extends ListAdapter<Note,RvAdapter.ViewHolder> {

    public RvAdapter(MainActivity mainActivity){
        super(CALLBACK);

}
private static final DiffUtil.ItemCallback<Note> CALLBACK = new DiffUtil.ItemCallback<Note>() {
    @Override
    public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getId()==newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getTitle().equals(newItem.getTitle())
                && oldItem.getDisp().equals(newItem.getDisp());
    }
};
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv , parent ,  false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.ViewHolder holder, int position) {
            Note note = getItem(position);
            holder.binding.titlerv.setText(note.getTitle());
            holder.binding.disprv.setText(note.getDisp());
    }
    public Note getNote(int position){
        return getItem(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        RvBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        binding = RvBinding.bind(itemView);
        }

        }
}
