package com.catalog.oon.services;

import com.catalog.oon.beans.Item;
import com.catalog.oon.beans.ItemType;
import com.catalog.oon.exceptions.InvalidEntityException;
import com.catalog.oon.exceptions.InvalidOperationException;
import com.catalog.oon.reposetory.ItemsRepos;
import com.catalog.oon.services.base.BaseItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectricityService implements BaseItem {
    private final ItemsRepos itemRepository;

    @Override
    public void saveItem(Item item) throws InvalidOperationException, InvalidEntityException {
        if (item.getItemType() != ItemType.ELECTRICITY)
            throw new InvalidOperationException("cannot add an item outside your domain");
        itemRepository.save(item) ;
    }

    @Override
    public void updateItem(Item item) throws InvalidEntityException, InvalidOperationException {
        if (item.getItemType() != ItemType.ELECTRICITY)
            throw new InvalidOperationException("cannot update an item outside your domain");
        itemRepository
                .findById(item.getId()).orElseThrow(()->
                new InvalidOperationException("Cannot update not existing id"));
        itemRepository.saveAndFlush(item) ;
    }

    @Override
    public long countItems() {
        return getAllItems().size();
    }

    @Override
    public Item getOneItem(Long id) throws InvalidEntityException, InvalidOperationException {
        Optional<Item> itemToExm = itemRepository.findById(id);

        Item itemToReturn = itemToExm.orElseThrow(() -> new InvalidEntityException("Item not found"));
        if (itemToReturn.getItemType() != ItemType.ELECTRICITY){
            throw new InvalidEntityException("cannot get an item outside your domain");
        }
        return itemToReturn;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> listToFilter = itemRepository.findAll();
        return listToFilter
                .stream()
                .filter(item -> item.getItemType() == ItemType.ELECTRICITY)
                .collect(Collectors.toList());
    }
}
