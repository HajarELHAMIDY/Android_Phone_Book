package com.example.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity3 extends AppCompatActivity {


    Integer id ;
    DBconnexion db = new DBconnexion(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        setTitle("Modifier Contact");

        if (getIntent().getExtras() != null) {
            Bundle data=getIntent().getBundleExtra("data");
            this.id = data.getInt("id");
            initData(id);
        }

        FloatingActionButton fab = findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity =new Intent(getApplicationContext(),MainActivity.class);
               startActivity(otherActivity);
            }
        });

    }
    void initData(Integer id){
        Contact c=db.getRow(id);

        EditText last_name=findViewById(R.id.ELastNameEdit);
        EditText first_name=findViewById(R.id.EFirstNameEdit);
        EditText job=findViewById(R.id.EJobEdit);
        EditText phone=findViewById(R.id.EPhoneEdit);
        EditText email=findViewById(R.id.EemailEdit);

        last_name.setText(c.getLastName());
        first_name.setText(c.getFirstName());
        email.setText(c.getEmail());
        phone.setText(c.getPhone());
        job.setText(c.getJob());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main3, menu);

        return true;
    }
    public void updateContact(){
        Contact c=db.getRow(this.id);
        EditText last_name=findViewById(R.id.ELastNameEdit);
        EditText first_name=findViewById(R.id.EFirstNameEdit);
        EditText job=findViewById(R.id.EJobEdit);
        EditText phone=findViewById(R.id.EPhoneEdit);
        EditText email=findViewById(R.id.EemailEdit);
        c.setEmail(String.valueOf(email.getText()));
        c.setFirstName(String.valueOf(first_name.getText()));
        c.setJob(String.valueOf(job.getText()));
        c.setLastName(String.valueOf(last_name.getText()));
        c.setPhone(String.valueOf(phone.getText()));
        db.update(c);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.up:
                updateContact();
                Intent otherActivity =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(otherActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }


}
