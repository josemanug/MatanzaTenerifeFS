import{ useEffect, useState } from "react";
import styles from "./detalles.module.css";
import { API_BASE_URL } from "../../../main";
import Header from "../../header/header.component";
import { Link, useParams } from "react-router-dom";


const DetallesJugador = () => {

    const { id } = useParams();
    const [jugador, setJugador] = useState();
    const [loading, setLoading] = useState(true);
    const [serverError, setServerError] = useState(null);


    useEffect(() => {

        const token = localStorage.getItem("token")

        if (!token) {
            setLoading(false);
            return;
        }

        const fetchJugador = async () => {
            try {
                const response = await fetch(`${API_BASE_URL}/jugadores/${id}`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                });

                if (!response.ok) {
                    throw new Error("Error al obtener el jugador")
                }

                const data = await response.json();
                setJugador(data);

            } catch (error) {
                setServerError(`${error}`);
            } finally {
                setLoading(false)
            }
        };

        fetchJugador();

    }, [id]);

    if (loading) {
        return <p className={styles.loading}>Cargando detalles del Jugador...</p>;
    }


    return (
        <>
            <Header />

            <h1>{jugador.nombre}</h1>

            <Link to={`/jugadores/update/${id}`}>
                            <button className="btn btn-primary" type="subbmit">
                                Actualizar Jugador
                            </button>
                        </Link>

            <table className={styles.table}>
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Dorsal</th>
                        <th>Teléfono</th>
                        <th>DNI</th>
                        <th>Categoría</th>
                    </tr>
                </thead>
                <tbody>
                    <tr key={jugador.playerId}>
                        <td>{jugador.nombre}</td>
                        <td>{jugador.dorsal}</td>
                        <td>{jugador.telefono}</td>
                        <td>{jugador.dni}</td>
                        <td>{jugador.categoria}</td>
                    </tr>

                </tbody>

            </table>

            <Link to="/jugadores">
                <p>Volver al listado</p>
            </Link>
        </>
    )
}

export default DetallesJugador;