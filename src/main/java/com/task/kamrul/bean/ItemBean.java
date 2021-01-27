
package com.task.kamrul.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kamrul Hasan
 */
public class ItemBean implements Serializable{
    
     private int itemId;
     private String itemName;
     private Date itemDate;
     private String itemDesc;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getItemDate() {
        return itemDate;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
     
     
    
}
