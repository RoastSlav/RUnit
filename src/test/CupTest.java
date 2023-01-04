package test;

import Anotations.Test;
import static Assertions.Assertions.*;

class CupTest {

    @Test
    void setLiquidType() {
        Cup cup = new Cup("water", 0.5);
        cup.setLiquidType("tea");
        assertEquals("tea", cup.getLiquidType());
    }

    @Test
    void setLiquidAmount() {
        Cup cup = new Cup("water", 0.5);
        cup.setLiquidAmount(0.75);
        assertEquals(0.75, cup.getLiquidAmount());
    }

    @Test
    void getLiquidType() {
        Cup cup = new Cup("water", 0.5);
        cup.setLiquidType("tea");
        assertEquals("water", cup.getLiquidType());
    }

    @Test
    void getLiquidAmount() {
        Cup cup = new Cup("water", 0.5);
        assertEquals(0.5, cup.getLiquidAmount());
    }
}