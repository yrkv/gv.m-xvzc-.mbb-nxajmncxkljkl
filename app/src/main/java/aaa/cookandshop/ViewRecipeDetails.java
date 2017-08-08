package aaa.cookandshop;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Kaleb on 8/8/2017.
 */

public class ViewRecipeDetails extends AppCompatActivity {

    private final int RECIPE_COUNT = 3;

    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe_details);
        num = Integer.parseInt(getIntent().getAction());
        Resources r = getResources();
        String[][] recipes = new String[RECIPE_COUNT][];
        for (int i = 0; i < RECIPE_COUNT; i++) {
            int resource = r.getIdentifier("recipe"+i, "array", getPackageName());
            recipes[i] = r.getStringArray(resource);
        }


    }

}
