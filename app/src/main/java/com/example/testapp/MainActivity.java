package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
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
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import static com.google.firebase.storage.FirebaseStorage.getInstance;


public class MainActivity extends AppCompatActivity {

    public ArrayList<Upload1> data = new ArrayList<Upload1>();
    public RecyclerView.LayoutManager layoutManager;
    public RecyclerView recyclerView;
    public ListAdapter adapter;
    public StorageReference firebaseStorage,firebaseStorage2;
    private TextView textView;
    private int image;
    private String Name, Phone, Email, TName,url;
    private EditText name, phone, email;
    private DatabaseReference firebaseDatabase, databaseReference , firebaseDatabase1, firebaseDatabase2;
    private Button button, getButton;
    private ImageView imageView;


    @Override
    protected void onStart() {
        super.onStart();

        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        firebaseStorage = getInstance().getReference("uploads");


        databaseReference.child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                data.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    url=dataSnapshot1.getValue(Upload1.class).getUrl();
                    Upload1 upload1 = dataSnapshot1.getValue(Upload1.class);
                    data.add(upload1);

                }


                Toast.makeText(MainActivity.this, ""+url, Toast.LENGTH_SHORT).show();
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

        firebaseDatabase1 = FirebaseDatabase.getInstance().getReference("Customer");

        firebaseDatabase2 = FirebaseDatabase.getInstance().getReference("Customer");

        recyclerView = findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        imageView = findViewById(R.id.imageView);



/*

        firebaseStorage2 = FirebaseStorage.getInstance().getReference("upload");


        Glide.with(getApplicationContext()).using(new FirebaseImageLoader()).
                load(firebaseStorage).into(image1);


      firebaseStorage2.child("1551717648673.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
          @Override
          public void onSuccess(Uri uri) {




              Picasso.with(getApplicationContext()).load(uri.toString()).into(image1);

          }
      });
*/






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String N, P, E;
                N = name.getText().toString().trim();
                P = phone.getText().toString().trim();
                E = email.getText().toString().trim();



                /*learn how to retreive images from firebase*/


                String id = P;
                Information information = new Information(N, P, E, "");

                firebaseDatabase.child("top").child("color").child("RED").child("size").setValue(information);

                firebaseDatabase.child("Kurti").child("color").child("RED").child("size").setValue(information);

                //   firebaseDatabase.child(id).setValue(information);

            }

        });


        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Nextpage.class));
            }
        });


    }


}
