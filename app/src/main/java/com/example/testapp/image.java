package com.example.testapp;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class image extends AppCompatActivity {


    StorageReference firebaseStorage;
    ImageView imageView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView4=findViewById(R.id.imageView4);


        firebaseStorage=FirebaseStorage.getInstance().getReference("upload");

    /*    Glide.with(image.this).using(new FirebaseImageLoader()).
                load(firebaseStorage).into(imageView4);
                1551718365246.png
*/

      //  Toast.makeText(this, ""+firebaseStorage.getDownloadUrl().getResult().toString(), Toast.LENGTH_SHORT).show();


        firebaseStorage.child("1551718365246.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Toast.makeText(image.this, ""+uri.toString(), Toast.LENGTH_SHORT).show();

                Upload1 upload1=new Upload1("k",uri.toString());


               // imageView4.setImageURI(uri);
                Picasso.with(getApplicationContext()).load(uri).into(imageView4);

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });








    }
}
