package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Equipacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipacionId;

    @Column(unique = true)
    private String codEquipacion;

    private int cantidadTotal;

    @ElementCollection
    @CollectionTable(name = "stock_tallas",
            joinColumns = @JoinColumn(name = "codEquipacion"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "talla")
    @Column(name = "cantidad")
    private Map<Talla, Integer> stockPorTalla = new HashMap<>();

    public Equipacion() {
    }

    public Equipacion(Map<Talla, Integer> stockPorTalla, String codEquipacion, int cantidadTotal) {
        this.stockPorTalla = stockPorTalla;
        this.codEquipacion = codEquipacion;
        this.cantidadTotal = cantidadTotal;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getCodEquipacion() {
        return codEquipacion;
    }

    public void setCodEquipacion(String codEquipacion) {
        this.codEquipacion = codEquipacion;
    }

    public int getEquipacionId() {
        return equipacionId;
    }

    public void setEquipacionId(int equipacionId) {
        this.equipacionId = equipacionId;
    }

    public Map<Talla, Integer> getStockPorTalla() {
        return stockPorTalla;
    }

    public void setStockPorTalla(Map<Talla, Integer> stockPorTalla) {
        this.stockPorTalla = stockPorTalla;
    }
}
