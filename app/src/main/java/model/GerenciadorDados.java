/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manerico
 */
public class GerenciadorDados {
    private List<Lancamento> lancamentos = new ArrayList<>();

    public void inserirLancamento(Lancamento lancamento) {
        lancamentos.add(lancamento);
    }

    public List<Receita> listarReceitas() {
        List<Receita> receitas = new ArrayList<>();
        for (Lancamento lancamento : lancamentos) {
            if (lancamento instanceof Receita) {
                receitas.add((Receita) lancamento);
            }
        }
        return receitas;
    }

    public List<Despesa> listarDespesas() {
        List<Despesa> despesas = new ArrayList<>();
        for (Lancamento lancamento : lancamentos) {
            if (lancamento instanceof Despesa) {
                despesas.add((Despesa) lancamento);
            }
        }
        return despesas;
    }
    
    public void salvarDados() {
        File arquivo = new File("dados.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            for (Lancamento lancamento : lancamentos) {
                if (lancamento instanceof Receita) {
                    Receita receita = (Receita) lancamento;
                    writer.write(receita.getValor() + ";" + receita.getData() + ";" + receita.getCategoria());
                    writer.newLine(); // Para separar os registros
                } else if (lancamento instanceof Despesa) {
                    Despesa despesa = (Despesa) lancamento;
                    writer.write(despesa.getValor() + ";" + despesa.getData() + ";" + despesa.getCategoria());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        File arquivo = new File("dados.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] campos = linha.split(";");
            String tipo = campos[0];
            double valor = Double.parseDouble(campos[1]);
            LocalDate data = LocalDate.parse(campos[2]);
            String categoria = campos[3];

            if ("Receita".equals(tipo)) {
                CategoriaReceita catReceita = CategoriaReceita.valueOf(categoria);
                inserirLancamento(new Receita(valor, data, catReceita));
            } else if ("Despesa".equals(tipo)) {
                CategoriaDespesa catDespesa = CategoriaDespesa.valueOf(categoria);
                inserirLancamento(new Despesa(valor, data, catDespesa));
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void ordenarLancamentosPorData() {
        lancamentos.sort(new ComparadorPorData()); // Ordena a lista de lançamentos
    }
    
    /**
     * @return the lancamentos
     */
    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    /**
     * @param lancamentos the lancamentos to set
     */
    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }
}
