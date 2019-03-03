package com.example.testapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public ArrayList<Information> data = new ArrayList<>();
    public RecyclerView.LayoutManager layoutManager;
    public RecyclerView recyclerView;
    public ListAdapter adapter;
    private TextView textView;
    private int image;
    private String Name, Phone, Email, TName;
    private EditText name, phone, email;
    private DatabaseReference firebaseDatabase;
    private Button button, getButton;
    private ImageView imageView;


    @Override
    protected void onStart() {
        super.onStart();


        firebaseDatabase.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                data.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    Information information = dataSnapshot1.getValue(Information.class);
                    data.add(information);

                }

                adapter = new ListAdapter(data, MainActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(layoutManager);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        name = findViewById(R.id.editText1);
        email = findViewById(R.id.editText2);
        phone = findViewById(R.id.editText3);
        button = findViewById(R.id.button);
        getButton = findViewById(R.id.button2);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference("Customer");
        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
         imageView=findViewById(R.id.imageView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String N, P, E;
                N = name.getText().toString().trim();
                P = phone.getText().toString().trim();
                E = email.getText().toString().trim();



                /*learn how to retreive images from firebase*/

                String id = P;
               Information information = new Information(N, P, E, );
                firebaseDatabase.child(id).setValue(information);

            }

        });


    }


}
