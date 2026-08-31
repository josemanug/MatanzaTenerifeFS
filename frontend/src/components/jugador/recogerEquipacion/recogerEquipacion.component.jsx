import { useEffect, useState } from "react";
import styles from "./recogerEquipacion.module.css";
import { API_BASE_URL } from "../../../main";
import Header from "../../header/header.component";
import { Link, useParams } from "react-router-dom";


const RecogerEquipacion = () => {
    const { id } = useParams();

    const [formData, setFormData] = useState({
        playerId: id,
        asigancionId: ""
    });

    const [equipacionesAsignadas, setEquipacionesAsignadas] = useState([]);
    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);
    const [serverError, setServerError] = useState(null);

    const handleChange = (e) => {
        const { name, value } = e.target;

        if (name === "equipacionId") {
            const asignacionSeleccionada = equipacionesAsignadas.find(
                (asignacion) => asignacion.id.toString() === value
            );

            setFormData({
                ...formData,
                equipacionId: asignacionSeleccionada?.equipacionId || "",
                talla: asignacionSeleccionada?.talla || ""
            });

            return;
        }

        setFormData({
            ...formData,
            [name]: value
        });
    };




    useEffect(() => {
        const fecthEquipacionesAsignadas = async () => {
            const response = await fetch(`${API_BASE_URL}/asignaciones/${id}/asignadas`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            });
            const data = await response.json();
            setEquipacionesAsignadas(data);
        };
        fecthEquipacionesAsignadas();
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
                        name="asignacionId"
                        value={formData.asignacionId}
                        onChange={handleChange}
                    >
                        <option value="">
                            Selecciona una equipación
                        </option>

                        {equipacionesAsignadas.map((asignacion) => (
                            <option
                                key={asignacion.id}
                                value={asignacion.id}
                            >
                                {asignacion.nombre} - Talla {asignacion.talla}
                            </option>
                        ))}
                    </select>



                    <button className={styles.button} type="submit" disabled={loading}>
                        {loading ? "Recogiendo equipación..." : "Recoger equipación"}
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