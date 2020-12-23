package com.catalog.oon.reposetory;

import com.catalog.oon.beans.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepos extends JpaRepository<Item, Long> {
}
