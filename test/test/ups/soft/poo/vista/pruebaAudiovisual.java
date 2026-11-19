package test.ups.soft.poo.vista;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import ups.soft.poo.vista.PruebaAudioVisual;

class pruebaAudiovisual {

    @Test
    void probarValidacionIngresosNumeroValido() {
        Scanner sc = new Scanner("3");
        int opcion = PruebaAudioVisual.validacionIngresos(sc);
        assertEquals(3, opcion);
    }

    @Test
    void probarValidacionIngresosConTextoYLuegoNumero() {
        Scanner sc = new Scanner("hola 5");
        int opcion = PruebaAudioVisual.validacionIngresos(sc, "Error");
        assertEquals(5, opcion);
    }
}