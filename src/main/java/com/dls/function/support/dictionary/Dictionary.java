package com.dls.function.support.dictionary;

import com.dls.arc.Entry;

public class Dictionary extends Entry {
    private int id;
    private int groupKey;
    private String groupValue;
    private int itemKey;
    private String itemValue;
    private int status;
    private int sort;

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupKey(int groupKey) {
        this.groupKey = groupKey;
    }

    public void setGroupValue(String groupValue) {
        this.groupValue = groupValue;
    }

    public void setItemKey(int itemKey) {
        this.itemKey = itemKey;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getId() {
        return id;
    }

    public int getGroupKey() {
        return groupKey;
    }

    public String getGroupValue() {
        return groupValue;
    }

    public int getItemKey() {
        return itemKey;
    }

    public String getItemValue() {
        return itemValue;
    }

    public int getStatus() {
        return status;
    }

    public int getSort() {
        return sort;
    }
}
