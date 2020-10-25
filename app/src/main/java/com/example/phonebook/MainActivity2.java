package com.example.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setTitle("Ajouter Contact");

        floatBtn();


    }

    public void floatBtn(){
        FloatingActionButton fab = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(otherActivity);
            }
        });
    }
    public void Enregistrer() {
        EditText firstName = findViewById(R.id.EFirstName);
        EditText lastName = findViewById(R.id.ELastName);
        EditText phone = findViewById(R.id.EPhone);
        EditText email = findViewById(R.id.Eemail);
        EditText job = findViewById(R.id.EJob);
        DBconnexion db = new DBconnexion(this);
        db.insertNewContact(lastName.getText().toString(), firstName.getText().toString(),job.getText().toString(),phone.getText().toString(),email.getText().toString());

        autreActivity();
    }
    public void initForm(){
        EditText firstName = findViewById(R.id.EFirstName);
        EditText lastName = findViewById(R.id.ELastName);
        EditText phone = findViewById(R.id.EPhone);
        EditText email = findViewById(R.id.Eemail);
        EditText job = findViewById(R.id.EJob);
        firstName.setText("");
        lastName.setText("");
        phone.setText("");
        email.setText("");
        job.setText("");
    }
    public void autreActivity(){
        Intent otherActivity =new Intent(getApplicationContext(),MainActivity.class);
        startActivity(otherActivity);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        MenuItem save = menu.findItem(R.id.save);
        save.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Enregistrer();
                return true;
            }
        });

        MenuItem reset = menu.findItem(R.id.reset);
        reset.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                initForm();
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
