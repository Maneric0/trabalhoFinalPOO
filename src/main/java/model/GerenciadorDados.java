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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manerico
 */
public class GerenciadorDados {
    private File arquivo;
    private double saldo = 0.00;
    private List<Lancamento> lancamentos;

    public GerenciadorDados(File file) {
        setArquivo(file);
        lancamentos = new ArrayList<>();
        carregarDados();
    }

    public void inserirLancamento(Lancamento lancamento) {
        if (lancamento instanceof Receita) {
            setSaldo(getSaldo() + lancamento.getValor());
        } else if (lancamento instanceof Despesa) {
            setSaldo(getSaldo() - lancamento.getValor());
        }
        lancamento.setSaldo(getSaldo());
        getLancamentos().add(lancamento);
        salvarDados(lancamento); 
    }

    public List<Receita> listarReceitas() {
        List<Receita> receitas = new ArrayList<>();
        for (Lancamento lancamento : getLancamentos()) {
            if (lancamento instanceof Receita) {
                receitas.add((Receita) lancamento);
            }
        }
        return receitas;
    }

    public List<Despesa> listarDespesas() {
        List<Despesa> despesas = new ArrayList<>();
        for (Lancamento lancamento : getLancamentos()) {
            if (lancamento instanceof Despesa) {
                despesas.add((Despesa) lancamento);
            }
        }
        return despesas;
    }

    public void salvarDados(Lancamento lancamento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getArquivo(), true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String tipo = lancamento instanceof Receita ? "Receita" : "Despesa";
            String categoria = lancamento instanceof Receita
                    ? ((Receita) lancamento).getCategoria().toString()
                    : ((Despesa) lancamento).getCategoria().toString();

            writer.write(tipo + ";"
                    + lancamento.getValor() + ";"
                    + lancamento.getData().format(formatter) + ";"
                    + categoria + ";"
                    + lancamento.getSaldo() + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public void carregarDados() {
        try (BufferedReader br = new BufferedReader(new FileReader(getArquivo()))) {
            String linha;
            boolean primeiraLinha = true;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] campos = linha.split(";");
                if (campos.length < 5) {
                    System.out.println("Linha inválida no CSV: " + linha);
                    continue;
                }

                String tipo = campos[0];
                double valor = Double.parseDouble(campos[1]);
                LocalDate data = LocalDate.parse(campos[2], formatter);
                String categoriaStr = campos[3];
                double saldoLido = Double.parseDouble(campos[4]);

                if (tipo.equalsIgnoreCase("Receita")) {
                    CategoriaReceita categoria = CategoriaReceita.valueOf(categoriaStr);
                    Receita receita = new Receita(valor, data, categoria);
                    setSaldo(getSaldo() + valor);
                    receita.setSaldo(getSaldo());
                    getLancamentos().add(receita);
                } else if (tipo.equalsIgnoreCase("Despesa")) {
                    CategoriaDespesa categoria = CategoriaDespesa.valueOf(categoriaStr);
                    Despesa despesa = new Despesa(valor, data, categoria);
                    setSaldo(getSaldo() - valor);
                    despesa.setSaldo(getSaldo());
                    getLancamentos().add(despesa);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
    
    public void ordenarLancamentosPorData() {
        getLancamentos().sort(new ComparadorPorData());
    }

    /**
     * @return the arquivo
     */
    public File getArquivo() {
        return arquivo;
    }

    /**
     * @param arquivo the arquivo to set
     */
    public void setArquivo(File arquivo) {
        this.arquivo = arquivo;
    }

    /**
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
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