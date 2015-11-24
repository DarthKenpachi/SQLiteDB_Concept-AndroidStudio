package com.m5b10.sqlite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    EditText main_Input;
    TextView main_Text;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_Input = (EditText) findViewById(R.id.main_Input);
        main_Text = (TextView) findViewById(R.id.main_text);
        dbHandler = new MyDBHandler(this, null, null, 1);

        printDatabase();
    }

    //Add a product to the database
    public void addButtonClicked(){
        Products product = new Products(main_Input.getText().toString());   //create new product object
        dbHandler.addProduct(product);
        printDatabase();    //call to display on screen
    }


    public void deleteButtonClicked(View view){
        String inputText = main_Input.getText().toString();  //store input into String inputText
        dbHandler.deleteProduct(inputText); //call deleteProduct() w/inputText param
        printDatabase();    //call printDatabase()

    }

    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        main_Text.setText(dbString);    //set txt of string from dbHandler
        main_Input.setText(""); //set input text to blank
    }
}
