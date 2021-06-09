package com.example.crud_api;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.LinkedList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {

    private LinkedList<Student> linkedList;
    private LayoutInflater inflater;
    private Context context;

    public RecycleViewAdapter(LinkedList<Student> linkedList, Context context) {
        inflater = LayoutInflater.from(context);
        this.linkedList = linkedList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_view, parent, false);
        return new RecycleViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        Student student = linkedList.get(position);
        holder.tvName.setText(student.getStName());
        holder.tvAge.setText(student.getAge());
        holder.tvSex.setText(student.getSex() == true ? "Male" : "Female");
        holder.imgStudent.setImageResource(student.getImgStudent());
    }

    @Override
    public int getItemCount() {
        return linkedList.size();
    }

    public void filterList(LinkedList<Student> list) {
        linkedList = list;
        notifyDataSetChanged();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecycleViewAdapter adapter;
        public TextView tvName;
        public TextView tvAge;
        public TextView tvSex;
        public ImageView imgStudent;

        public RecycleViewHolder(View view, RecycleViewAdapter adapter) {
            super(view);

            tvName = view.findViewById(R.id.tvName);
            tvAge = view.findViewById(R.id.tvAge);
            tvSex = view.findViewById(R.id.tvSex);
            imgStudent = view.findViewById(R.id.imgStudent);

            this.adapter = adapter;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Student element = linkedList.get(position);
            ImageView btnMinus = v.findViewById(R.id.btnMinus);
            ImageView btnEdit = v.findViewById(R.id.btnEdit);
            String url = "https://60c03368b8d36700175547b3.mockapi.io/Students";

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FormRegisterStudent.class);

                    intent.putExtra("idStudent", element.getId());
                    intent.putExtra("nameStudent", element.getStName());
                    intent.putExtra("ageStudent", element.getAge());
                    intent.putExtra("heightStudent", element.getHeight());
                    intent.putExtra("weightStudent", element.getWeight());
                    intent.putExtra("sexStudent", element.getSex());

                    context.startActivity(intent);
                }
            });

            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteData(url, element.getId());
                }
            });
            adapter.notifyDataSetChanged();
        }
        
        private void DeleteData(String url, int id) {
            StringRequest stringRequest = new StringRequest(
                    Request.Method.DELETE, url + '/' + id, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    linkedList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(stringRequest);
        }
    }
}
