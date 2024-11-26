/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package model;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.io.TempDir;

/**
 *
 * @author João Pedro
 */
public class GerenciadorDadosTest {
    private static GerenciadorDados gerenciador;
    private static Receita receita;
    private static Despesa despesa;
    
    @TempDir
    private static File tempDir;  // Diretório temporário criado para testes
    
    public GerenciadorDadosTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
         // Criar o arquivo temporário para o teste
        File arquivo = new File(tempDir, "dados.csv");
        gerenciador = new GerenciadorDados(arquivo);
        // Criar alguns lançamentos
        receita = new Receita(2000.0, LocalDate.of(2023, 11, 22), CategoriaReceita.SALARIO);
        despesa = new Despesa(500.0, LocalDate.of(2023, 11, 23), CategoriaDespesa.ALIMENTACAO);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of inserirLancamento method, of class GerenciadorDados.
     */
    @Test
    public void testInserirLancamentoReceita() {
         // Inserir uma receita e verificar se o saldo foi atualizado
        gerenciador.inserirLancamento(receita);
        
        assertEquals(2000.0, gerenciador.getSaldo(), "O saldo após a receita deve ser 2000.");
    }
    
    @Test
    public void testInserirLancamentoDespesa() {
        // Inserir uma despesa e verificar se o saldo foi atualizado
        gerenciador.inserirLancamento(despesa);
        
        assertEquals(-500.0, gerenciador.getSaldo(), "O saldo após a despesa deve ser -500.");
    }

    /**
     * Test of listarReceitas method, of class GerenciadorDados.
     */
    @Test
    public void testListarReceitas() {
        // Inserir receita e verificar se ela é listada
        gerenciador.inserirLancamento(receita);
        List<Receita> receitas = gerenciador.listarReceitas();
        assertEquals(1, receitas.size(), "Deve haver uma receita na lista.");
        assertEquals(receita.getValor(), receitas.get(0).getValor(), "O valor da receita deve ser igual ao esperado.");
    }

    /**
     * Test of listarDespesas method, of class GerenciadorDados.
     */
    @Test
    public void testListarDespesas() {
        // Inserir despesa e verificar se ela é listada
        gerenciador.inserirLancamento(despesa);
        List<Despesa> despesas = gerenciador.listarDespesas();
        assertEquals(1, despesas.size(), "Deve haver uma despesa na lista.");
        assertEquals(despesa.getValor(), despesas.get(0).getValor(), "O valor da despesa deve ser igual ao esperado.");
    }

    /**
     * Test of salvarDados method, of class GerenciadorDados.
     */
    @Test
    public void testSalvarDados() {
        // Salvar dados e garantir que o arquivo foi criado
        gerenciador.inserirLancamento(receita);
        File arquivo = gerenciador.getArquivo();
        assertTrue(arquivo.exists(), "O arquivo de dados deve existir após salvar um lançamento.");
    }

    /**
     * Test of carregarDados method, of class GerenciadorDados.
     */
    @Test
    public void testCarregarDados() {
        System.out.println("carregarDados");
        GerenciadorDados instance = null;
        instance.carregarDados();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ordenarLancamentosPorData method, of class GerenciadorDados.
     */
    @Test
    public void testOrdenarLancamentosPorData() {
        System.out.println("ordenarLancamentosPorData");
        GerenciadorDados instance = null;
        instance.ordenarLancamentosPorData();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArquivo method, of class GerenciadorDados.
     */
    @Test
    public void testGetArquivo() {
        System.out.println("getArquivo");
        GerenciadorDados instance = null;
        File expResult = null;
        File result = instance.getArquivo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArquivo method, of class GerenciadorDados.
     */
    @Test
    public void testSetArquivo() {
        System.out.println("setArquivo");
        File arquivo = null;
        GerenciadorDados instance = null;
        instance.setArquivo(arquivo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSaldo method, of class GerenciadorDados.
     */
    @Test
    public void testGetSaldo() {
        System.out.println("getSaldo");
        GerenciadorDados instance = null;
        double expResult = 0.0;
        double result = instance.getSaldo();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSaldo method, of class GerenciadorDados.
     */
    @Test
    public void testSetSaldo() {
        System.out.println("setSaldo");
        double saldo = 0.0;
        GerenciadorDados instance = null;
        instance.setSaldo(saldo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLancamentos method, of class GerenciadorDados.
     */
    @Test
    public void testGetLancamentos() {
        System.out.println("getLancamentos");
        GerenciadorDados instance = null;
        List<Lancamento> expResult = null;
        List<Lancamento> result = instance.getLancamentos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLancamentos method, of class GerenciadorDados.
     */
    @Test
    public void testSetLancamentos() {
        System.out.println("setLancamentos");
        List<Lancamento> lancamentos = null;
        GerenciadorDados instance = null;
        instance.setLancamentos(lancamentos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
