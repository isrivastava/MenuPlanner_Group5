package library.group5;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;

/**
 * Created by Isha on 06-04-2018.
 */

public class HomePageActivity extends Activity implements OnClickListener{

    Button recipeBtn,eventBtn,listBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        recipeBtn = (Button) this.findViewById(R.id.recipe_btn);
        recipeBtn.setOnClickListener(this);
        eventBtn = (Button) this.findViewById(R.id.event_btn);
        eventBtn.setOnClickListener(this);
        listBtn = (Button) this.findViewById(R.id.list_btn);
        listBtn.setOnClickListener(this);
    }
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.recipe_btn) {
            Intent intent = new Intent(this, RecipeActivity.class);
            this.startActivity(intent);
        }
        else if(id == R.id.event_btn){
            Intent intent = new Intent(this, EventActivity.class);
            this.startActivity(intent);

        }
        else if(id == R.id.list_btn){
            Intent intent = new Intent(this, ManageListActivity.class);
            this.startActivity(intent);

        }

    }

}
