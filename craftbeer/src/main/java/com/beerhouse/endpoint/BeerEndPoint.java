package com.beerhouse.endpoint;

import com.beerhouse.error.ResourceNotFoundException;
import com.beerhouse.model.Beer;
import com.beerhouse.repository.BeerRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("beers")
public class BeerEndPoint {

    private final BeerRepository beerDAO;

    public BeerEndPoint(BeerRepository beerDAO) {
        this.beerDAO = beerDAO;
    }

    @GetMapping
    public @ResponseBody
    List<Beer> listAll(Pageable pageable) {
        return beerDAO.findAll(pageable).getContent();
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> save(@Valid @RequestBody Beer beer) {
        beerDAO.save(beer);
        return new ResponseEntity<>("Object successfully saved", HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBeerById(@PathVariable Integer id) {
        verifyIfBeerExists(id);
        Beer beer = beerDAO.findOne(id);
        return new ResponseEntity<>(beer, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> update(@Valid @RequestBody Beer beer, @PathVariable Integer id) {

        verifyIfBeerExists(id);
        Beer upBeer = new Beer();

        upBeer.setId(id);
        upBeer.setName(beer.getName());
        upBeer.setIngredients(beer.getIngredients());
        upBeer.setAlcoholContent(beer.getAlcoholContent());
        upBeer.setPrice(beer.getPrice());
        upBeer.setCategory(beer.getCategory());
        beerDAO.save(upBeer);

        return new ResponseEntity<>(upBeer, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> partialUpdate(@RequestBody Beer beer, @PathVariable Integer id) {

        verifyIfBeerExists(id);
        Beer upBeer = new Beer();
        upBeer.setId(id);

        if (beer.getName() == null) {
            upBeer.setName(beerDAO.findOne(id).getName());
        } else {
            upBeer.setName(beer.getName());
        }

        if (beer.getIngredients() == null) {
            upBeer.setIngredients(beerDAO.findOne(id).getIngredients());
        } else {
            upBeer.setIngredients(beer.getIngredients());
        }

        if (beer.getAlcoholContent() == null) {
            upBeer.setAlcoholContent(beerDAO.findOne(id).getAlcoholContent());
        } else {
            upBeer.setAlcoholContent(beer.getAlcoholContent());
        }

        if (beer.getPrice() == null) {
            upBeer.setPrice(beerDAO.findOne(id).getPrice());
        } else {
            upBeer.setPrice(beer.getPrice());
        }

        if (beer.getCategory() == null) {
            upBeer.setCategory(beerDAO.findOne(id).getCategory());
        } else {
            upBeer.setCategory(beer.getCategory());
        }

        beerDAO.save(upBeer);
        return new ResponseEntity<>(upBeer, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        verifyIfBeerExists(id);
        beerDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void verifyIfBeerExists(Integer id) {
        if (beerDAO.findOne(id) == null) {
            throw new ResourceNotFoundException("Beer not found for ID: " + id);
        }
    }
}
