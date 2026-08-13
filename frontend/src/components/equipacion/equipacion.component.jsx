import React, { useEffect, useState } from "react";
import styles from "./equipacion.module.css";
import { API_BASE_URL } from "../../main";
import Header from "../header/header.component";
import { Link } from "react-router-dom";

function Equipacion() {

    const [equipaciones, setEquipaciones] = useState([]);
    const [loading, setLoading] = useState(true);


    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) {
            setLoading(false);
            return;
        }

        const fetchEquipaciones = async () => {
            try {
                const response = await fetch(`${API_BASE_URL}/equipaciones`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                });

                if (!response.ok) {
                    throw new Error("Error al obtener las equipaciones");
                }

                const data = await response.json();
                setEquipaciones(data);

            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false);
            }
        };

        fetchEquipaciones();

    }, []);

    if (loading) {
        return <p className={styles.loading}>Cargando equipaciones...</p>;
    }

    return (
        <>

            <Header />

            <h1>Listado de equipaciones</h1>

            <Link to="/equipaciones/create">
                <button className="btn btn-primary" type="subbmit">
                    Crear Equipación
                </button>
            </Link>

            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>Código equipación</th>
                        <th>Nombre</th>
                        <th>Cantidad Total</th>
                    </tr>
                </thead>

                <tbody>

                    {equipaciones.map((appt) => (

                        
                        <tr key={appt.id}>
                            <td>
                                <Link to={`/equipaciones/${appt.id}`}>{appt.codEquipacion}</Link>
                            </td>

                            <td>
                                <Link to={`/equipaciones/${appt.id}`}>{appt.nombre}</Link>
                            </td>

                            <td>
                                <Link to={`/equipaciones/${appt.id}`}>{appt.cantidadTotal}</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

        </>
    )
}

export default Equipacion;