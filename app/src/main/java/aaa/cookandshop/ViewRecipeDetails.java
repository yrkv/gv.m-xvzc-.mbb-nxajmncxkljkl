package aaa.cookandshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kaleb on 8/8/2017.
 */

public class ViewRecipeDetails extends AppCompatActivity {
    private String[] recipe;

    private static final boolean LEFT = false;
    private static final boolean RIGHT = true;
    private String ingredientsText = "";

    private boolean side = LEFT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe_details);

        Resources r = getResources();
        int resource = r.getIdentifier("recipe"+getIntent().getAction(), "array", getPackageName());
        recipe = r.getStringArray(resource);

        ((TextView) findViewById(R.id.recipeName)).setText(recipe[0]);

        final HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.someThing);

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                        scrollView.getWidth(),
                        LinearLayout.LayoutParams.MATCH_PARENT
                );

                findViewById(R.id.detailsText).setLayoutParams(p);
                findViewById(R.id.ingredientsText).setLayoutParams(p);
            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        TextView details = (TextView) findViewById(R.id.detailsText);
        TextView ingredients = (TextView) findViewById(R.id.ingredientsText);
        TextView serves = (TextView) findViewById(R.id.servesX);

        serves.setText(getString(R.string.serves) + " " + recipe[2]);

        ingredientsText = "";
        for (int i = 4; i < recipe.length; i++) {
            String[] split = recipe[i].split(",");
            ingredientsText += split[1] + " - " + split[0] + "\n\n";
        }

        ingredients.setText(ingredientsText);


        String detailsText = "";

        detailsText += recipe[1] + "\n\n" + recipe[3];

        details.setText(detailsText);
    }

    public void addRecipeToFavorites(View v) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        ArrayList<String> favoritedRecipes = SavingThing.toArrayList(sharedPref.getString(getString(R.string.favoriteData), ""));
        //TODO: add the recipe LOL
        //favoritedRecipes.add()
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.favoriteData), SavingThing.toString(favoritedRecipes));
        editor.apply();
    }

    public void addRecipeToShoppingList(View v) {
        String[] ingredients = ingredientsText.split("\\n\\n");
        for(String item : ingredients) {
            addItem(item);
        }

        Toast.makeText(this, "Recipe Added", Toast.LENGTH_SHORT).show();
    }

    public void addItem(String item) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        ArrayList<String> shoppingList = SavingThing.toArrayList(sharedPref.getString(getString(R.string.listData), ""));
        shoppingList.add(item);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.listData), SavingThing.toString(shoppingList));
        editor.apply();
    }

    public void scrollRight(View v) {
        side = RIGHT;
        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.someThing);
        scrollView.smoothScrollTo(scrollView.getWidth(), 0);

        TextView details = (TextView) findViewById(R.id.details);
        TextView ingredients = (TextView) findViewById(R.id.ingredients);

        ingredients.setBackgroundColor(0xaaffffff);
        ingredients.setTextColor(0xff000000);

        details.setBackgroundColor(0x44000000);
        details.setTextColor(0xffffffff);
    }

    public void scrollLeft(View v) {
        side = LEFT;
        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.someThing);
        scrollView.smoothScrollTo(0, 0);

        TextView details = (TextView) findViewById(R.id.details);
        TextView ingredients = (TextView) findViewById(R.id.ingredients);

        details.setBackgroundColor(0xaaffffff);
        details.setTextColor(0xff000000);

        ingredients.setBackgroundColor(0x44000000);
        ingredients.setTextColor(0xffffffff);
    }
}
