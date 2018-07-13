package library.group5;

import android.os.Bundle;
import library.group5.util.DBOperator;
import library.group5.constant.SQLCommand;
import android.app.Activity;
import android.database.Cursor;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class group5Activity extends Activity implements OnClickListener {

    Button submitBtn;
    EditText emailIdEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landingpage);
        emailIdEdit = (EditText) this.findViewById(R.id.email_id);
        submitBtn = (Button) this.findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(this);
        try{
            DBOperator.copyDB(getBaseContext());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void onClick(View v) {
        int b=0;
        int id = v.getId();
        String emailValue=emailIdEdit.getText().toString();

        if (id == R.id.submit_btn) {
            Cursor cursor = DBOperator.getInstance().execQuery(
                    SQLCommand.SELECT_USER_EMAIL);
            while (cursor.moveToNext()) {
                String cursorValue = cursor.getString(0);
                if (cursorValue.equals(emailValue)) {
                    b=1;
                }

            }
            if (b == 1){
                Intent intent = new Intent(this, HomePageActivity.class);
                this.startActivity(intent);
            }
            else{
                Intent intent = new Intent(this, NewCustomerActivity.class);
                this.startActivity(intent);
            }

        }

    }


}
