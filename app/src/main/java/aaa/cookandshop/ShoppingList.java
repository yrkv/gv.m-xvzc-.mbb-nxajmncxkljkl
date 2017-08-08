package aaa.cookandshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArraySet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity {

    // TODO: find some way to save this shit
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<View> list2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
    }

    public void addItem(View v) {
        addItem("");
    }

    public void removeItem(View v) {
        LinearLayout projectsList = (LinearLayout) findViewById(R.id.itemList);
        projectsList.removeView(v);
        list.remove(0);

    }

    public void selectAddMethod(View v) {
        System.out.println("ijawjiawod");
        RelativeLayout shoppingScreen = (RelativeLayout) findViewById(R.id.shoppingScreen);
        final View Popup = getLayoutInflater().inflate(R.layout.add_item_popup, shoppingScreen, false);
        shoppingScreen.addView(Popup);
    }

    public void addItem(String text) {
        // get the LinearLayout
        LinearLayout projectsList = (LinearLayout) findViewById(R.id.itemList);

        // make a copy of the button
        final View button = getLayoutInflater().inflate(R.layout.shopping_list_item, projectsList, false);

        // set the text
        ((EditText)button.findViewById(R.id.itemText)).setText(text);

        button.setId(list.size());

        // add it to the Layout
        projectsList.addView(button);

        // add it to a list
        list.add(text);
        list2.add(button);

        ((EditText)button.findViewById(R.id.itemText)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                list.set(button.getId(), s.toString());
            }
        });

//        ((CheckBox)button.findViewById(R.id.checkBox))
//                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
//                removeItem();
//                what happens when checkbox is checked.
//            }
//        });
    }
}
