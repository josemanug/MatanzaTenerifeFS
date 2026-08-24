package com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerId;

    private String nombre;

    private int dorsal;

    private int telefono;

    private String categoria;

    private String dni;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "equipacion_jugador",
            joinColumns = @JoinColumn(name = "playerId"),
            inverseJoinColumns = @JoinColumn(name = "equipacionId")
    )
    private List<Equipacion> equipaciones = new ArrayList<>();

    public Jugador() {
    }

    public Jugador( String nombre, int dorsal, int telefono, String dni, String categoria) {
        this.categoria = categoria;
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
        this.dorsal = dorsal;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public List<Equipacion> getEquipaciones() {
        return equipaciones;
    }

    public void setEquipaciones(List<Equipacion> equipaciones) {
        this.equipaciones = equipaciones;
    }
}
