package com.example.eyeapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeachersRvAdapter extends RecyclerView.Adapter<TeachersRvAdapter.ViewHolder> {
    public List<Teacher> teachers;
    public SchoolDetailActivity schoolDetailActivity;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        private final TextView name;
        private final LinearLayout delete;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            name = view.findViewById(R.id.title);
            delete = view.findViewById(R.id.cancel_button);
        }
    }

    public Teacher getValueAt(int position) {
        return teachers.get(position);
    }

    public TeachersRvAdapter(SchoolDetailActivity schoolDetailActivity, List<Teacher> teachers) {
        this.teachers = teachers;
        this.schoolDetailActivity = schoolDetailActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schools_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return teachers.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Teacher teacher = getValueAt(position);

        holder.name.setText("الأسم : "
                .concat(teacher.getName())
                .concat(" , ")
                .concat(" المواد الدراسية : ")
                .concat(teacher.getSubject())
                .concat(" \n")
                .concat(" الفصل الدراسي : ")
                .concat(teacher.getSections()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schoolDetailActivity.deleteTeacher(teacher.getId());
            }
        });
    }

}
