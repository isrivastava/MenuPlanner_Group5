package library.group5;

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
import android.widget.Toast;
import java.util.ArrayList;
/**
 * Created by Isha on 06-04-2018.
 */

public class ManageListActivity extends Activity implements OnClickListener{

        private ArrayList<String> listNameString=new ArrayList<String>();
        private ArrayList<String> eventNameString=new ArrayList<String>();
        private AutoCompleteTextView autoTextView;
        private AutoCompleteTextView autoTextView1;
        private ArrayAdapter<String>arrayAdapter;
        private ArrayAdapter<String>arrayAdapter1;

       AutoCompleteTextView listName,eventName;
       Button showEvent,delList,addEvent,delEvent,homeBtn,recBtn,eventBtn,ShopBtn;
       ScrollView eventView;
       String listID=null, eventID=null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managelist);

        showEvent = (Button) this.findViewById(R.id.show_event);
        showEvent.setOnClickListener(this);
        delList = (Button) this.findViewById(R.id.del_list);
        delList.setOnClickListener(this);
        addEvent = (Button) this.findViewById(R.id.add_event);
        addEvent.setOnClickListener(this);
        delEvent = (Button) this.findViewById(R.id.del_event);
        delEvent.setOnClickListener(this);
        homeBtn = (Button) this.findViewById(R.id.home_Btn);
        homeBtn.setOnClickListener(this);
        recBtn = (Button) this.findViewById(R.id.rec_Btn);
        recBtn.setOnClickListener(this);
        eventBtn = (Button) this.findViewById(R.id.event_btn);
        eventBtn.setOnClickListener(this);
        ShopBtn = (Button) this.findViewById(R.id.shop_Btn);
        ShopBtn.setOnClickListener(this);

        eventView = (ScrollView) this.findViewById(R.id.event_view);

        listName = (AutoCompleteTextView) this.findViewById(R.id.listAutoComp);
        eventName = (AutoCompleteTextView) this.findViewById(R.id.eventAutoComp);


        Cursor cursor_one = DBOperator.getInstance().execQuery(
                SQLCommand.SELECT_LIST);
        while (cursor_one.moveToNext()) {
            listNameString.add(cursor_one.getString(0));
        }

        Cursor cursor_two = DBOperator.getInstance().execQuery(
                SQLCommand.SELECT_EVENT);
        while (cursor_two.moveToNext()) {
            eventNameString.add(cursor_two.getString(0));
        }
        initialize();
    }

    private void initialize() {
        autoTextView = (AutoCompleteTextView) this
                .findViewById(R.id.listAutoComp);

        // use ArrayAdpater to add content to AutoCompleteTextView
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, listNameString);
        autoTextView.setAdapter(arrayAdapter);

        autoTextView1 = (AutoCompleteTextView) this
                .findViewById(R.id.eventAutoComp);


        arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, eventNameString);
        autoTextView1.setAdapter(arrayAdapter1);
    }

    public void onClick(View v) {
        int id = v.getId();

        String[] args = null;
        args = new String[1];
        args[0]=listName.getText().toString();

        String[] argsId = null;
        argsId = new String[1];
        argsId[0]=eventName.getText().toString();

        String[] argList = null;
        argList = new String[1];

        String[] argEvent = null;
        argEvent = new String[2];

        String[] argDel = null;
        argDel = new String[1];


        if (id == R.id.show_event) {
            eventView.removeAllViews();

            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_LIST_BY_NAME,args);

            while (cursor.moveToNext()) {
                listID=cursor.getString(0);
            }
            argList[0]=listID;

            Cursor cursor_one = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_EVENT_LIST,argList);
            eventView.addView(new TableView(this.getBaseContext(),cursor_one));


        }
        else if(id == R.id.del_list){
            argList[0]=listID;
            DBOperator.getInstance().execSQL(SQLCommand.DELETE_LIST,argList);
            Toast.makeText(getBaseContext(), "List Deleted successfully", Toast.LENGTH_SHORT).show();

        }
        else if(id == R.id.add_event){
            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_EVENT_BY_NAME,argsId);
            while (cursor.moveToNext()) {
                eventID=cursor.getString(0);
            }
            argEvent[0]=listID;
            argEvent[1]=eventID;
            DBOperator.getInstance().execSQL(SQLCommand.ADD_EVENT_TO_LIST,argEvent);
            Toast.makeText(getBaseContext(), "Event Added to the list successfully", Toast.LENGTH_SHORT).show();

        }
        else if(id == R.id.del_event){
            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_EVENT_BY_NAME,argsId);
            while (cursor.moveToNext()) {
                eventID=cursor.getString(0);
            }
            argDel[0]=eventID;
            DBOperator.getInstance().execSQL(SQLCommand.DELETE_EVENT_FROM_LIST,argDel);
            Toast.makeText(getBaseContext(), "Event deleted from the list successfully", Toast.LENGTH_SHORT).show();

        }
        else if(id == R.id.shop_Btn){
            Intent intent = new Intent(this, ShoppingListActivity.class);
            this.startActivity(intent);
        }
        else if(id == R.id.home_Btn){
            Intent intent = new Intent(this, HomePageActivity.class);
            this.startActivity(intent);
        }
        else if(id == R.id.event_btn){
            Intent intent = new Intent(this, EventActivity.class);
            this.startActivity(intent);
        }
        else if(id == R.id.rec_Btn){
            Intent intent = new Intent(this, RecipeActivity.class);
            this.startActivity(intent);
        }


    }
}
