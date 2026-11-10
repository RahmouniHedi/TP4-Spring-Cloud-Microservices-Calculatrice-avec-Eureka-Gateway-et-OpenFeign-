package com.exemple.historique.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Operation {
    private int id;
    private String operateur; // somme, soustraction, produit ou division
    private int a;
    private int b;
    private double resultat;
}