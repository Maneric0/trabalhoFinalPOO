package model;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Manerico
 */
public class Main {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        GerenciadorDados gerenciador = new GerenciadorDados(new File("C:\\Users\\Manerico\\Downloads\\teste.csv"));

        //gerenciador.inserirLancamento(new Receita(1000, LocalDate.of(2024, 1, 10), CategoriaReceita.SALARIO));
        //gerenciador.inserirLancamento(new Despesa(200, LocalDate.of(2024, 2, 15), CategoriaDespesa.ALIMENTACAO));
        //gerenciador.inserirLancamento(new Despesa(150, LocalDate.of(2024, 3, 02), CategoriaDespesa.ENTRETENIMENTO));
//        gerenciador.inserirLancamento(new Receita(100, LocalDate.of(2024, 4, 15), CategoriaReceita.DECIMO_TERCEIRO));
//        gerenciador.inserirLancamento(new Receita(100, LocalDate.of(2024, 5, 15), CategoriaReceita.FERIAS));
        
        gerenciador.ordenarLancamentosPorData();
        
        System.out.println("Lançamentos:");
        for (Lancamento lancamento : gerenciador.getLancamentos()) {
            String categoria = lancamento instanceof Receita
                    ? ((Receita) lancamento).getCategoria().toString()
                    : ((Despesa) lancamento).getCategoria().toString();

            System.out.println("Data: " + lancamento.getData().format(formatter) +
                    ", Valor: " + lancamento.getValor() +
                    ", Categoria: " + categoria +
                    ", Saldo: " + lancamento.getSaldo());
        }
    }
}
