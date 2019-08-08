package com.beerhouse.repository;

import com.beerhouse.model.Beer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BeerRepository extends PagingAndSortingRepository<Beer, Integer> {
    List<Beer> findByNameIgnoreCaseContaining(String name);

}
