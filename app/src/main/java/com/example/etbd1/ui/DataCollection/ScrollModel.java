package com.example.etbd1.ui.DataCollection;

import androidx.recyclerview.widget.RecyclerView;

public class ScrollModel {
    String scrollPosition;//RecyclerView
    String scrollItemName;//Recycler Namee
    String scrollDirection;
    int count;

    public String getScrollPosition() {
        return scrollPosition;
    }

    public void setScrollPosition(String scrollPosition) {
        this.scrollPosition = scrollPosition;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getScrollAt() {
        return scrollPosition;
    }

    public void setScrollAt(String scrollAt) {
        this.scrollPosition = scrollAt;
    }

    public String getScrollItemName() {
        return scrollItemName;
    }

    public void setScrollItemName(String scrollItemName) {
        this.scrollItemName = scrollItemName;
    }

    public String getScrollDirection() {
        return scrollDirection;
    }

    public void setScrollDirection(String scrollDirection) {
        this.scrollDirection = scrollDirection;
    }
}
