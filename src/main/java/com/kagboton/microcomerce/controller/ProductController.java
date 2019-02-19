package com.kagboton.microcomerce.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kagboton.microcomerce.dao.ProductDao;
import com.kagboton.microcomerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    //produits
    /*@GetMapping(value="produits")
    public List<Product> listeProduit(){
        return productDao.findAll();
    }*/

    //Récupérer la liste des produits
    @RequestMapping(value = "/produits", method = RequestMethod.GET)
    public MappingJacksonValue listeProduits() {

        List<Product> produits = productDao.findAll();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat"); //on peut a l'inverse utiliser filterOutAllExcept  pour selectionner les champs à afficher

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);

        produitsFiltres.setFilters(listDeNosFiltres);

        return produitsFiltres;
    }

    //produits/{id}
    @GetMapping(value = "produits/{id}")
    public Product afficherUnProduit(
            @PathVariable int id
    ){
        return productDao.findByID(id);
    }

    //produit
    @PostMapping(value = "produit")
    public ResponseEntity<Void> ajouterProduit(
            @RequestBody Product product
    ){
        Product product1 = productDao.save(product);
        if (product1 == null){
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product1.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
