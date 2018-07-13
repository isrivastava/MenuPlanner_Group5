package library.group5;

/**
 * Created by Isha on 16-04-2018.
 */
import android.os.Bundle;
import library.group5.util.DBOperator;
import library.group5.view.TableView;
import library.group5.constant.SQLCommand;
import android.app.Activity;
import android.database.Cursor;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import java.util.ArrayList;


public class ShoppingListActivity extends Activity implements OnClickListener {

    private ArrayList<String> listNameString=new ArrayList<String>();
    private AutoCompleteTextView autoTextView;
    private ArrayAdapter<String>arrayAdapter;
    AutoCompleteTextView listName;
    Button showItem,homeBtn,logOutBtn;
    ScrollView ingView;
    String listID=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppinglist);

        showItem = (Button) this.findViewById(R.id.show_item);
        showItem.setOnClickListener(this);
        homeBtn = (Button) this.findViewById(R.id.shop_homebtn);
        homeBtn.setOnClickListener(this);
        logOutBtn = (Button) this.findViewById(R.id.log_out);
        logOutBtn.setOnClickListener(this);

        ingView = (ScrollView) this.findViewById(R.id.ing_view);
        listName = (AutoCompleteTextView) this.findViewById(R.id.list_auto_comp);

        Cursor cursor_one = DBOperator.getInstance().execQuery(
                SQLCommand.SELECT_LIST);
        while (cursor_one.moveToNext()) {
            listNameString.add(cursor_one.getString(0));
        }
        initialize();

    }

    private void initialize() {
        autoTextView = (AutoCompleteTextView) this
                .findViewById(R.id.list_auto_comp);

        // use ArrayAdpater to add content to AutoCompleteTextView
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listNameString);
        autoTextView.setAdapter(arrayAdapter);
    }

    public void onClick(View v) {
        int id = v.getId();

        String[] args = null;
        args = new String[1];
        args[0]=listName.getText().toString();

        String[] argList = null;
        argList = new String[1];


        if (id == R.id.show_item) {
            ingView.removeAllViews();

            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_LIST_BY_NAME,args);

            while (cursor.moveToNext()) {
                listID=cursor.getString(0);
            }
            argList[0]=listID;

            Cursor cursor_one = DBOperator.getInstance().execQuery(
                    SQLCommand.CREATE_SHOPPING_LIST,argList);
            ingView.addView(new TableView(this.getBaseContext(),cursor_one));

        }
        else if (id == R.id.shop_homebtn) {
            Intent intent = new Intent(this, HomePageActivity.class);
            this.startActivity(intent);
        }
        else if (id == R.id.log_out) {
            Intent intent = new Intent(this, group5Activity.class);
            this.startActivity(intent);
        }


    }

}
