package com.mclientui.web.controller;


import com.mclientui.beans.ProductBean;
import com.mclientui.proxy.MicroserviceProduitsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    MicroserviceProduitsProxy mProduitsProxy;

    @RequestMapping("/")
    public String accueil(Model model){

        List<ProductBean> produits = mProduitsProxy.listeDesProduits();
        model.addAttribute("produits", produits);
        return "Accueil";
    }

    @RequestMapping("/details-produit/{id}")
    public String detailProduit(@PathVariable int id, Model model){
        ProductBean produit = mProduitsProxy.recupererUnProduit(id);
        model.addAttribute("produit", produit);

        return "FicheProduit";

    }
}
