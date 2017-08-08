package aaa.cookandshop;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewRecipes extends AppCompatActivity {

    private static final int RECIPE_COUNT = 3;

    private String[][] recipes = new String[3][];
    private ArrayList<String> categoryButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.view_recipes);

        String action = getIntent().getAction();

        ((TextView) findViewById(R.id.recipesDirectory)).setText(action);

        String[] currentDir = action.split("/");

        Resources r = getResources();

        for (int i = 0; i < RECIPE_COUNT; i++) {
            int resource = r.getIdentifier("recipe"+i, "array", getPackageName());
            recipes[i] = r.getStringArray(resource);
        }


        for (int i = 0; i < RECIPE_COUNT; i++) {
            String[] tags = recipes[i][1].split("\\.");

            for (int j = 0; j < tags.length; j++) {
                if (j < currentDir.length) {
                    if (!currentDir[j].equals(tags[j])) {
                        break;
                    }
                } else {
                    if (!categoryButtons.contains(tags[j])) {
                        categoryButtons.add(tags[j]);
                        addButton(action, tags[j]);
                    }
                    break;
                }
            }

            boolean showButton = tags.length >= currentDir.length;
            if (showButton) {
                for (int j = 0; j < currentDir.length; j++) {
                    if (!currentDir[j].equals(tags[j])) {
                        showButton = false;
                        break;
                    }
                }
            }
            if (showButton)
                addRecipeButton(i);
        }


    }


    public void addButton(final String dir, final String text) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.recipeTypes);

        final View button = getLayoutInflater().inflate(R.layout.recipes_button, layout, false);

        // set the text
        ((TextView)button.findViewById(R.id.textWhatever)).setText(text);

        // add it to the Layout
        layout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThis(), ViewRecipes.class);
                intent.setAction(dir + "/" + text);
                startActivity(intent);
            }
        });
    }

    public void addRecipeButton(int num) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.recipesDisplay);

        final View button = getLayoutInflater().inflate(R.layout.recipe_button, layout, false);

        ((TextView)button.findViewById(R.id.textWhatever)).setText(recipes[num][0]);

        layout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: have a page that shows a single recipe

                // go to that page here
            }
        });
    }

    private ViewRecipes getThis() {
        return this;
    }

}
