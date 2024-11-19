package model;

import java.time.LocalDate;

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
        GerenciadorDados gerenciador = new GerenciadorDados();

        // Adicionando lançamentos para teste
        gerenciador.inserirLancamento(new Receita(1000, LocalDate.of(2024, 1, 10), CategoriaReceita.SALARIO));
        gerenciador.inserirLancamento(new Despesa(200, LocalDate.of(2024, 2, 15), CategoriaDespesa.ALIMENTACAO));
        gerenciador.inserirLancamento(new Receita(500, LocalDate.of(2023, 12, 25), CategoriaReceita.OUTROS));

        // Ordenar por data
        gerenciador.ordenarLancamentosPorData();

        // Exibir lançamentos ordenados
        for (Lancamento lancamento : gerenciador.getLancamentos()) {
            System.out.println("Data: " + lancamento.getData() + ", Valor: " + lancamento.getValor());
        }
    }
}
