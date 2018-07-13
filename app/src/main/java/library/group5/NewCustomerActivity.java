package library.group5;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import library.group5.util.DBOperator;
import library.group5.constant.SQLCommand;

/**
 * Created by Isha on 06-04-2018.
 */

public class NewCustomerActivity extends Activity implements OnClickListener {

    Button registerBtn;
    EditText userNameEdit,passwordEdit,emailIdEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcustomer);
        emailIdEdit = (EditText) this.findViewById(R.id.user_email);
        userNameEdit = (EditText) this.findViewById(R.id.user_name);
        passwordEdit = (EditText) this.findViewById(R.id.user_pwd);
        registerBtn = (Button) this.findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(this);
    }


    public void onClick(View v) {
        int id = v.getId();
        String[] args = null;
        args = new String[3];

        args[0]=userNameEdit.getText().toString();
        args[1]=emailIdEdit.getText().toString();
        args[2]=passwordEdit.getText().toString();

        if (id == R.id.register_btn) {
            DBOperator.getInstance().execSQL(SQLCommand.INSERT_USER,args);
            Toast.makeText(getBaseContext(), "inserted", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, HomePageActivity.class);
            this.startActivity(intent);

        }

    }


}
