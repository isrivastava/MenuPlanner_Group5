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
import android.widget.EditText;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


/**
 * Created by Isha on 06-04-2018.
 */

public class EventActivity extends Activity implements OnClickListener{

    private ArrayList<String> eventNameString=new ArrayList<String>();
    private ArrayList<String> recipeNameString=new ArrayList<String>();
    private AutoCompleteTextView autoTextView;
    private AutoCompleteTextView autoTextView1;
    private ArrayAdapter<String>arrayAdapter;
    private ArrayAdapter<String>arrayAdapter1;

    AutoCompleteTextView eventName,recName;
    EditText noOfGuest,eventDate,servReq;
    Button showBtn,updateBtn,deleteBtn,showServ,addRec,delRec,homeBtn,listBtn,recBtn;
    ScrollView menuView;

    String eventID=null;
    String recID=null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);
        showBtn = (Button) this.findViewById(R.id.show_btn);
        showBtn.setOnClickListener(this);
        updateBtn = (Button) this.findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(this);
        deleteBtn = (Button) this.findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(this);
        homeBtn = (Button) this.findViewById(R.id.home_btn);
        homeBtn.setOnClickListener(this);
        listBtn = (Button) this.findViewById(R.id.list_btn);
        listBtn.setOnClickListener(this);
        recBtn = (Button) this.findViewById(R.id.rec_btn);
        recBtn.setOnClickListener(this);
        showServ = (Button) this.findViewById(R.id.show_serv);
        showServ.setOnClickListener(this);
        addRec = (Button) this.findViewById(R.id.add_rec);
        addRec.setOnClickListener(this);
        delRec = (Button) this.findViewById(R.id.del_rec);
        delRec.setOnClickListener(this);
        menuView = (ScrollView) this.findViewById(R.id.menu_view);


        noOfGuest = (EditText) this.findViewById(R.id.No_of_Guests);
        eventDate = (EditText) this.findViewById(R.id.eventDate);
        servReq = (EditText) this.findViewById(R.id.Serv_Req);

        eventName = (AutoCompleteTextView) this.findViewById(R.id.EventAutoComp);
        recName = (AutoCompleteTextView) this.findViewById(R.id.recAutoComp);


        Cursor cursor = DBOperator.getInstance().execQuery(
                SQLCommand.SELECT_EVENT);
        while (cursor.moveToNext()) {
            eventNameString.add(cursor.getString(0));
        }
        Cursor cursor_one = DBOperator.getInstance().execQuery(
                SQLCommand.SELECT_RECIPE);
        while (cursor_one.moveToNext()) {
            recipeNameString.add(cursor_one.getString(0));
        }
        initialize();

    }

    private void initialize() {
        autoTextView = (AutoCompleteTextView) this
                .findViewById(R.id.EventAutoComp);


        // use ArrayAdpater to add content to AutoCompleteTextView
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, eventNameString);
        autoTextView.setAdapter(arrayAdapter);

        autoTextView1 = (AutoCompleteTextView) this
                .findViewById(R.id.recAutoComp);


        // use ArrayAdpater to add content to AutoCompleteTextView
        arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, recipeNameString);
        autoTextView1.setAdapter(arrayAdapter1);
    }

    public void onClick(View v) {
        int id = v.getId();

        String[] args = null;
        args = new String[1];
        args[0]=eventName.getText().toString();

        String[] argsUpdate = null;
        argsUpdate = new String[3];

        String[] argsMenu = null;
        argsMenu = new String[1];

        String[] argsServ = null;
        argsServ = new String[1];
        argsServ[0]=recName.getText().toString();

        String[] argsRec = null;
        argsRec = new String[3];
        argsRec[0]=recID;
        argsRec[1]=eventID;
        argsRec[2]=servReq.getText().toString();

        String[] argsDel = null;
        argsDel = new String[2];


        if (id == R.id.show_btn) {
           menuView.removeAllViews();

            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_EVENT_BY_NAME,args);

            while (cursor.moveToNext()) {
                noOfGuest.setText(cursor.getString(4), TextView.BufferType.EDITABLE);
                eventDate.setText(cursor.getString(2), TextView.BufferType.EDITABLE);
                eventID=cursor.getString(0);
            }
            argsMenu[0]=eventID;

            Cursor cursor_three = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_EVENT_MENU,argsMenu);
            menuView.addView(new TableView(this.getBaseContext(),cursor_three));

        }
        if (id == R.id.update_btn) {
            argsUpdate[0]=noOfGuest.getText().toString();
            argsUpdate[1]=eventDate.getText().toString();
            argsUpdate[2]=eventID;

            DBOperator.getInstance().execSQL(SQLCommand.UPDATE_EVENT,argsUpdate);
            Toast.makeText(getBaseContext(), "Event Updated successfully", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.delete_btn) {
            argsMenu[0]=eventID;
            DBOperator.getInstance().execSQL(SQLCommand.DELETE_EVENT,argsMenu);
            Toast.makeText(getBaseContext(), "Event Deleted successfully", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.show_serv) {
            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_RECIPE_BY_NAME,argsServ);

            while (cursor.moveToNext()) {
                servReq.setText(cursor.getString(2), TextView.BufferType.EDITABLE);
                recID=cursor.getString(0);
            }
        }
        if (id == R.id.add_rec) {
            DBOperator.getInstance().execSQL(SQLCommand.ADD_RECIPE_MENU,argsRec);
            Toast.makeText(getBaseContext(), "Recipe for event inserted", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.del_rec) {
            argsDel[0]=eventID;
            argsDel[1]=recID;
            DBOperator.getInstance().execSQL(SQLCommand.DELETE_RECIPE_FROM_MENU,argsDel);
            Toast.makeText(getBaseContext(), "Recipe from event deleted", Toast.LENGTH_SHORT).show();

        }
        if (id == R.id.home_btn) {
            Intent intent = new Intent(this, HomePageActivity.class);
            this.startActivity(intent);
        }
        if(id == R.id.rec_btn){
            Intent intent = new Intent(this, RecipeActivity.class);
            this.startActivity(intent);

        }
        if(id == R.id.list_btn){
            Intent intent = new Intent(this, ManageListActivity.class);
            this.startActivity(intent);

        }


    }


}
