package com.example.vij.one;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editlname,editfname,editemail,editTextid;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnviewDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb=new DatabaseHelper(this);

        editfname= (EditText) findViewById(R.id.editText_fname);
        editlname= (EditText) findViewById(R.id.editText_lname);
        editemail= (EditText) findViewById(R.id.editText_email);
        btnAddData= (Button) findViewById(R.id.button_add);
        btnviewAll= (Button) findViewById(R.id.button_viewAll);
        btnviewUpdate= (Button) findViewById(R.id.button_update);
        editTextid= (EditText) findViewById(R.id.editText_id);
        btnviewDelete= (Button) findViewById(R.id.button_delete);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }

    private void DeleteData() {
        btnviewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows=myDb.DeleteData(editTextid.getText().toString());
                if (deletedRows > 0){
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void UpdateData(){
        btnviewUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate=myDb.updateData(editfname.getText().toString(), editlname.getText().toString(), editemail.getText().toString(), editTextid.getText().toString());
                if (isUpdate == true){
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editTextid.getText().toString(),editfname.getText().toString(),
                        editlname.getText().toString(),
                        editemail.getText().toString());
                if (isInserted == true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void viewAll(){
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=myDb.getAllData();
                if(res.getCount() == 0){
                    //show Message
                    showMessage("Error","Nothing found");
                    return ;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("id :"+ res.getString(0)+ "\n");
                    buffer.append("fname :"+ res.getString(1)+ "\n");
                    buffer.append("lname :"+ res.getString(2)+ "\n");
                    buffer.append("email :"+ res.getString(3)+ "\n\n");
                }
                //show all Data
                showMessage("Data",buffer.toString());
                return;
            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.menu_Gujarati:
                switch (item.getItemId()){
                    case R.id.menu_Gujarati:
                        startActivity(new Intent(this,Gujarati.class));
                }
                Toast.makeText(this," welcome to Gujarati Page",Toast.LENGTH_SHORT).show();
                break;


            case R.id.menu_Panjabi:
                switch (item.getItemId()){
                    case R.id.menu_Panjabi:
                        startActivity(new Intent(this,Panjabi.class));
                        Toast.makeText(this," welcome to Panjabi Page",Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.menu_Chinese:
                switch (item.getItemId()){
                    case R.id.menu_Chinese:
                        startActivity(new Intent(this,Chinese.class));
                        Toast.makeText(this," welcome to Chinese Page",Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return true;
    }
}
