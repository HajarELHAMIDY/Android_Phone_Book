package com.example.phonebook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  ListView lvListe;
    private View v ;
    private MainActivity activity = this;
    DBconnexion db = new DBconnexion(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Listes des Contacts");

        TextView email = findViewById(R.id.email);

        FloatingActionButton fab = findViewById(R.id.fab1);

        initListeView();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherActivity =new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(otherActivity);
            }
        });
        lvListe.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           int pos, long id) {
                supprimer(view);


                return true;
            }
        });


    }

    public void initListeView(){

        lvListe = (ListView)findViewById(R.id.listeContact);

        Adapter contactAdapter = new Adapter(this, db.getAllRecord());
        lvListe.setAdapter(contactAdapter);


    }

    public void initListeView(String mc){
        ListView lvListe = (ListView)findViewById(R.id.listeContact);
        Adapter collaborateurAdapter = new Adapter(this, db.getByKey(mc));
        lvListe.setAdapter(collaborateurAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem =menu.findItem(R.id.search);

        android.widget.SearchView  searchView = (android.widget.SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                initListeView(newText);

                return true;
            }
        });



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void MakePhoneCall(String phone){
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null)));
    }
    public void appeler(View view){
        View parent= (View) view.getParent();
        TextView phone=parent.findViewById(R.id.tel);
        MakePhoneCall(phone.getText().toString());
    }
    public void supprimer(View view){
        this.v = view;
        AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);

        TextView nomText = v.findViewById(R.id.lastName);
        myPopup.setTitle("Confirmation");

        myPopup.setMessage("Vous voulez vraiment supprimer l'enregistrement !!!");
        myPopup.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView idText = v.findViewById(R.id.id);

                db.deleteRow(Integer.parseInt((String) idText.getText()));
                Toast.makeText(getApplicationContext(),"l'enregistrement sera supprimer",Toast.LENGTH_SHORT).show();

                initListeView();
                 //   MainActivity.super.onResume();



            }
        });

        myPopup.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"l'enregistrement reste toujours dans la liste",Toast.LENGTH_SHORT).show();
            }
        });
        myPopup.show();
    }


    public void Modifier(View view) {
        Intent intent= new Intent(this,MainActivity3.class);
        Bundle data=new Bundle();

        TextView id_view=view.findViewById(R.id.id);

        Integer id_update=Integer.parseInt((String) id_view.getText());
        data.putInt("id", id_update);
        intent.putExtra("data",data);
        startActivity(intent);
    }
}
