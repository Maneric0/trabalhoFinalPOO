/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */


import model.Despesa;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author leona
 */
public class DespesaTest {
    
    private Despesa despesa;

    @Test
    public void testCalcularSaldo() {
        // Verificando se o saldo é negativo
        assertEquals(-100.0, despesa.calcularSaldo(), "O saldo deve ser negativo do valor");
    }
 
}