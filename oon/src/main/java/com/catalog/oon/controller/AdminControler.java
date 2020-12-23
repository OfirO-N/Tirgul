package com.catalog.oon.controller;

import com.catalog.oon.beans.Item;
import com.catalog.oon.exceptions.InvalidEntityException;
import com.catalog.oon.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminControler {
    private final AdminService adminService;

    @SneakyThrows
    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody @Valid Item item) {
        adminService.saveItem(item);
    }
    @SneakyThrows
    @PutMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@PathVariable Long id,@RequestBody @Valid Item item)  {
        adminService.updateItem(item);
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteItem(@PathVariable Long id , @RequestBody @Valid Item item)
            throws InvalidEntityException {
        adminService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/items",produces={"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getAllItems(){
        return ResponseEntity.ok(adminService.getAllItems());
    }

    @SneakyThrows
    @GetMapping("/items/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getOneItemById(@PathVariable Long id) {
        Item item = adminService.getOneItem(id);

        return ResponseEntity.ok(item);
    }

    @GetMapping("/items/count")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> countItems(){
        return ResponseEntity.ok(adminService.countItems());
    }

}
