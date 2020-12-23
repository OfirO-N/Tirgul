package com.catalog.oon.bootstrap;

import com.catalog.oon.beans.Item;
import com.catalog.oon.reposetory.ItemsRepos;
import com.catalog.oon.utils.TablePrinter;
import com.catalog.oon.utils.TestsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
@Order(1)
public class InitData implements CommandLineRunner {
    private final ItemsRepos itemRepository;

    @Override
    public void run(String... args) throws Exception {

        TestsUtils.printTestInfo("bootstrap testing");
        itemRepository.saveAll(Factory.generateAll());
        TablePrinter.print(itemRepository.findAll());




    }
}
