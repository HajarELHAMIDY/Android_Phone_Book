package com.example.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBconnexion extends SQLiteOpenHelper {

    public DBconnexion(Context context) {
        super(context, "contact.db", null, 1);
    }

    public void insertNewContact(String nom,String prenom,String job,String tel,String email){
        SQLiteDatabase wDB = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("nom", nom);
        contentValue.put("prenom", prenom);
        contentValue.put("job", job);
        contentValue.put("tel", tel);
        contentValue.put("email", email);

        wDB.insert("Contact",null,contentValue);
    }
    public void deleteRow(Integer id){
        SQLiteDatabase wDB = this.getWritableDatabase();
        String[] s = new String[]{String.valueOf(id)};
        wDB.delete("Contact","id=?",s);

    }
    public Contact getRow(Integer id){
        SQLiteDatabase rDB = this.getReadableDatabase();
        Cursor res = rDB.rawQuery("Select * FROM Contact where ID=?",new String[]{String.valueOf(id)});
        if (res.moveToFirst()) {
            Contact c = new Contact(res.getInt(res.getColumnIndex("id")), res.getString(res.getColumnIndex("prenom")), res.getString(res.getColumnIndex("nom")),
                    res.getString(res.getColumnIndex("job")), res.getString(res.getColumnIndex("email")), res.getString(res.getColumnIndex("tel")));
            return c;
        }
       return null;

    }
    public void update(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",contact.getFirstName());
        cv.put("prenom",contact.getLastName());
        cv.put("job",contact.getJob());
        cv.put("tel",contact.getPhone());
        cv.put("email",contact.getEmail());
        db.update("contact",cv,"id=?",new String[]{Long.toString(contact.getId())});
    }
    public ArrayList<Contact> getAllRecord(){
        ArrayList<Contact> maliste = new ArrayList();
        SQLiteDatabase rDB = this.getReadableDatabase();
        Cursor res = rDB.rawQuery("Select * FROM Contact",null);
        res.moveToFirst();
        while(res.isAfterLast()== false){
            maliste.add( new Contact(res.getInt(res.getColumnIndex("id")),res.getString(res.getColumnIndex("prenom")),res.getString(res.getColumnIndex("nom")),
                    res.getString(res.getColumnIndex("job")),res.getString(res.getColumnIndex("email")),res.getString(res.getColumnIndex("tel"))));
            res.moveToNext();
       }
        return maliste;

    }
    public List<Contact> getByKey(String key){
        List<Contact> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Contact WHERE nom like ? OR prenom like ? OR job like ? OR email like ? OR tel like ?",new String[]{"%"+key+"%","%"+key+"%","%"+key+"%","%"+key+"%","%"+key+"%"});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add( new Contact(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("prenom")),cursor.getString(cursor.getColumnIndex("nom")),
                        cursor.getString(cursor.getColumnIndex("job")),cursor.getString(cursor.getColumnIndex("email")),cursor.getString(cursor.getColumnIndex("tel"))));
                cursor.moveToNext();
            }
        }

        return list;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Contact (id INTEGER PRIMARY KEY, nom TEXT, prenom TEXT, job TEXT, tel TEXT, email TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Contact");
        onCreate(db);
    }

}

