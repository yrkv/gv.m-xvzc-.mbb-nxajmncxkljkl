package aaa.cookandshop;

import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
    }

    public void addItem(View v) {
        addItem("");
    }

    public void addItem(String text) {
        // get the LinearLayout
        LinearLayout projectsList = (LinearLayout) findViewById(R.id.itemList);

        // make a copy of the button
        View button = getLayoutInflater().inflate(R.layout.shopping_list_item, projectsList, false);

        // set the text
        ((EditText)button.findViewById(R.id.itemText)).setText(text);

        // add it to the list
        projectsList.addView(button);

        // TODO: add it to a list of items

        ((CheckBox)button.findViewById(R.id.checkBox))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                //what happens when checkbox is checked.
            }
        });
    }
}
