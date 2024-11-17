/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Manerico
 */
import java.util.Comparator;

public class ComparadorPorData implements Comparator<Lancamento> {
    @Override
    public int compare(Lancamento l1, Lancamento l2) {
        return l1.getData().compareTo(l2.getData());
    }
}