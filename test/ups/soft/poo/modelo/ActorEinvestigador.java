package ups.soft.poo.modelo;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

// Pruebas para revisar que Actor y tambien Investigador guarden y cambien bien sus datos
class ActorEinvestigador {

    @Test
    void probarActorDatosYCambios() {
        Actor actor = new Actor("Keanu Reeves", 62, 1.86, "Canadiense");

        assertEquals("Keanu Reeves", actor.getNombre());
        assertEquals(62, actor.getEdad());
        assertEquals(1.86, actor.getAltura());
        assertEquals("Canadiense", actor.getNacionalidad());

        // Probar que los setters actualicen bien los valores
        actor.setNombre("Ana");
        actor.setEdad(28);
        actor.setAltura(1.68);
        actor.setNacionalidad("Ecuatoriana");

        assertEquals("Ana", actor.getNombre());
        assertEquals(28, actor.getEdad());
        assertEquals(1.68, actor.getAltura());
        assertEquals("Ecuatoriana", actor.getNacionalidad());
    }

    @Test
    void probarInvestigadorDatosYCambios() {
        Investigador inv = new Investigador("Carl Sagan", 62, "Estadounidense");

        assertEquals("Carl Sagan", inv.getNombre());
        assertEquals(62, inv.getEdad());
        assertEquals("Estadounidense", inv.getNacionalidad());

        inv.setNombre("Carlos");
        inv.setEdad(40);
        inv.setNacionalidad("Colombiana");

        assertEquals("Carlos", inv.getNombre());
        assertEquals(40, inv.getEdad());
        assertEquals("Colombiana", inv.getNacionalidad());
    }
}