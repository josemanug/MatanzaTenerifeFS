package com.MatanzaTenerifeFS.backend.Entidades.Asignacion.Models;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AsignacionEquipacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "equipacion_id", nullable = false)
    private Equipacion equipacion;

    @Enumerated(EnumType.STRING)
    private Talla talla;

    private LocalDateTime fechaAsignacion;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public AsignacionEquipacion() {
    }

    public AsignacionEquipacion(Equipacion equipacion, Jugador jugador, Talla talla) {
        this.equipacion = equipacion;
        this.jugador = jugador;
        this.talla = talla;
        this.fechaAsignacion = LocalDateTime.now();
    }

    public Equipacion getEquipacion() {
        return equipacion;
    }

    public void setEquipacion(Equipacion equipacion) {
        this.equipacion = equipacion;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
