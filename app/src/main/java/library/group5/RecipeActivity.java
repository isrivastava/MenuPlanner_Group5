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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Isha on 06-04-2018.
 */

public class RecipeActivity extends Activity implements OnClickListener{
    private ArrayList<String> recipeNameString=new ArrayList<String>();
    private ArrayList<String> ingNameString=new ArrayList<String>();
    private ArrayList<String> unitNameString=new ArrayList<String>();


    private AutoCompleteTextView autoTextView;
    private AutoCompleteTextView autoTextView1;
    private Spinner spinner;
    private ArrayAdapter<String>arrayAdapter;
    private ArrayAdapter<String>arrayAdapter1;
    private ArrayAdapter<String>arrayAdapter2;

    AutoCompleteTextView recName,ingName;
    EditText noOfServ,qty;
    Button showIngBtn,updateRecBtn,deleteRecBtn,updateIng,addIng,delIng,homeBtn,listBtn,eventBtn;
    ScrollView recView;
    Spinner unitSpinner;

    String recID=null,ingID=null,unitID=null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);

        showIngBtn = (Button) this.findViewById(R.id.showIng_btn);
        showIngBtn.setOnClickListener(this);
        updateRecBtn = (Button) this.findViewById(R.id.updateRec_btn);
        updateRecBtn.setOnClickListener(this);
        deleteRecBtn = (Button) this.findViewById(R.id.deleteRec_btn);
        deleteRecBtn.setOnClickListener(this);
        updateIng = (Button) this.findViewById(R.id.updateIng_btn);
        updateIng.setOnClickListener(this);
        addIng = (Button) this.findViewById(R.id.addIng_btn);
        addIng.setOnClickListener(this);
        delIng = (Button) this.findViewById(R.id.delIng_btn);
        delIng.setOnClickListener(this);
        homeBtn = (Button) this.findViewById(R.id.homePage_btn);
        homeBtn.setOnClickListener(this);
        listBtn = (Button) this.findViewById(R.id.listPage_btn);
        listBtn.setOnClickListener(this);
        eventBtn = (Button) this.findViewById(R.id.eventPage_btn);
        eventBtn.setOnClickListener(this);
        recView = (ScrollView) this.findViewById(R.id.rec_view);


        noOfServ = (EditText) this.findViewById(R.id.no_of_serv);
        qty = (EditText) this.findViewById(R.id.ingQty);

        recName = (AutoCompleteTextView) this.findViewById(R.id.recAutoComp);
        ingName = (AutoCompleteTextView) this.findViewById(R.id.ingAutoComp);
        unitSpinner = (Spinner) this.findViewById(R.id.unitId_spinner);


        Cursor cursor_one = DBOperator.getInstance().execQuery(
                SQLCommand.SELECT_RECIPE);
        while (cursor_one.moveToNext()) {
            recipeNameString.add(cursor_one.getString(0));
        }

        Cursor cursor_two = DBOperator.getInstance().execQuery(
                SQLCommand.SELECT_INGREDIENT);
        while (cursor_two.moveToNext()) {
            ingNameString.add(cursor_two.getString(0));
        }

        Cursor cursor_three = DBOperator.getInstance().execQuery(
                SQLCommand.SELECT_UNIT);
        while (cursor_three.moveToNext()) {
            unitNameString.add(cursor_three.getString(0));
        }
        initialize();

    }

    private void initialize() {
        autoTextView = (AutoCompleteTextView) this
                .findViewById(R.id.recAutoComp);


        // use ArrayAdpater to add content to AutoCompleteTextView
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, recipeNameString);
        autoTextView.setAdapter(arrayAdapter);

        autoTextView1 = (AutoCompleteTextView) this
                .findViewById(R.id.ingAutoComp);

        // use ArrayAdpater to add content to AutoCompleteTextView
        arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, ingNameString);
        autoTextView1.setAdapter(arrayAdapter1);


        spinner = (Spinner) this
                .findViewById(R.id.unitId_spinner);

        // set items list and textview style of items in ArrayAdapter
        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, unitNameString);
        // set the style of drop down views
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // set the ArrayAdapter into Spinner
        spinner.setAdapter(arrayAdapter2);

    }


    public void onClick(View v) {
        int id = v.getId();
        String[] argsRec = null;
        argsRec = new String[1];
        argsRec[0]=recName.getText().toString();

        String[] args = null;
        args = new String[1];

        String[] argsRecUpdate = null;
        argsRecUpdate = new String[2];

        String[] argsIng = null;
        argsIng = new String[1];
        argsIng[0]=ingName.getText().toString();

        String[] argsUnit = null;
        argsUnit = new String[1];
        argsUnit[0]=unitSpinner.getSelectedItem().toString();

        String[] argsIngUpdate = null;
        argsIngUpdate = new String[4];

        String[] argsIngDel = null;
        argsIngDel = new String[2];


        if (id == R.id.showIng_btn) {

            recView.removeAllViews();
            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_RECIPE_BY_NAME,argsRec);

            while (cursor.moveToNext()) {
                noOfServ.setText(cursor.getString(2), TextView.BufferType.EDITABLE);
                recID=cursor.getString(0);
            }
            args[0]=recID;
            Cursor cursor_one = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_RECIPE_INGREDIENT_DETAIL,args);
            recView.addView(new TableView(this.getBaseContext(),cursor_one));

        }

        else if (id == R.id.updateRec_btn) {
            argsRecUpdate[1]=recID;
            argsRecUpdate[0]=noOfServ.getText().toString();;
            DBOperator.getInstance().execSQL(SQLCommand.UPDATE_RECIPE_SERVINGS,argsRecUpdate);
            Toast.makeText(getBaseContext(), "Recipe Updated successfully", Toast.LENGTH_SHORT).show();
        }

        else if (id == R.id.deleteRec_btn) {
            System.out.println("recID----->>>>" + recID);
            args[0]=recID;
            DBOperator.getInstance().execSQL(SQLCommand.DELETE_RECIPE,args);
            Toast.makeText(getBaseContext(), "Recipe Deleted successfully", Toast.LENGTH_SHORT).show();
        }

        else if (id == R.id.updateIng_btn) {


            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_INGREDIENT_BY_NAME,argsIng);

            while (cursor.moveToNext()) {
                ingID=cursor.getString(0);
            }

            Cursor cursor_two = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_UNIT_BY_NAME,argsUnit);

            while (cursor_two.moveToNext()) {
                unitID=cursor_two.getString(0);
            }

            argsIngUpdate[0]=qty.getText().toString();
            argsIngUpdate[1]=unitID;
            argsIngUpdate[2]=recID;
            argsIngUpdate[3]=ingID;

            DBOperator.getInstance().execSQL(SQLCommand.UPDATE_REC_ING_QTY_UNIT,argsIngUpdate);
            Toast.makeText(getBaseContext(), "Recipe Ingredient Updated successfully", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.addIng_btn) {
            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_INGREDIENT_BY_NAME,argsIng);

            while (cursor.moveToNext()) {
                ingID=cursor.getString(0);
            }

            Cursor cursor_two = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_UNIT_BY_NAME,argsUnit);

            while (cursor_two.moveToNext()) {
                unitID=cursor_two.getString(0);
            }
            argsIngUpdate[0]=recID;
            argsIngUpdate[1]=ingID;
            argsIngUpdate[2]=qty.getText().toString();
            argsIngUpdate[3]=unitID;

            DBOperator.getInstance().execSQL(SQLCommand.ADD_ING_TO_REC,argsIngUpdate);
            Toast.makeText(getBaseContext(), "Recipe Ingredient Added successfully", Toast.LENGTH_SHORT).show();


        }
        else if (id == R.id.delIng_btn) {

            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_INGREDIENT_BY_NAME,argsIng);

            while (cursor.moveToNext()) {
                ingID=cursor.getString(0);
            }
            argsIngDel[0]=recID;
            argsIngDel[1]=ingID;

            DBOperator.getInstance().execSQL(SQLCommand.DEL_ING_FROM_RECIPE,argsIngDel);
            Toast.makeText(getBaseContext(), "Recipe Ingredient Deleted successfully", Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.homePage_btn) {
            Intent intent = new Intent(this, HomePageActivity.class);
            this.startActivity(intent);
        }
        else if (id == R.id.listPage_btn) {
            Intent intent = new Intent(this, ManageListActivity.class);
            this.startActivity(intent);
        }
        else if (id == R.id.eventPage_btn) {
            Intent intent = new Intent(this, EventActivity.class);
            this.startActivity(intent);
        }


    }

}
