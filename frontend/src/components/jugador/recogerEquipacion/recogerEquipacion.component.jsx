import { useEffect, useState } from "react";
import styles from "./recogerEquipacion.module.css";
import { API_BASE_URL } from "../../../main";
import Header from "../../header/header.component";
import { Link, useParams } from "react-router-dom";


const RecogerEquipacion = () => {
    const { id } = useParams();

    const [formData, setFormData] = useState({
        playerId: id,
        equipacionId: "",
        talla: ""
    });

    const [jugador, setJugador] = useState([]);
    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);
    const [serverError, setServerError] = useState(null);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    useEffect(() => {
        const fecthJugador = async () => {
            const response = await fetch(`${API_BASE_URL}/jugador/${id}`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            });
            const data = await response.json();
            setJugador(data);
        };
        fecthJugador();
    }, []);

    const handleSubmit = async (e) => {

        e.preventDefault();

        setServerError(null);
        setSuccess(false);
        setErrors({});
        setLoading(true);

        try {
            const response = await fetch(`${API_BASE_URL}/recogidas`, {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                const data = await response.json();
                throw new Error(data.message || "Error al recoger la equipación");
            }

            setSuccess(true);
            setFormData({
                playerId: "",
                equipacionId: "",
                talla: ""
            });
        } catch (error) {
            setServerError(`${error}`);
        } finally {
            setLoading(false);
        }
    }



    return (
        <>
            <Header />

            <div className={styles.container}>
                <h2 className={styles.title}>Recoger equipación</h2>

                <form onSubmit={handleSubmit} className={styles.form}>

                    <select
                        className={styles.input}
                        name="equipacionId"
                        value={formData.equipacionId}
                        onChange={handleChange}
                    >
                        <option value="">Selecciona una equipación</option>

                        {jugador.equipacionAsignadaResponse.map((equipacion) => (
                            <option key={equipacion.equipacionId} 
                            value={equipacion.equipacionId}>
                                {equipacion.nombre}
                            </option>
                        ))}
                    </select>

                    <select
                        className={styles.input}
                        name="talla"
                        value={formData.talla}
                        onChange={handleChange}
                    >
                        <option value="">Selecciona una talla</option>

                        {equipacionSeleccionada &&
                            Object.entries(equipacionSeleccionada.stockPorTalla)
                                .filter(([_, stock]) => stock.cantidadDisponible > 0)
                                .map(([talla, stock]) => (
                                    <option key={talla} value={talla}>
                                        {talla} ({stock.cantidadDisponible})
                                    </option>
                                ))}
                    </select>





                    <button className={styles.button} type="submit" disabled={loading}>
                        {loading ? "Asignando equipación..." : "Asignar equipación"}
                    </button>

                    {success && (
                        <p className={{ ...styles.message, color: "green" }}>
                            Equipación recogida correctamente
                        </p>
                    )}

                    {serverError && (
                        <p className={{ ...styles.message, color: "red" }}>
                            {serverError}
                        </p>
                    )}

                </form>

                <Link to={`/jugadores/${id}`}>
                    <button className={styles.loginBackButton}>
                        Volver atras
                    </button>
                </Link>

            </div>
        </>
    )

}

export default RecogerEquipacion;