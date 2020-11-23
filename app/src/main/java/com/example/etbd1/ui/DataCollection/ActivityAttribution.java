package com.example.etbd1.ui.DataCollection;

import com.google.android.gms.analytics.ecommerce.Product;

import java.util.Date;

public class ActivityAttribution {

    private String action;
    private String actionType;
    private int count;
    private Date dateAndTime;
    //private Product product;
    //private String

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        if (action == "open"){

            this.action = action;
        }
        else if (action == "close"){

            this.action = action;
        }
        else if (action == "click"){

            this.action = action;
        }
        else if (action == "view"){

            this.action = action;
        }
    }

    public String getActionType(String action) {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

   /* public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }*/
}
