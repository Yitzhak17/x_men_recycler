package com.example.xmenrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

// MainActivity class responsible for managing the main activity layout
public class MainActivity extends AppCompatActivity {
    private ArrayList<DataModel> dataSet; // ArrayList to hold the data models
    private CustomAdapter adapter; // CustomAdapter for RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing RecyclerView, LayoutManager, and SearchView
        // RecyclerView to display the data
        RecyclerView recyclerView = findViewById(R.id.resView);
        // LayoutManager for RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // SearchView for filtering data
        SearchView searchView = findViewById(R.id.search_view);
        recyclerView.setLayoutManager(layoutManager);
        dataSet = new ArrayList<>(); // Initialize the ArrayList to store data

        // Setting item animator for RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Populating dataSet with data from myData class
        for (int i = 0; i < myData.nameArray.length; i++) {
            dataSet.add(new DataModel(
                    myData.nameArray[i],
                    myData.desArray[i],
                    myData.drawleArray[i],
                    myData.id[i]
            ));
        }

        // Initializing and setting CustomAdapter for RecyclerView
        adapter = new CustomAdapter(dataSet);
        recyclerView.setAdapter(adapter);

        // Adding onTouchListener to RecyclerView to handle click events
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Handling click event on RecyclerView item
                DataModel dataModel = dataSet.get(position);
                Toast.makeText(getApplicationContext(), dataModel.getName() , Toast.LENGTH_SHORT).show(); // Displaying a toast with the name of the selected item
            }

            @Override
            public void onLongClick(View view, int position) {
                // Handling long-click event on RecyclerView item if needed
            }
        }));

        // Setting up a listener for SearchView to filter data as the user types
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtering the data based on the user input
                filter(newText);
                return true;
            }
        });
    }

    // Method to filter data based on the search query
    private void filter(String newText) {
        ArrayList<DataModel> filteredList = new ArrayList<>();
        for (DataModel item : dataSet) {
            // Checking if the name of the item contains the search query
            if (item.getName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item); // Adding matching items to the filtered list
            }
        }
        adapter.filterList(filteredList); // Updating the RecyclerView with filtered data
    }
}
