package com.example.firebaseapp.Modules;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrudStudents {

    DatabaseReference databaseReference;
    public Result result;

    public CrudStudents(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        createResult();
    }

    public void createResult(){
        result = new Result();
    }

    public void getStudents(){
        databaseReference.child("Students").orderByChild("firstName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Student student = dataSnapshot.getValue(Student.class);
                    if (student != null){

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getStudent(String uid){
        try{
            if(!uid.isEmpty() && uid.length() > 1){
                databaseReference.child("Students").orderByChild("firstName").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Student student = dataSnapshot.getValue(Student.class);
                            if (student.getUid().equals(uid)){

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }else {
                result.setError("Uid invalid");
            }
        }
        catch (Exception ex){
            result.setError(ex.getMessage());
        }
    }

    public void addStudent(Student student){
        createResult();
        try{
            student.setUid(UUID.randomUUID().toString());
            student.setDateCreate(new Date());
            student.setDateUpdate(new Date());

            databaseReference.child("Students").setValue(student);

            result.setSuccessful("Student add successful!");
        }catch (Exception ex){
            result.setError(ex.getMessage());
        }
    }

    public void updateStudent(Student student){
        student.setDateUpdate(new Date());

        HashMap<String, Object> values = new HashMap<>();
        values.put("uid", student.getUid());
        values.put("firstName", student.getFirstName());
        values.put("lastName", student.getLastName());
        values.put("age", student.getAge());
        values.put("numberControl", student.getNumberControl());
        values.put("numberPhone", student.getNumberPhone());
        values.put("zipCode", student.getZipCode());
        values.put("dateCreate", student.getDateCreate());
        values.put("dateUpdate", student.getDateUpdate());

        Map<String, Object> postValues = values;
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/School/Students/" + student.getUid(), postValues);

        databaseReference.updateChildren(childUpdates);

        result.setSuccessful("Student update successful!");
    }

    public void deleteStudent(Student student){
        databaseReference.child("").child(student.getUid()).removeValue();

        result.setSuccessful("Student deleted successful!");
    }
}
