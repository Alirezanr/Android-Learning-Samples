package com.example.composeapplication.design_patterns.singleton;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCardJava {
    private static ShoppingCardJava INSTANCE;
    private final ArrayList<String> items = new ArrayList();

    private ShoppingCardJava() {
    }

    public static ShoppingCardJava getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ShoppingCardJava();

        return INSTANCE;
    }

    public void addItem(String item) {
        items.add(item);
    }

    public List<String> getItems() {
        return items;
    }
}
