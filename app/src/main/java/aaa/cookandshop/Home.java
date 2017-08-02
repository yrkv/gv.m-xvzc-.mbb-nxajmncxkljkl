package aaa.cookandshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void goToShoppingList(View view) {

        Intent intent = new Intent(this, ShoppingList.class);

        startActivity(intent);
    }

    public void goToRecipes(View view) {

        Intent intent2 = new Intent(this, Recipes.class);

        startActivity(intent2);
    }
}
