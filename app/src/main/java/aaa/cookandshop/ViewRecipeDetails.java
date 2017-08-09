package aaa.cookandshop;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Kaleb on 8/8/2017.
 */

public class ViewRecipeDetails extends AppCompatActivity {
    private String[] recipe;

    private static final boolean LEFT = false;
    private static final boolean RIGHT = true;

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

        String ingredientsText = "";
        for (int i = 4; i < recipe.length; i++) {
            ingredientsText += recipe[i] + "\n\n";
        }

        ingredients.setText(ingredientsText);


        String detailsText = "";

        detailsText += recipe[1] + "\n\n" + recipe[3];

        details.setText(detailsText);
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
