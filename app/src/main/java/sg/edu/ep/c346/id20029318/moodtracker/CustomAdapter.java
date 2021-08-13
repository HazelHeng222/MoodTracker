package sg.edu.ep.c346.id20029318.moodtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Mood> moodList;

    public  CustomAdapter (Context context, int resource, ArrayList<Mood> objects) {

        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        moodList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView txtName = rowView.findViewById(R.id.txtName);
        TextView txtDescription = rowView.findViewById(R.id.txtDes);
        TextView txtMood = rowView.findViewById(R.id.txtMood);
        TextView txtDate = rowView.findViewById(R.id.txtDate);




        // Obtain the Android Version information based on the position
        Mood currIsland = moodList.get(position);


        // Set values to the TextView to display the corresponding information
        txtName.setText(currIsland.getName());
        txtDescription.setText(currIsland.getDescription());
        txtMood.setText(currIsland.getMood());
        txtDate.setText(currIsland.getDate() + "");

        return rowView;
    }

}


