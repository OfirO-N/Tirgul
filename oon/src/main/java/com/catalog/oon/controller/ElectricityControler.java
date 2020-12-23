package com.catalog.oon.controller;

import com.catalog.oon.beans.Item;
import com.catalog.oon.services.ElectricityService;
import com.catalog.oon.services.SportsService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/electricity")
@RequiredArgsConstructor
public class ElectricityControler {
    private final ElectricityService electricService;

    @SneakyThrows
    @PostMapping("items")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody @Valid Item item) {
        electricService.saveItem(item);
    }
    @SneakyThrows
    @PutMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@PathVariable Long id,@RequestBody @Valid Item item)  {
        electricService.updateItem(item);
    }


    @GetMapping(value = "/items",produces={"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllItems(){
        return ResponseEntity.ok(electricService.getAllItems());
    }

    @SneakyThrows
    @GetMapping("/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getOneItemById(@PathVariable Long id) {
        Item item = electricService.getOneItem(id);

        return ResponseEntity.ok(item);
    }

    @GetMapping("/items/count")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> countItems(){
        return ResponseEntity.ok(electricService.countItems());
    }

}
