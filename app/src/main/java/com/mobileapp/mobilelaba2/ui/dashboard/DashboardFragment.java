package com.mobileapp.mobilelaba2.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mobileapp.mobilelaba2.R;
import com.mobileapp.mobilelaba2.ui.dashboard.database.MyDbHelper;
import com.mobileapp.mobilelaba2.ui.dashboard.database.Student;
import com.mobileapp.mobilelaba2.ui.dashboard.database.StudentsAdapter;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        MyDbHelper myDbHelper = new MyDbHelper(requireContext());

//        myDbHelper.addStudent(new Student("Stepan", "59", "46", "Lviv"));
//        myDbHelper.addStudent(new Student("Stepan Hirka", "59", "46", "Lviv"));
//        myDbHelper.addStudent(new Student("Marta Kvitka", "45", "58", "Cherkasy"));
//        myDbHelper.addStudent(new Student("Ivanna Mudruk", "100", "85", "Rivne"));

        List<Student> students = myDbHelper.getAllStudents();
        StudentsAdapter studentsAdapter = new StudentsAdapter(requireContext(), students);
        recyclerView.setAdapter(studentsAdapter);

        Button buttonGrade = view.findViewById(R.id.buttonGrade);
        Button buttonPercentage = view.findViewById(R.id.button2);
        Button buttonShowAll = view.findViewById(R.id.button3);
        TextView textViewPercentage = view.findViewById(R.id.studentsPercentage);

        buttonGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = view.findViewById(R.id.recyclerViewStudents);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                List<Student> studentsGradeMore60 = myDbHelper.getStudentsAbove60();
                StudentsAdapter studentsMore60Adapter = new StudentsAdapter(requireContext(), studentsGradeMore60);
                recyclerView.setAdapter(studentsMore60Adapter);
            }
        });

        buttonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = view.findViewById(R.id.recyclerViewStudents);
                recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
                List<Student> studentsGradeMore60 = myDbHelper.getAllStudents();
                StudentsAdapter studentsMore60Adapter = new StudentsAdapter(requireContext(), studentsGradeMore60);
                recyclerView.setAdapter(studentsMore60Adapter);
            }
        });

        buttonPercentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> studentsGradeMore60 = myDbHelper.getStudentsAbove60();
                List<Student> allStudents = myDbHelper.getAllStudents();
                double percentage = calculatePercentage(allStudents, studentsGradeMore60);
                textViewPercentage.setText(String.format(Locale.getDefault(), "%.2f%%", percentage));
            }
        });


        return view;
    }


    public double calculatePercentage(List<Student> allStudents, List<Student> studentsAbove60) {
        if (allStudents.isEmpty() || studentsAbove60.isEmpty()) {
        }

        int totalStudents = allStudents.size();
        int studentsAbove60Count = studentsAbove60.size();

        return ((double) studentsAbove60Count / totalStudents) * 100;
    }

}