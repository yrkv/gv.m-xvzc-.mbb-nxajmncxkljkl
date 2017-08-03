package aaa.cookandshop;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity {

    ArrayList<CheckBox> listBoxes = new ArrayList<CheckBox>(); //sets up 2 parallel arraylists for boxes and texts
    ArrayList<EditText> listTexts = new ArrayList<EditText>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
    }

    public void addItem(View view) {
       // setContentView(R.layout.activity_projects);

       // EditText editText = (EditText) findViewById(R.id.editTextNameProject);

       // String name = editText.getText().toString();

        LinearLayout projectsList = (LinearLayout) findViewById(R.id.itemList);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT );

        CheckBox box = new CheckBox(this);

        EditText text = new EditText(this);

        /*
        set attributes here
         */
        Resources r = getResources();

        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                20,
                r.getDisplayMetrics()
        );

        params.setMargins(0, 0, 0, px);

        box.setLayoutParams(params);

        text.setLayoutParams(params);

        projectsList.addView(box);
        projectsList.addView(text);

        // add the edittext to list

        listBoxes.add(box);
        listTexts.add(text);
    }
}
