package com.example.tt.read_write;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends ActionBarActivity {
    private Button updateButton;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_Text);
        updateButton = (Button) findViewById(R.id.updateButton);
        ReadFile();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WriteFile();
                ReadFile();
            }
        });
    }

public void ReadFile()
{
    String tmp;
    String out;
    try {
        FileInputStream in = openFileInput("Read_Write_File.txt");
        InputStreamReader inputStreamReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder builder = new StringBuilder();
        while ((tmp = bufferedReader.readLine())!=null)
        {
            builder.append(tmp);
            builder.append("\n");
        }
        out = builder.toString();
        editText.setText(out);
    }
    catch (FileNotFoundException e)
    {
        File f = new File("Read_Write_File.txt");// no file then create new file
    }
    catch (IOException e)
    {

    }

}

    public void WriteFile()
    {
        String tmp;

        try{
            FileOutputStream out = openFileOutput("Read_Write_File.txt",0);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(editText.getText().toString());
            writer.close();
        }
        catch (FileNotFoundException e){
            File f = new File("Read_Write_File.txt");
        }
        catch (IOException e){
            //do nothing
        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
