package com.kagboton.microcomerce.dao;

import com.kagboton.microcomerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

    Product findById(int id);

    List<Product> findByPrixGreaterThan(int prixLimit);

    @Query("select id, nom, prix from Product  p where p.prix > :prixLimit")
    List<Product> chercherUnProduitCher(@Param("prixLimit") int prix);

    List<Product> findByNomLike(String s);
}
