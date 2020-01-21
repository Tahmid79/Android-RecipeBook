package com.example.recipebook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText recipe_txt ; EditText title_txt ;

    Button add_btn ; Button show_btn ;
    RatingBar rating_bar  ;
    ListView listview ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recipe_txt = (EditText)findViewById(R.id.recipe_txt) ;
        title_txt = (EditText)findViewById(R.id.title_txt) ;

        add_btn = (Button)findViewById(R.id.insert_btn) ;
        show_btn = (Button)findViewById(R.id.show_btn) ;

        rating_bar = (RatingBar)findViewById(R.id.ratingBar) ;





    }

    public void AddRecipe(View view){

        ContentValues values = new ContentValues() ;

        String recipe = recipe_txt.getText().toString()  ;
        String title =  title_txt.getText().toString() ;
        int rating_val =  (int)rating_bar.getRating() ;
        String rating =  Integer.toString(rating_val) ;

        values.put("title" , title  );
        values.put("recipe" , recipe) ;
        values.put("rating"  , rating) ;

        getContentResolver().insert(RecipeProvider.CONTENT_URI , values) ;


    }


    public void showRecipes(View view){

       // Cursor cursor = getContentResolver().query(RecipeProvider.CONTENT_URI, null , null , null , null);

        //RecipeView adapter = new RecipeView(this, cursor);
        //listview.setAdapter(adapter);

        Intent intent = new Intent(this , RecipeList.class) ;
        startActivity(intent) ;

    }





}
