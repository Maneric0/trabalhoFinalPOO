/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import model.*;
/**
 *
 * @author leona
 */
public class GerenciadorDadosTest {
    private GerenciadorDados gerenciador;
    private Receita receita;
    private Despesa despesa;
   @TempDir
    File tempDir;  // Diretório temporário criado para testes

    @BeforeEach
    public void setUp() {
        // Criar o arquivo temporário para o teste
        File arquivo = new File(tempDir, "dados.csv");
        gerenciador = new GerenciadorDados(arquivo);

        // Criar alguns lançamentos
        receita = new Receita(2000.0, LocalDate.of(2023, 11, 22), CategoriaReceita.SALARIO);
        despesa = new Despesa(500.0, LocalDate.of(2023, 11, 23), CategoriaDespesa.ALIMENTACAO);
    }

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

    @Test
    public void testListarReceitas() {
        // Inserir receita e verificar se ela é listada
        gerenciador.inserirLancamento(receita);
        List<Receita> receitas = gerenciador.listarReceitas();

        assertEquals(1, receitas.size(), "Deve haver uma receita na lista.");
        assertEquals(receita.getValor(), receitas.get(0).getValor(), "O valor da receita deve ser igual ao esperado.");
    }

    @Test
    public void testListarDespesas() {
        // Inserir despesa e verificar se ela é listada
        gerenciador.inserirLancamento(despesa);
        List<Despesa> despesas = gerenciador.listarDespesas();

        assertEquals(1, despesas.size(), "Deve haver uma despesa na lista.");
        assertEquals(despesa.getValor(), despesas.get(0).getValor(), "O valor da despesa deve ser igual ao esperado.");
    }

    @Test
    public void testSalvarDados() throws IOException {
        // Salvar dados e garantir que o arquivo foi criado
        gerenciador.inserirLancamento(receita);
        File arquivo = gerenciador.getArquivo();

        assertTrue(arquivo.exists(), "O arquivo de dados deve existir após salvar um lançamento.");
    }

}
