package com.example.recipebook;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


public class RecipeList extends AppCompatActivity {

    ListView listview ;

    Cursor cursor  ;
    RecipeView adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        listview  = (ListView)findViewById(R.id.recipe_list) ;

        cursor = getContentResolver().query(RecipeProvider.CONTENT_URI, null , null , null , "rating DESC");

        adapter = new RecipeView(this, cursor );

        adapter.notifyDataSetChanged();

        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   cursor.moveToPosition(position) ;
                   String title =   cursor.getString(cursor.getColumnIndex("title"))  ;
                    Intent intent = new Intent(RecipeList.this , SingleRecipe.class) ;
                    intent.putExtra("title" , title) ;
                    startActivity(intent) ;

            }

        });
    }


    public void back(View view){
        Intent intent = new Intent(this , MainActivity.class)  ;
        startActivity(intent);

    }

    public void sort_title(View view){


        cursor = getContentResolver().query(RecipeProvider.CONTENT_URI, null , null , null , "title");
        adapter.notifyDataSetChanged();
        adapter.swapCursor(cursor) ;


    }

    public void sort_rating(View view){


        cursor = getContentResolver().query(RecipeProvider.CONTENT_URI, null , null , null , "rating DESC");
        adapter.notifyDataSetChanged();
        adapter.swapCursor(cursor) ;


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this , MainActivity.class)  ;
        startActivity(intent);
    }




}
