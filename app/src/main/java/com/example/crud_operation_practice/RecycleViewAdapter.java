package com.example.crud_operation_practice;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{
    private static final String TAG = "RecycleViewAdapter";

    private ArrayList<String> headings = new ArrayList<>();
    private ArrayList<String> descriptions = new ArrayList<>();
    private Context context;

    public RecycleViewAdapter(ArrayList<String> headings, ArrayList<String> descriptions, Context context) {
        this.headings = headings;
        this.descriptions = descriptions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_resource_file, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "OnBindViewHolder: called");
        //Setting the Heading to the layout.
        holder.heading.setText(headings.get(position));
        holder.description.setText(descriptions.get(position));

        //Onclick method
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Item Clicked " + headings.get(position), Toast.LENGTH_SHORT).show();
                //Creating an Intent and pass the values to the viewing activity.
                Intent inten = new Intent(context, EditUpdateSubjects.class);
                inten.putExtra("subject", headings.get(position));
                inten.putExtra("description", descriptions.get(position));
                context.startActivity(inten);
            }
        });
    }

    @Override
    public int getItemCount() {
        //Amount of items in the arraylist if set to 0 nothing will be shown.
        return headings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView heading;
        TextView description;
        RelativeLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            description = itemView.findViewById(R.id.description);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
