package com.example.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    CheckBox checkBox1,checkBox2;
    Button increase,decrease,Reset,Orderb;

    TextView contain;
    int Count=0,cost=0,Countc=0;

    String C="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);



        increase=findViewById(R.id.button);
        decrease=findViewById(R.id.button2);
        contain=findViewById(R.id.content);
        Orderb=findViewById(R.id.orderb);
        Reset=findViewById(R.id.button3);


        checkBox1=findViewById(R.id.checkBox);
        checkBox2=findViewById(R.id.checkBox2);




           increase.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                  Count++;
                  C= String.valueOf(Count);

                  contain.setText(String.valueOf(Count));




               }
           });



           decrease.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {



                   if(Count>0) {
                       Count--;

                       contain.setText(String.valueOf(Count));

                   }
               }
           });





      Orderb.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              cost=Integer.parseInt(contain.getText().toString());


              Toast.makeText(MainActivity.this, ""+cost*Countc, Toast.LENGTH_SHORT).show();



          }
      });

      Reset.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

            contain.setText("0");
            cost=0;


         }
         });





















    }

    public void check(View view) {

          if(checkBox1.isChecked()&&checkBox2.isChecked()){

              Countc=10;


          }
          else if(checkBox1.isChecked()||checkBox2.isChecked()){

              Countc=5;




          }
          else{


              Countc=0;
          }







    }





}
