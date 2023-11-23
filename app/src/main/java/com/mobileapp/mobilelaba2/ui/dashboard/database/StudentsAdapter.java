package com.mobileapp.mobilelaba2.ui.dashboard.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.mobilelaba2.R;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<MyStubentHolder> {

    Context context;
    List<Student> students;

    public StudentsAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public MyStubentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyStubentHolder(LayoutInflater.from(context).inflate(R.layout.student_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyStubentHolder holder, int position) {
        holder.pibView.setText(students.get(position).getPib());
        String grades = students.get(position).getGrade1() + "    " +  students.get(position).getGrade2();
        holder.gradesView.setText(grades);
        holder.addressView.setText(students.get(position).getAdress());
        String id = "    " + students.get(position).getId() ;
        holder.idView.setText(id);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
}
