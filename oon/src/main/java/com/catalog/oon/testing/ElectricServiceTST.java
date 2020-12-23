package com.catalog.oon.testing;

import com.catalog.oon.beans.Item;
import com.catalog.oon.beans.ItemType;
import com.catalog.oon.bootstrap.Factory;
import com.catalog.oon.reposetory.ItemsRepos;
import com.catalog.oon.services.ElectricityService;
import com.catalog.oon.services.SportsService;
import com.catalog.oon.utils.AppArtUtils;
import com.catalog.oon.utils.TablePrinter;
import com.catalog.oon.utils.TestsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(4)
public class ElectricServiceTST implements CommandLineRunner {
    private final ElectricityService electricService;
    private final ItemsRepos itemRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(AppArtUtils.ROUND_4_SERVICE_TESTING);

        TestsUtils.printTestInfo("electricity service - get all sport items");
        TablePrinter.print(electricService.getAllItems());
        Thread.sleep(500);

        TestsUtils.printTestInfo("electricity service - cannot add item outside of domain");
        try {
            Item toAdd = Factory.creating();
            toAdd.setItemType(ItemType.FOOD);
            electricService.saveItem(toAdd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestsUtils.printTestInfo("electricity service - add sport item");
        Item toAdd = Factory.creating();
        toAdd.setItemType(ItemType.ELECTRICITY);
        System.out.println("Before :" + electricService.countItems());
        electricService.saveItem(toAdd);
        System.out.println("After :" + electricService.countItems());
        TablePrinter.print(electricService.getAllItems());
        Thread.sleep(500);


        TestsUtils.printTestInfo("electricity service - count all sport items");
        TablePrinter.print(electricService.getAllItems());
        System.out.println(electricService.countItems());
        Thread.sleep(500);

        TestsUtils.printTestInfo("electricity service - cannot get item by id not exist");
        try {
            System.out.println(electricService.getOneItem(101L));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

    }
}
