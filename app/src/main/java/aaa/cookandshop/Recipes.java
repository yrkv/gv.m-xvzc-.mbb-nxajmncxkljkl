package aaa.cookandshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Recipes extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

    }
    public void goToStarters(View view) {
        Intent intent = new Intent(this, ViewRecipes.class);
        intent.setAction("Recipes/Starters");
        startActivity(intent);
    }
    public void goToMain_dishes(View view) {

        Intent intent = new Intent(this, ViewRecipes.class);
        intent.setAction("Recipes/Main Dishes");
        startActivity(intent);
    }
    public void goToSides(View view) {

        Intent intent = new Intent(this, ViewRecipes.class);
        intent.setAction("Recipes/Sides");
        startActivity(intent);
    }
    public void goToDesserts(View view) {

        Intent intent = new Intent(this, ViewRecipes.class);
        intent.setAction("Recipes/Desserts");
        startActivity(intent);
    }

}
