import React, { useEffect, useState } from "react";
import styles from "./detalles.module.css";
import { API_BASE_URL } from "../../../main";
import Header from "../../header/header.component";
import { Link, useParams } from "react-router-dom";


const Detalles = () => {

    const { id } = useParams()
    const [details, setDetails] = useState();
    const [loading, setLoading] = useState(true);
    const [serverError, setServerError] = useState(null);

    useEffect(() => {

        const token = localStorage.getItem("token")

        if (!token) {
            setLoading(false);
            return;
        }

        const fetchDetails = async () => {
            try {
                const response = await fetch(`${API_BASE_URL}/equipaciones/${id}`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                });

                if (!response.ok) {
                    throw new Error("Error al obtener la equipación")
                }

                const data = await response.json();
                setDetails(data);

            } catch (error) {
                setServerError(`${error}`);
            } finally {
                setLoading(false)
            }
        };

        fetchDetails();

    }, []);

    if (loading) {
        return <p className={styles.loading}>Cargando detalles de la equipación...</p>;
    }

    return (
        <>
            <Header />

            <h1>{details.nombre} ({details.cantidadTotal})</h1>

            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>Talla</th>
                        <th>Cantidad</th>
                    </tr>
                </thead>
                <tbody>
                    {Object.entries(details.stockPorTalla).map(([talla, cantidad]) => (
                        <tr key={talla}>
                            <td>{talla}</td>
                            <td>{cantidad}</td>
                        </tr>
                    ))}

                </tbody>

            </table>

            <Link to="/equipaciones">
                <p>Volver al listado</p>
            </Link>
        </>
    )
}

export default Detalles;