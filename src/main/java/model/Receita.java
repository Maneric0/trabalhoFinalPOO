/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Manerico
 */
public class Receita extends Lancamento {
    private CategoriaReceita categoria;
    
    public Receita(double valor, LocalDate data, CategoriaReceita categoria) {
        super(valor, data);
        setCategoria(categoria);
    }

    @Override
    public double calcularSaldo() {
        return this.getValor(); // Receita adiciona ao saldo
    }   

    /**
     * @return the categoria
     */
    public CategoriaReceita getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(CategoriaReceita categoria) {
        this.categoria = categoria;
    }
    
    
}
