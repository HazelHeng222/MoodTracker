package sg.edu.ep.c346.id20029318.moodtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {
    EditText edID, edName, edDes, edMood, edDate;
    Button btnCancel, btnUpdate, btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mem);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        edID = (EditText) findViewById(R.id.edID);
        edName = (EditText) findViewById(R.id.edName);
        edDes = (EditText) findViewById(R.id.edDes);
        edMood = (EditText) findViewById(R.id.edMood);
        edDate = (EditText) findViewById(R.id.edDate);

        Intent i = getIntent();
        final Mood currentMood = (Mood) i.getSerializableExtra("mood");

        edID.setText(currentMood.getId()+"");
        edName.setText(currentMood.getName());
        edDes.setText(currentMood.getDescription());
        edMood.setText(currentMood.getMood());
        edDate.setText(currentMood.getDate()+"");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                currentMood.setName(edName.getText().toString().trim());
                currentMood.setDescription(edDes.getText().toString().trim());
                currentMood.setMood(edMood.getText().toString().trim());
                currentMood.setDate(edDate.getText().toString().trim());

                int result = dbh.updateMood(currentMood);
                if (result>0){
                    Toast.makeText(EditActivity.this, "Mood updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(EditActivity.this);
                alertBuilder.setTitle("Danger");
                alertBuilder.setMessage("Are you sure you want to discard the changes\n" + currentMood.getName());
                alertBuilder.setCancelable(false);
                alertBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                alertBuilder.setPositiveButton("Do NOT Discard", null);
                AlertDialog alertDialog = alertBuilder.create();
                alertBuilder.show();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(EditActivity.this);
                alertBuilder.setTitle("Danger");
                alertBuilder.setMessage("Are you sure you want to delete Entry\n" + currentMood.getName());
                alertBuilder.setCancelable(false);
                alertBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditActivity.this);
                        int result = dbh.deleteMood(currentMood.getId());
                        if (result>0){
                            Toast.makeText(EditActivity.this, "Entry deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(EditActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertBuilder.setPositiveButton("Cancel", null);
                AlertDialog alertDialog = alertBuilder.create();
                alertBuilder.show();

            }
        });



    }


}

