package com.kagboton.microcomerce.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kagboton.microcomerce.dao.ProductDao;
import com.kagboton.microcomerce.exceptions.ProduitIntrouvableException;
import com.kagboton.microcomerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    //produits
    @GetMapping(value="produits")
    public List<Product> listeProduit(){
        return productDao.findAll();
    }

    //Récupérer la liste des produits
    /*@RequestMapping(value = "/produits", method = RequestMethod.GET)
    public MappingJacksonValue listeProduits() {

        List<Product> produits = productDao.findAll();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat"); //on peut a l'inverse utiliser filterOutAllExcept  pour selectionner les champs à afficher

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);

        produitsFiltres.setFilters(listDeNosFiltres);

        return produitsFiltres;
    }*/

    //produits/{id}
    @GetMapping(value = "produits/{id}")
    public Product afficherUnProduit(
           @PathVariable int id
    )throws ProduitIntrouvableException{
        Product product =  productDao.findById(id);

        if(product == null) {
            throw new ProduitIntrouvableException("Le produit avec l'id " +id+ " est introuvable");
        }
        return product;
    }

    //produit
    @PostMapping(value = "produit")
    public ResponseEntity<Void> ajouterProduit(
            @Valid @RequestBody Product product
    ) {
        Product product1 = productDao.save(product);
        if (product1 == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product1.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /*@GetMapping(value = "test/produits/{prixLimit}")
    public List<Product> testDeRequete(@PathVariable int prixLimit){
        return productDao.findByPrixGreaterThan(400);
    }*/

    @GetMapping(value = "test/produits/{prixLimit}")
    public List<Product> testDeRequeteBis(@PathVariable int prixLimit){
        return productDao.chercherUnProduitCher(prixLimit);
    }


    @GetMapping(value = "testMot/produits/{recherche}")
    public List<Product> testeDeRequeteBisBis(@PathVariable String recherche) {
        return productDao.findByNomLike("%"+recherche+"%");
    }

    @DeleteMapping(value = "/produits/{id}")
    public void supprimerProduit(@PathVariable int id) {
        productDao.delete(productDao.findById(id));
    }

    @PutMapping (value = "/produits")
    public void updateProduit(@RequestBody Product product) {
        productDao.save(product);
    }


}
