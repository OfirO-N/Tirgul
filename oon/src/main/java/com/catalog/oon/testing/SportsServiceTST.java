package com.catalog.oon.testing;

import com.catalog.oon.beans.Item;
import com.catalog.oon.beans.ItemType;
import com.catalog.oon.bootstrap.Factory;
import com.catalog.oon.reposetory.ItemsRepos;
import com.catalog.oon.services.AdminService;
import com.catalog.oon.services.SportsService;
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
@Order(3)
public class SportsServiceTST implements CommandLineRunner {
    private final SportsService sportService;
    private final ItemsRepos itemRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(AppArtUtils.ROUND_3_SERVICE_TESTING);

        TestsUtils.printTestInfo("sport service - get all sport items");
        TablePrinter.print(sportService.getAllItems());
        Thread.sleep(500);

        TestsUtils.printTestInfo("sport service - cannot add item outside of domain");
        try {
            Item toAdd = Factory.creating();
            toAdd.setItemType(ItemType.FOOD);
            sportService.saveItem(toAdd);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

        TestsUtils.printTestInfo("sport service - add sport item");
        Item toAdd = Factory.creating();
        toAdd.setItemType(ItemType.SPORTS);
        System.out.println("Before :" + sportService.countItems());
        sportService.saveItem(toAdd);
        System.out.println("After :" + sportService.countItems());
        TablePrinter.print(sportService.getAllItems());
        Thread.sleep(500);

        TestsUtils.printTestInfo("sport service - count all sport items");
        TablePrinter.print(sportService.getAllItems());
        System.out.println(sportService.countItems());
        Thread.sleep(500);

        TestsUtils.printTestInfo("sport service - cannot get item by id not exist");
        try {
            System.out.println(sportService.getOneItem(101L));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Thread.sleep(500);

//        TestsUtils.printTestInfo("sport service - cannot get item by id when type is not belong to the domain");
//        Item toAddNow2 = Factory.creating();
//        toAddNow2.setItemType(ItemType.FOOD);
//        itemRepository.save(toAddNow2);
//        try {
//            System.out.println(sportService.getOneItem(itemRepository.findFirstByOrderByIdDesc().getId()));
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }
//        Thread.sleep(500);

//        TestsUtils.printTestInfo("sport service - get one");
////        Long id = itemRepository.findFirstByItemType(ItemType.SPORTS.name()).getId();
//        TablePrinter.print(sportService.getOneItem(id));
    }
}
