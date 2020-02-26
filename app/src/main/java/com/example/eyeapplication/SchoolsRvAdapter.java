package com.example.eyeapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SchoolsRvAdapter extends RecyclerView.Adapter<SchoolsRvAdapter.ViewHolder> {
    public List<School> schools;
    public SchoolsActivity schoolsActivity;



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

    public School getValueAt(int position) {
        return schools.get(position);
    }

    public SchoolsRvAdapter(SchoolsActivity schoolsActivity, List<School> schools) {
        this.schools = schools;
        this.schoolsActivity = schoolsActivity;
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
        return schools.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final School school = getValueAt(position);

        holder.name.setText(school.getName());
        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                schoolsActivity.deleteSchool(school.getId());

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schoolsActivity.goToDetails(school.getId());
            }
        });

    }


}
