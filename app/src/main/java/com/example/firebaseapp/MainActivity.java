package com.example.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.firebaseapp.Modules.CrudStudents;
import com.example.firebaseapp.Modules.Student;

public class MainActivity extends AppCompatActivity {

    TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SETUP
        setup();
    }

    public void setup(){
        castElements();
    }

    public void castElements(){
        tvMain = (TextView) findViewById(R.id.tvMain);

        tvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setFirstName("Noel Cristian");
                student.setLastName("Casas Rodríguez");
                student.setAge(21);
                student.setNumberControl(1217100955);
                student.setNumberPhone("4681041522");
                student.setZipCode(37906);

                CrudStudents crudStudents = new CrudStudents();
                crudStudents.addStudent(student);

                tvMain.setText(crudStudents.result.getMessage());
            }
        });
    }
}