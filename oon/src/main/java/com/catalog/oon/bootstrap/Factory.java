package com.catalog.oon.bootstrap;

import com.catalog.oon.beans.Item;
import com.catalog.oon.beans.ItemType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Factory {
    private static int COUNT = 1;

    public static Item creating(){


        return Item.builder().itemType(randType())
                .price(randPrice())
                .name(String.format("Item %d", COUNT++))
                .build();
    }

    private static BigDecimal randPrice() {
        double price = Math.random() * 101;
        return BigDecimal.valueOf(price);
    }

    private static ItemType randType(){
        int val = (int) (Math.random() * ItemType.values().length);
        return ItemType.values()[val];
    }

    public static List<Item> generateAll(){
        return Arrays.asList(creating(),creating(),creating(),creating(),creating(),
                creating(),creating(),creating(),creating(),creating());
    }
}
