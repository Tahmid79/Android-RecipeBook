package com.example.recipebook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class SingleRecipe extends AppCompatActivity {

    TextView title  ;
    TextView recipe ;
    Cursor cursor ;
    Intent received_intent ;
    RatingBar ratingbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recipe);

        title = (TextView)findViewById( R.id.title_recipe ) ;
        recipe = (TextView)findViewById(R.id.text_recipe) ;
        ratingbar = (RatingBar)findViewById( R.id.ratingBar2 ) ;

        showRecipe();


        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ContentValues values = new ContentValues() ;
                int rtr = (int)rating ;
                String rtng = Integer.toString(rtr) ;

                values.put("rating" , rtng);

                received_intent = getIntent() ;
                String nm = received_intent.getStringExtra("title") ;
                String [] ags = {nm} ;

                getContentResolver().update(RecipeProvider.CONTENT_URI , values , "title = ?" , ags) ;




            }
        });

    }

    public void showRecipe(){

        received_intent = getIntent() ;
        String nm = received_intent.getStringExtra("title") ;

        title.setText(nm) ;

        String [] ags  = {""} ;
        ags[0] = nm ;

        String rcp  = "";
        String rating  = " ";

         cursor =  getContentResolver().query(RecipeProvider.CONTENT_URI, null , "title = ?" , ags , null);

        if( cursor.moveToFirst() ) {

            while (!cursor.isAfterLast()) {
                rcp = cursor.getString(cursor.getColumnIndex("recipe"));
                rating = cursor.getString(cursor.getColumnIndex("rating"));
                cursor.moveToNext()  ;
            }
        }

        recipe.setText(rcp);

        int rt = Integer.parseInt(rating) ;

        ratingbar.setRating(rt);


    }

    public void delete(View view){

        String nm = received_intent.getStringExtra("title") ;

        title.setText(" ");
        recipe.setText(" ") ;

        String [] ags = {nm} ;
        String sel_clause = "title = ?"  ;

        int deleted = getContentResolver().delete(RecipeProvider.CONTENT_URI , sel_clause , ags ) ;

        Intent intent = new Intent(this , RecipeList.class) ;
        startActivity(intent) ;


    }

    public void back(View view){
        Intent intent = new Intent(this , RecipeList.class)  ;
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this , RecipeList.class)  ;
        startActivity(intent);
    }


}
