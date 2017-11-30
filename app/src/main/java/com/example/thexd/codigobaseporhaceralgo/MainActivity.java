package com.example.thexd.codigobaseporhaceralgo;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.R.id.text1;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    Button btn;
    CheckBox chb;
    ArrayAdapter<String> adapter;
    ArrayList<String> values = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("h.txt")));
            String texto ;
            while((texto=fin.readLine())!=null){
                values.add(texto);
            }
           fin.close();
        }
        catch (Exception ex)
        {
            for (int i=0;i<100;i++){
                values.add("Vamos");
            }

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lst);
        btn=(Button) findViewById(R.id.btn);
        chb=(CheckBox) findViewById(R.id.chc);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,  android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
                if (chb.isChecked()) {
                    values.remove(itemPosition);
                }else{
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();}
    guardado();
            }

        });

        guardado();
    }


    public void pulsador(View v){

        values.set(2,"Que no copn");
        // Assign adapter to ListView
        listView.setAdapter(adapter);
    guardado();
    }
    public void guardado(){
        try {
            OutputStreamWriter fout= new OutputStreamWriter(openFileOutput("h.txt", Context.MODE_PRIVATE));
            for (int i=0;i<values.size();i++){
                fout.write(values.get(i));
                fout.write("\n");
     }
            fout.close();
            listView.setAdapter(adapter);
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }

    }


}
