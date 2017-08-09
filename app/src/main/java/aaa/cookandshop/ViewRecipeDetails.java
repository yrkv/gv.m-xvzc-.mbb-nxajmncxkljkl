package aaa.cookandshop;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
                findViewById(R.id.instructionsText).setLayoutParams(p);


            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    int diffX = scrollX - oldScrollX;
                    if (diffX < 0 && side == RIGHT) {
                        scrollLeft(v);
                    } else if (diffX > 0 && side == LEFT) {
                        scrollRight(v);
                    }
                }
            });
        }


    }

    public void scrollRight(View v) {
        side = RIGHT;
        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.someThing);
        scrollView.smoothScrollTo(scrollView.getWidth(), 0);

        TextView details = (TextView) findViewById(R.id.details);
        TextView instructions = (TextView) findViewById(R.id.instructions);

        instructions.setBackgroundColor(0xaaffffff);
        instructions.setTextColor(0xff000000);

        details.setBackgroundColor(0x44000000);
        details.setTextColor(0xffffffff);
    }

    public void scrollLeft(View v) {
        side = LEFT;
        HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.someThing);
        scrollView.smoothScrollTo(0, 0);

        TextView details = (TextView) findViewById(R.id.details);
        TextView instructions = (TextView) findViewById(R.id.instructions);

        details.setBackgroundColor(0xaaffffff);
        details.setTextColor(0xff000000);

        instructions.setBackgroundColor(0x44000000);
        instructions.setTextColor(0xffffffff);
    }
}
