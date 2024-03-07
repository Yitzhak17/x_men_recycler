package com.example.xmenrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private ArrayList<DataModel> dataSet;

    // Constructor to initialize the adapter with the dataset
    public CustomAdapter(ArrayList<DataModel> dataSet){
        this.dataSet = dataSet;
    }

    // ViewHolder class to hold the views of each item in the RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textViewName; // TextView to display the name of the X-Men character
        TextView textViewDescription; // TextView to display the description of the X-Men character
        ImageView imageView; // ImageView to display the image of the X-Men character

        // Constructor to initialize the ViewHolder with the item view
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tvName); // Finding and referencing the TextView for the name
            textViewDescription = itemView.findViewById(R.id.tvDescription); // Finding and referencing the TextView for the description
            imageView = itemView.findViewById(R.id.imageXMen); // Finding and referencing the ImageView for the image
        }
    }

    // Method called when ViewHolder needs to be created
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrow,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view); // Create a new ViewHolder with the inflated view
        return myViewHolder ; // Return the ViewHolder
    }

    // Method called to bind the data to the views of each item in the RecyclerView
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Set the text of TextViews and image resource of ImageView with data from the dataset
        holder.textViewName.setText(dataSet.get(position).getName());
        holder.textViewDescription.setText(dataSet.get(position).getDescription());
        holder.imageView.setImageResource(dataSet.get(position).getImage());
    }

    // Method to get the total number of items in the dataset
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    // Method to filter the dataset based on a filtered list
    public void filterList(ArrayList<DataModel> filterList){
        dataSet = filterList; // Assign the filtered list to the dataset
        notifyDataSetChanged(); // Notify the adapter that the dataset has changed
    }
}
