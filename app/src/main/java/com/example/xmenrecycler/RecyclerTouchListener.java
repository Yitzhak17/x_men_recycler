package com.example.xmenrecycler;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// Custom RecyclerView touch listener to handle click and long-click events
public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private final ClickListener clickListener; // Interface to communicate click events
    private final GestureDetector gestureDetector; // Gesture detector to detect touch events

    // Constructor to initialize the touch listener with the context, RecyclerView, and click listener
    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;

        // Creating a GestureDetector to detect gestures on the RecyclerView
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            // Override method to handle single tap up event

            @Override
            public boolean onSingleTapUp(@NonNull MotionEvent e) {
                return true; // Return true to indicate that the single tap up event is handled
            }

            // Override method to handle long press event
            @Override
            public void onLongPress(@NonNull MotionEvent e) {
                // Find the child view under the long press position
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                // If a child view is found and the click listener is not null, trigger the onLongClick event
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }

    // Override method to intercept touch events on the RecyclerView
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        // Find the child view under the touch position
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        // If a child view is found and the click listener is not null, and a gesture is detected, trigger the onClick event
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildAdapterPosition(child));
        }
        return false; // Return false to indicate that the touch event interception is not needed
    }

    // Override method to handle touch events on the RecyclerView
    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
    }

    // Override method to handle request to disallow intercept touch event
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    // Interface to communicate click events
    public interface ClickListener {
        void onClick(View view, int position); // Method to handle click event
        void onLongClick(View view, int position); // Method to handle long-click event
    }
}
