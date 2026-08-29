package com.MatanzaTenerifeFS.backend.Entidades.Recogida.Models;

import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Equipacion;
import com.MatanzaTenerifeFS.backend.Entidades.Equipacion.Models.Talla;
import com.MatanzaTenerifeFS.backend.Entidades.Jugador.Models.Jugador;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RecogidaEquipacion {

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

    private LocalDateTime fechaRecogida;

    public RecogidaEquipacion() {
    }

    public RecogidaEquipacion(Equipacion equipacion, Jugador jugador, Talla talla) {
        this.equipacion = equipacion;
        this.jugador = jugador;
        this.talla = talla;
        this.fechaRecogida = LocalDateTime.now();
    }

    public Equipacion getEquipacion() {
        return equipacion;
    }

    public void setEquipacion(Equipacion equipacion) {
        this.equipacion = equipacion;
    }

    public LocalDateTime getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(LocalDateTime fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
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
}
