package com.catalog.oon.services;

import com.catalog.oon.beans.Item;
import com.catalog.oon.exceptions.InvalidEntityException;
import com.catalog.oon.exceptions.InvalidOperationException;
import com.catalog.oon.reposetory.ItemsRepos;
import com.catalog.oon.services.base.BaseItem;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService implements BaseItem {
    private final ItemsRepos itemRepository;

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item) ;
    }

    @Override
    @SneakyThrows
    public void updateItem(Item item) {
        getOneItem(item.getId());
        itemRepository.saveAndFlush(item);
    }

    @Override
    public long countItems() {
        return getAllItems().size();
    }

    @Override
    @SneakyThrows
    public Item getOneItem(Long id)  {
        Optional<Item> itemToReturn = itemRepository.findById(id);
        return itemToReturn.orElseThrow(() -> new InvalidEntityException("Item not found"));
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void deleteItem (Long id) throws InvalidEntityException {
        getOneItem(id);
        itemRepository.deleteById(id);
    }

}
