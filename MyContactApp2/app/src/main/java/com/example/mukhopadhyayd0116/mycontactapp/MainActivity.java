package com.example.mukhopadhyayd0116.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editAddress;
    Button btnAddData;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDb=  new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_Age);
        editAddress = (EditText) findViewById(R.id.editText_Address);
    }
    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(),editAddress.getText().toString());

        if(isInserted==true){
            //create toast message to user indicating data inserted correctly
            Log.d("MyContact","Data Insertion successful");
        }
        else{
            //create toast message to user indicating data inserted correctly
            Log.d("MyContact","Data Insertion successful");

        }
    }
    public void viewData(View v){
        Cursor res= myDb.getAllData();
        if(res.getCount()== 0){
            showMessage("Error", "No data found in database");
        //put a logd message and toast
            Log.d("Error","No data found in database");

        return;

        }
        StringBuffer buffer= new StringBuffer();

        //setup loop with cursor movetonext method
        // append each col to buffer
        //use the getstring method
        showMessage("Data", buffer.toString());

    }

    private void showMessage(String title, String message) {

    }


}
