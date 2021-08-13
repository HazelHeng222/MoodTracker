package sg.edu.ep.c346.id20029318.moodtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edName, edDes, edMood, edDate;
    Button butAdd, butShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_main));

        edName = findViewById(R.id.edName);
        edDes = findViewById(R.id.edDes);
        edMood = findViewById(R.id.edMood);
        edDate = findViewById(R.id.edDate);
        butAdd = findViewById(R.id.butAddMem);
        butShow = findViewById(R.id.butShowMem);

        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edName.getText().toString().trim();
                String description = edDes.getText().toString().trim();
                String mood = edMood.getText().toString().trim();

                if (name.length() == 0 || description.length() == 0) {
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String date = edDate.getText().toString().trim();
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.addMood(name, description, mood, date);
                dbh.close();

                Toast.makeText(MainActivity.this, "Mood inserted", Toast.LENGTH_LONG).show();
                edName.setText("");
                edDes.setText("");
                edMood.setText("");
                edDate.setText("");


            }
        });

        butShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(i);
            }
        });

    }


}