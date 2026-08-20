import { useEffect, useState } from "react";
import styles from "./jugador.module.css";
import { API_BASE_URL } from "../../main";
import Header from "../header/header.component";
import { Link } from "react-router-dom";

function Jugador() {

    const [jugadores, setJugadores] = useState([]);
    const [loading, setLoading] = useState(true);


    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) {
            setLoading(false);
            return;
        }

        const fetchJugador = async () => {
            try {
                const response = await fetch(`${API_BASE_URL}/jugador`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                });

                if (!response.ok) {
                    throw new Error("Error al obtener las equipaciones");
                }

                const data = await response.json();
                setJugadores(data);

            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false);
            }
        };

        fetchJugador();

    }, []);

    if (loading) {
        return <p className={styles.loading}>Cargando jugadores...</p>;
    }

    return (
        <>

            <Header />

            <h1>Listado de jugadores</h1>

            <Link to="/jugadores/create">
                <button className="btn btn-primary" type="subbmit">
                    Nuevo Jugador
                </button>
            </Link>

            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Categoría</th>
                        <th>Dorsal</th>
                    </tr>
                </thead>

                <tbody>

                    {jugadores.map((appt) => (

                        
                        <tr key={appt.id}>
                            <td>
                                <Link to={`/jugadores/${appt.id}`}>{appt.nombre}</Link>
                            </td>

                            <td>
                                <Link to={`/jugadores/${appt.id}`}>{appt.categoria}</Link>
                            </td>

                            <td>
                                <Link to={`/jugadores/${appt.id}`}>{appt.dorsal}</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>

        </>
    )
}

export default Jugador;