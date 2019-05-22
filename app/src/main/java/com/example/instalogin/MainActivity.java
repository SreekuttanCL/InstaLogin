package com.example.instalogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = (EditText) findViewById(R.id.editText1);
        Password = (EditText) findViewById(R.id.editText2);
        Login = (Button) findViewById(R.id.button);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.languages));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(Username.getText().equals("Admin") && Password.getText().equals("1234")){
                   // Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                   // startActivity(intent);
                    Toast sucessMsg = Toast.makeText(MainActivity.this, "Sucessfully Logged in ", Toast.LENGTH_SHORT);
                    sucessMsg.show();
                    return;
                }
                else{
                    Toast errorMsg = Toast.makeText(MainActivity.this, "Username or Password is invalid. ", Toast.LENGTH_SHORT);
                    errorMsg.show();
                    return;
                }
            }
        });


    }


}
