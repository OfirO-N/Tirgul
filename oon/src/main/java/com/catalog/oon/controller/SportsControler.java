package com.catalog.oon.controller;

import com.catalog.oon.beans.Item;
import com.catalog.oon.exceptions.InvalidEntityException;
import com.catalog.oon.services.AdminService;
import com.catalog.oon.services.SportsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sports")
@RequiredArgsConstructor
public class SportsControler {
    private final SportsService sportsService;

    @SneakyThrows
    @PostMapping("items")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody @Valid Item item) {
        sportsService.saveItem(item);
    }
    @SneakyThrows
    @PutMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@PathVariable Long id,@RequestBody @Valid Item item)  {
        sportsService.updateItem(item);
    }


    @GetMapping(value = "/items",produces={"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllItems(){
        return ResponseEntity.ok(sportsService.getAllItems());
    }

    @SneakyThrows
    @GetMapping("/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getOneItemById(@PathVariable Long id) {
        Item item = sportsService.getOneItem(id);

        return ResponseEntity.ok(item);
    }

    @GetMapping("/items/count")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> countItems(){
        return ResponseEntity.ok(sportsService.countItems());
    }

}
