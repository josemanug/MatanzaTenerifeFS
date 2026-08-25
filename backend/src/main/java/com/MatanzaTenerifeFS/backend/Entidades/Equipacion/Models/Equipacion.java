package com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models;

import com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models.AsignacionEquipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;
import jakarta.persistence.*;

import java.util.ArrayList;
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

    public int cantidadTotal(){
        return stockPorTalla
                .values()
                .stream()
                .mapToInt(StockPorTalla::getCantidadTotal)
                .sum();
    };

    public int cantidadDisponible(){
        return stockPorTalla
                .values()
                .stream()
                .mapToInt(StockPorTalla::getCantidadDisponible)
                .sum();
    };

    @ElementCollection
    @CollectionTable(
            name = "stock_tallas",
            joinColumns = @JoinColumn(name = "equipacionId")
    )
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "talla")
    private Map<Talla, StockPorTalla> stockPorTalla = new HashMap<>();

    @OneToMany(
            mappedBy = "equipacion",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AsignacionEquipacion> asignaciones = new ArrayList<>();

    public Equipacion() {
    }

    public Equipacion(String codEquipacion, String nombre, Map<Talla, StockPorTalla> stockPorTalla) {
        this.stockPorTalla = stockPorTalla;
        this.codEquipacion = codEquipacion;
        this.nombre = nombre;
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

    public List<AsignacionEquipacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<AsignacionEquipacion> asignaciones) {
        this.asignaciones = asignaciones;
    }
}
