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

    private String nombre;

    private int cantidadTotal;

    private int cantidadDisponible;

    @ElementCollection
    @CollectionTable(
            name = "stock_tallas",
            joinColumns = @JoinColumn(name = "equipacionId")
    )
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "talla")
    private Map<Talla, StockPorTalla> stockPorTalla = new HashMap<>();

    public Equipacion() {
    }

    public Equipacion(String codEquipacion, String nombre, Map<Talla, StockPorTalla> stockPorTalla) {
        this.stockPorTalla = stockPorTalla;
        this.codEquipacion = codEquipacion;
        this.nombre = nombre;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Map<Talla, StockPorTalla> getStockPorTalla() {
        return stockPorTalla;
    }

    public void setStockPorTalla(Map<Talla, StockPorTalla> stockPorTalla) {
        this.stockPorTalla = stockPorTalla;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
}
