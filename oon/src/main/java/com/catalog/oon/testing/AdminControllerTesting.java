package com.catalog.oon.testing;


import com.catalog.oon.utils.TablePrinter;
import com.catalog.oon.utils.TestsUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
@Order(5)
@RequiredArgsConstructor
public class AdminControllerTesting implements CommandLineRunner {

    private final RestTemplate restTemplate;
    @Override
    public void run(String... args) throws Exception {

        TestsUtils.printTestInfo("admin controller - get all");
        String res = restTemplate.getForObject("http://localhost:8080/admin/items",String.class);
        System.out.println(res);

        TestsUtils.printTestInfo("admin controller - get one");
         res = restTemplate.getForObject("http://localhost:8080/admin/items/{id}",String.class, "3");
        System.out.println(res);

        TestsUtils.printTestInfo("admin controller - get count before");
        res = restTemplate.getForObject("http://localhost:8080/admin/items/count",String.class);
        System.out.println(res);

//        TestsUtils.printTestInfo("admin controller - delete ");
//        restTemplate.delete("http://localhost:8080/admin/items/{id}",String.class, "3");
//       System.out.println(res);


        TestsUtils.printTestInfo("admin controller - add one");
        res = restTemplate.postForObject("http://localhost:8080/admin/items",
                "{\"name\":\"Item 20\",\"itemType\":\"SPORTS\",\"price\":199.90}",
                String.class);
        System.out.println(res);

    }
}
