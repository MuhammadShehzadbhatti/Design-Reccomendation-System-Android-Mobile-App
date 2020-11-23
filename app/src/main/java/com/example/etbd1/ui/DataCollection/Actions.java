package com.example.etbd1.ui.DataCollection;

import java.util.ArrayList;

public class Actions {
    String actionName;
    String actionPlace;
    ArrayList<ScrollModel> actionType = new ArrayList<>();

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionPlace() {
        return actionPlace;
    }

    public void setActionPlace(String actionPlace) {
        this.actionPlace = actionPlace;
    }

    public ArrayList<ScrollModel> getActionType() {
        return actionType;
    }

    public void setActionType(ArrayList<ScrollModel> actionType) {
        this.actionType = actionType;
    }
}
