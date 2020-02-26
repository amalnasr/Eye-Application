package com.example.eyeapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsRvAdapter extends RecyclerView.Adapter<StudentsRvAdapter.ViewHolder> {
    public List<StudentInformation> studentInformations;
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

    public StudentInformation getValueAt(int position) {
        return studentInformations.get(position);
    }

    public StudentsRvAdapter(SchoolDetailActivity schoolDetailActivity, List<StudentInformation> studentInformations) {
        this.studentInformations = studentInformations;
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
        return studentInformations.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final StudentInformation studentInformation = getValueAt(position);

        holder.name.setText("الأسم : "
                .concat(studentInformation.getName())
                .concat(" , ")
                .concat(" رقم هويه الأم : ")
                .concat(studentInformation.getMid())
                .concat(" \n")
                .concat(" رقم هويه الأب : ")
                .concat(studentInformation.getFid())
                .concat(" , ")
                .concat(" الفصل : ")
                .concat(studentInformation.getSec()));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schoolDetailActivity.deleteStudent(studentInformation.getId());
            }
        });
    }

}
