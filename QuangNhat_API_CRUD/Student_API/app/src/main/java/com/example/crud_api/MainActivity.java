package com.example.crud_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    ImageView btnAdd;
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    TextView tvStudent;
    LinkedList<Student> linkedList = new LinkedList<>();

    String url = "https://60c03368b8d36700175547b3.mockapi.io/Students";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        GetData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormRegisterStudent.class);
                startActivity(intent);
            }
        });
    }

    private void GetData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                        int idStudent = object.getInt("idStudent");
                        String nameStudent = object.getString("nameStudent");
                        int age = object.getInt("age");
                        int height = object.getInt("height");
                        int weight = object.getInt("weight");
                        Boolean sex = object.getBoolean("sex");
                        linkedList.add(new Student(idStudent,nameStudent, sex, age, height,weight, R.drawable.ic_pet));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter = new RecycleViewAdapter(linkedList, MainActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void AnhXa() {
        recyclerView = findViewById(R.id.recycleView);

        btnAdd = findViewById(R.id.btnAdd);

        tvStudent = findViewById(R.id.tvStudents);

    }
}