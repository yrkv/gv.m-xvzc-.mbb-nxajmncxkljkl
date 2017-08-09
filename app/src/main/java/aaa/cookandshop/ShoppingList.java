package aaa.cookandshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArraySet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
        load();
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }

    private void save() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(getString(R.string.listData), SavingThing.toString(list));
        editor.apply();
    }

    private void load() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String data = sharedPref.getString(getString(R.string.listData), "");

        if (!data.equals("")) {
            ArrayList<String> newList = SavingThing.toArrayList(data);

            for (String text : newList) {
                addItem(text);
            }
        }
    }

    public void addItem(View v) {
        addItem(((EditText) findViewById(R.id.inputItem)).getText().toString());
    }

    public void removeItem(View v) {
        LinearLayout projectsList = (LinearLayout) findViewById(R.id.itemList);
        projectsList.removeView(v);
        int id = v.getId();
        list.remove(id);
        list2.remove(id);

        for(id = v.getId(); id < list2.size(); id++) {
            System.out.println(list2.get(id).getId());
            list2.get(id).setId(id);
            System.out.println(list2.get(id).getId());
        }
    }

    public void selectAddMethod(View v) {
        final View window = getLayoutInflater().inflate(R.layout.add_item_popup, (ViewGroup) findViewById(R.id.popupWindow), false);

        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                250,
                getResources().getDisplayMetrics()
        );

        final PopupWindow popup = new PopupWindow(window, px, (int) (px / 1.5), true);

        popup.showAtLocation(window, Gravity.CENTER, 0, 0);

        Button button = (Button) window.findViewById(R.id.popupButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(((EditText) window.findViewById(R.id.inputItem)).getText().toString());
                popup.dismiss();
            }
        });
    }

    public void addItem(String text) {
        // get the LinearLayout
        LinearLayout projectsList = (LinearLayout) findViewById(R.id.itemList);

        // make a copy of the button
        final View button = getLayoutInflater().inflate(R.layout.shopping_list_item, projectsList, false);

        // set the text
        ((TextView)button.findViewById(R.id.itemText)).setText(text);

        button.setId(list.size());

        // add it to the Layout
        projectsList.addView(button);

        // add it to a list
        list.add(text);
        list2.add(button);

        ((TextView)button.findViewById(R.id.itemText)).addTextChangedListener(new TextWatcher() {
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
