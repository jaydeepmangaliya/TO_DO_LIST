package com.example.p1_to_do_list;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  EditText task;
  Button button;
  ListView listView;

  ArrayList<String> items;

  ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        task = findViewById(R.id.edit);
        button = findViewById(R.id.button);
        listView = findViewById(R.id.list);
        items = new ArrayList<>();
        items = FileForSaveData.readdata(this);
        arrayAdapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1 , android.R.id.text1,items);
         listView.setAdapter(arrayAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       String work = task.getText().toString();
       items.add(work);
       task.setText("");
       FileForSaveData.writedata(items ,getApplicationContext());
       arrayAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setTitle("DELETE");
                ab.setMessage("Do You Want To Delete ??");
                ab.setCancelable(false);
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        items.remove(position);
                     arrayAdapter.notifyDataSetChanged();
                     FileForSaveData.writedata(items , getApplicationContext());
                    }
                });



                AlertDialog a = ab.create();
                a.show();
            }
        });



    }



}