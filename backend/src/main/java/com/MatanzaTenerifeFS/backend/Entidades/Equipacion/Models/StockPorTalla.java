package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models;

import jakarta.persistence.Embeddable;

@Embeddable
public class StockPorTalla {

    private int cantidadTotal;

    private int cantidadDisponible;

    public StockPorTalla() {
    }

    public StockPorTalla(int cantidadTotal, int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadTotal = cantidadTotal;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }
}
