package com.example.testapp;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.ImageVideoModelLoader;
import com.bumptech.glide.load.model.file_descriptor.FileDescriptorModelLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URI;

public class Nextpage extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    public Uri uri;
    Button Upload, Chooser;
    ImageView imageView;
    EditText editText;
    private DatabaseReference firebaseDatabase;
    private StorageReference firebaseStorage;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Uri uri=Uri.fromFile(new File());

        if (resultCode == PICK_IMAGE_REQUEST && requestCode == RESULT_OK && data != null && data.getData() != null) {

            imageView = findViewById(R.id.imageView2);

            Uri uri = data.getData();

            imageView.setImageURI(uri);

        } else {
            imageView = findViewById(R.id.imageView2);

            uri = data.getData();

            imageView.setImageURI(uri);


        }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextpage);


        firebaseDatabase = FirebaseDatabase.getInstance().getReference("uploads");
        firebaseStorage = FirebaseStorage.getInstance().getReference("upload");

        Upload = findViewById(R.id.upload);
        Chooser = findViewById(R.id.button3);
        editText = findViewById(R.id.editText);

        imageView = findViewById(R.id.imageView2);


        Chooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Choose();


            }
        });


        Upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Upload();

            }
        });


    }


    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }


    private void Upload() {


        if (uri != null) {

            firebaseStorage = firebaseStorage.child(System.currentTimeMillis()
                    + "." + getFileExtension(uri));

            firebaseStorage.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(final UploadTask.TaskSnapshot taskSnapshot) {





                    firebaseStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            Upload1 object = new Upload1(editText.getText().toString().trim(),uri.toString());

                            String id = firebaseDatabase.push().getKey();




                            firebaseDatabase.child(id).setValue(object);



                        }
                    });




                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Nextpage.this, "fail" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        } else {

            Toast.makeText(this, "setimage", Toast.LENGTH_SHORT).show();
        }


















    }

    public void Choose() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //  startActivityForResult(intent,PICK_IMAGE_REQUEST);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);





     /*   Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, PICK_IMAGE_REQUEST);
*/

    }


    public void clicked(View view) {
        startActivity(new Intent(Nextpage.this,image.class));


    }
}
