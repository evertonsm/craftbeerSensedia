package com.beerhouse;

import com.beerhouse.model.Beer;
import com.beerhouse.repository.BeerRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class beerRepositoryTest {

    @Autowired
    private BeerRepository beerRepository;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistData() {
        Beer beer = new Beer("brahma",
                "Água, malte (de cevada), cereais não maltados, carboidratos e lúpulo",
                "5,0%", 2.39, "pilsen");

        this.beerRepository.save(beer);

        beer = this.beerRepository.findOne(beer.getId());
        assertThat(beer.getId()).isNotNull();
        assertThat(beer.getName()).isEqualTo("brahma");
        assertThat(beer.getIngredients()).isEqualTo("Água, malte (de cevada), cereais não maltados, carboidratos e lúpulo");
        assertThat(beer.getAlcoholContent()).isEqualTo("5,0%");
        assertThat(beer.getPrice()).isEqualTo(2.39);
        assertThat(beer.getCategory()).isEqualTo("pilsen");

    }

    @Test
    public void deleteShouldRemoveData() {
        Beer beer = new Beer("brahma",
                "Água, malte (de cevada), cereais não maltados, carboidratos e lúpulo",
                "5,0%", 2.39, "pilsen");

        this.beerRepository.save(beer);
        beerRepository.delete(beer.getId());
        assertThat(beerRepository.findOne(beer.getId())).isNull();
    }

    @Test
    public void updateShouldChangeAndPersistData() {
        Beer beer = new Beer("brahma",
                "Água, malte (de cevada), cereais não maltados, carboidratos e lúpulo",
                "5,0%", 2.39, "pilsen");

        this.beerRepository.save(beer);

        beer.setName("Brahma Extra Lager");
        beer.setIngredients("Água, malte (de cevada) e lúpulo");
        beer.setAlcoholContent("5,5%");
        beer.setPrice(2.99);
        beer.setCategory("Lager");

        this.beerRepository.save(beer);

        beer = this.beerRepository.findOne(beer.getId());
        assertThat(beer.getId()).isNotNull();
        assertThat(beer.getName()).isEqualTo("Brahma Extra Lager");
        assertThat(beer.getIngredients()).isEqualTo("Água, malte (de cevada) e lúpulo");
        assertThat(beer.getAlcoholContent()).isEqualTo("5,5%");
        assertThat(beer.getPrice()).isEqualTo(2.99);
        assertThat(beer.getCategory()).isEqualTo("Lager");
    }

    @Test
    public void createWhenNameIsNullShouldThrowConstraintException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Field name may not be empty");
        this.beerRepository.save(new Beer());
    }

    @Test
    public void createWhenIngredientsIsNullShouldThrowConstraintException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Field ingredients may not be empty");
        this.beerRepository.save(new Beer());
    }

    @Test
    public void createWhenAlcoholContentIsNullShouldThrowConstraintException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Field alcoholContent may not be empty");
        this.beerRepository.save(new Beer());
    }

    @Test
    public void createWhenPriceIsInvalidShouldThrowConstraintException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Price Must be equal or greater than 0");

        Beer beer = new Beer();

        beer.setName("Brahma Extra Lager");
        beer.setIngredients("Água, malte (de cevada) e lúpulo");
        beer.setAlcoholContent("5,5%");
        beer.setPrice(-10);
        beer.setCategory("Lager");

        this.beerRepository.save(beer);
    }

    @Test
    public void createWhenCategoryIsNullShouldThrowConstraintException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Field category may not be empty");
        this.beerRepository.save(new Beer());
    }
}
