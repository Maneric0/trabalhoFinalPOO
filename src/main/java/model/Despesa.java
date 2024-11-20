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
public class Despesa extends Lancamento {
    private CategoriaDespesa categoria;
    
    public Despesa(double valor, LocalDate data, CategoriaDespesa categoria) {
        super(valor, data);
        setCategoria(categoria);
    }

    @Override
    public double calcularSaldo() {
        return -this.getValor();
    }   

    /**
     * @return the categoria
     */
    public CategoriaDespesa getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(CategoriaDespesa categoria) {
        this.categoria = categoria;
    }
    
    
}
