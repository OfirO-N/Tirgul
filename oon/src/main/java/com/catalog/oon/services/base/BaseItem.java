package com.catalog.oon.services.base;

import com.catalog.oon.beans.Item;
import com.catalog.oon.exceptions.InvalidEntityException;
import com.catalog.oon.exceptions.InvalidOperationException;

import java.util.List;

public interface BaseItem {
    void saveItem(Item item) throws InvalidOperationException, InvalidEntityException;
    void updateItem(Item item) throws InvalidEntityException, InvalidOperationException;
    long countItems();
    Item getOneItem(Long id) throws InvalidEntityException, InvalidOperationException;
    List<Item> getAllItems();

    //delete will handle bu admin only
}
