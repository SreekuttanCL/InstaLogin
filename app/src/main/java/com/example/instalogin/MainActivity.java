package com.example.instalogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

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
//                if(Username.getText().toString().equals("Student@gc.ca") && Password.getText().toString().equals("password")){
//                    // Intent intent = new Intent(MainActivity.this,SecondActivity.class);
//                    // startActivity(intent);
//                    Toast successMsg = Toast.makeText(MainActivity.this, "Successfully Logged in ", Toast.LENGTH_SHORT);
//                    successMsg.show();
//                }
//                else{
//                    Toast errorMsg = Toast.makeText(MainActivity.this, "Username or Password is invalid. ", Toast.LENGTH_SHORT);
//                    errorMsg.show();
//                }

                registerUser();
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                startActivity(intent);
            }
        });

    }

    private void registerUser(){

        String email = Username.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Sucessfully registered", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Registration Error..", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }


}
