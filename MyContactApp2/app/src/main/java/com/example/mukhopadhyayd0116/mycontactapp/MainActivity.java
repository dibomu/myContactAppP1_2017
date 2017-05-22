package com.example.mukhopadhyayd0116.mycontactapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editAddress;
    EditText editSearch;
    Button btnAddData;
    String types[]= {"ID","NAME","AGE","ADDRESS"};






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDb=  new DatabaseHelper(this);
        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_Age);
        editAddress = (EditText) findViewById(R.id.editText_Address);
        editSearch= (EditText) findViewById(R.id.Search);


    }
    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(),editAddress.getText().toString());

        if(isInserted==true){
            //create toast message to user indicating data inserted correctly
            Context context = getApplicationContext();
            CharSequence text = "data insertion successfull";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.d("MyContact","Data Insertion successful");
        }
        else{
            //create toast message to user indicating data inserted correctly
            Context context = getApplicationContext();
            CharSequence text = "data insertion not successfull";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Log.d("MyContact","Data Insertion successful");
            Log.d("MyContact","Data Insertion successful");

        }
    }
    public void viewData(View v){


        Cursor res= myDb.getAllData();


        res.moveToFirst();
        if(res.getCount()== 0){
            showMessage("Error", "No data found in database");
        //put a logd message and toast
            Log.d("MyContact", " Got to view data" );
            Log.d("Error","No data found in database");


        return;

        }
        StringBuffer buffer= new StringBuffer();

        res.moveToFirst();
       while(!(res.isAfterLast())){
        for ( int i =0; i<res.getColumnCount();i++){
            if (i==0){
                buffer.append("ID:");

            }
            if(i==1){
                buffer.append("Name:");

            }
            if(i==2){
                buffer.append("Age:");

            }
            if(i==3){
                buffer.append("Address:");
            }
            buffer.append(res.getString(i));
            buffer.append("\n");

            Log.d("MyContact","test"+ res.getCount());

        }
        res.moveToNext();
        //setup loop with cursor movetonext method
        // append each col to buffer
        //use the getstring method
        showMessage("Data", buffer.toString());

    }
    }
    public void searchContact ( View v ){

        Cursor res = myDb.getAllData();
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();

        while(res.moveToNext()) {
            for (int i = 0; i < res.getCount(); i++) {
                if (res.getString(1).equals(editSearch.getText().toString())) {
                       if(buffer2.indexOf(types[0] + res.getString(0) + "\n") == -1) {


                        for (int j = 0; j < 4;j++) {

                           if(j > 0) {buffer.append(types[j] + " : " + res.getString(j) + "\n");}
                            buffer2.append(types[j] + res.getString(j) + "\n");
                        }
                        buffer.append("\n");
                    }
                }
            }
        }

        if (buffer.toString().equals("")) {
            showMessage("Did not work ", " ");

        }
        else {
            showMessage("Result: ", buffer.toString());
        }

        editSearch.setText("");
    }
    private void showMessage(String title, String message) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);//cancel using back button
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }


}
