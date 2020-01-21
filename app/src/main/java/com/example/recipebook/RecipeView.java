package com.example.recipebook;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class RecipeView extends CursorAdapter {

    public RecipeView(Context context, Cursor cursor) {
        super(context, cursor,0) ;
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

         String title =  cursor.getString( cursor.getColumnIndex("title")  ) ;
         // String recipe = cursor.getString( cursor.getColumnIndex("recipe")  ) ;
         String rtr  =    cursor.getString( cursor.getColumnIndex("rating")  ) ;
         int rating = Integer.parseInt(rtr) ;


         ImageView img = (ImageView)view.findViewById(R.id.imageView) ;
         img.setImageResource(R.drawable.rcp);

         TextView title_view = (TextView)view.findViewById(R.id.title_txt) ;
         title_view.setText(title);

         //TextView recipe_txt = (TextView)view.findViewById(R.id.txt) ;
         //recipe_txt.setText(recipe);

         RatingBar ratingBar  =(RatingBar)view.findViewById(R.id.recipe_rating) ;
         ratingBar.setRating(rating);




    }



}
