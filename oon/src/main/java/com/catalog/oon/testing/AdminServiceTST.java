package com.catalog.oon.testing;

import com.catalog.oon.beans.Item;
import com.catalog.oon.bootstrap.Factory;
import com.catalog.oon.services.AdminService;
import com.catalog.oon.utils.AppArtUtils;
import com.catalog.oon.utils.TablePrinter;
import com.catalog.oon.utils.TestsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Order(2)
public class AdminServiceTST implements CommandLineRunner {
    private final AdminService adminService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(AppArtUtils.ROUND_2_SERVICE_TESTING);

        TestsUtils.printTestInfo("admin service - get all items");
        TablePrinter.print(adminService.getAllItems());

        TestsUtils.printTestInfo("admin service - add item");
        Item toAdd = Factory.creating();
        adminService.saveItem(toAdd);
        TablePrinter.print(adminService.getAllItems());

        TestsUtils.printTestInfo("admin service - cannot get one item by not existing id");
        try{

            System.out.println("trying to get an item with id 101 LOL");
            adminService.getOneItem(101L);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        TestsUtils.printTestInfo("admin service - get one item by id");
        TablePrinter.print(adminService.getOneItem(1L));

        TestsUtils.printTestInfo("admin service - cannot update no existing id");
        Item toUpdate = adminService.getOneItem(1L);
        System.out.println(toUpdate);
        try {
            toUpdate.setId(101L);
            System.out.println(toUpdate);
            adminService.updateItem(toUpdate);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        Thread.sleep(500);




        TestsUtils.printTestInfo("admin service - update an item");
        toUpdate = adminService.getOneItem(1L);
        System.out.println(toUpdate);
        toUpdate.setPrice(BigDecimal.valueOf(199.90));
        System.out.println(toUpdate);
        adminService.updateItem(toUpdate);
        TablePrinter.print(adminService.getAllItems());

        TestsUtils.printTestInfo("admin service - delete an item");
        Item toDelete = adminService.getOneItem(11L);
        System.out.println(toDelete);
        adminService.deleteItem(toDelete.getId());
        TablePrinter.print(adminService.getAllItems());

        TestsUtils.printTestInfo("admin service - count all items");
        System.out.println(adminService.countItems());



    }

}
