import { useEffect, useState } from "react";
import styles from "./updateJugador.module.css";
import { API_BASE_URL } from "../../../main";
import Header from "../../header/header.component";
import { Link, useParams } from "react-router-dom";

const UpdateJugador = (user) => {

    const [formData, setFormData] = useState({
        nombre: "",
        dorsal: "",
        telefono: "",
        categoria: "",
        dni: ""
    });

    const { id } = useParams();
    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);
    const [serverError, setServerError] = useState(null);
    const [categorias, setCategorias] = useState([]);
    const [data, setData] = useState([]);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    useEffect(() => {
        const fetchCategorias = async () => {
            const response = await fetch(`${API_BASE_URL}/categorias`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            });
            const data = await response.json();
            setCategorias(data);
        };
        fetchCategorias();
    }, []);

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`${API_BASE_URL}/jugadores/${id}`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                    "Content-Type": "application/json",
                }
            });

            if (!response.ok) {
                throw new Error("Error al obtener la equipación")
            }

            const data = await response.json();
            setData(data);

            setFormData({
                nombre: data.nombre || "",
                dorsal: data.dorsal,
                telefono: data.telefono,
                categoria: data.categoria || "",
                dni: data.dni || ""
            })
        };
        fetchData();
    }, [id]);

    const handleSubmit = async (e) => {

        e.preventDefault();

        setServerError(null);
        setSuccess(false);
        setErrors({});
        setLoading(true);

        try {
            const response = await fetch(`${API_BASE_URL}/jugadores/${id}`, {
                method: "PUT",
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                const data = await response.json();
                throw new Error(data.message || "Error al actualizar el jugador");
            }

            setSuccess(true);
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
                <h2 className={styles.title}>Actualizar Jugador</h2>

                <form onSubmit={handleSubmit} className={styles.form}>

                    <input
                        className={styles.input}
                        type="text"
                        name="nombre"
                        placeholder={formData.nombre}
                        value={formData.nombre}
                        onChange={handleChange}
                    />
                    {errors.nombre && <p className={styles.error}>{errors.nombre}</p>}

                    <input
                        className={styles.input}
                        type="number"
                        name="dorsal"
                        placeholder={formData.dorsal}
                        value={formData.dorsal}
                        onChange={handleChange}
                    />
                    {errors.dorsal && <p className={styles.error}>{errors.dorsal}</p>}

                    <input
                        className={styles.input}
                        type="tel"
                        name="telefono"
                        placeholder={formData.telefono}
                        value={formData.telefono}
                        onChange={handleChange}
                    />
                    {errors.telefono && <p className={styles.error}>{errors.telefono}</p>}

                    <select
                        className={styles.input}
                        name="categoria"
                        value={formData.categoria}
                        onChange={handleChange}
                    >
                        <option value="">{formData.categoria}</option>

                        {categorias.map((categoria) => (
                            <option key={categoria} value={categoria}>
                                {categoria}
                            </option>
                        ))}
                    </select>
                    {errors.categoria && <p className={styles.error}>{errors.categoria}</p>}

                    <input
                        className={styles.input}
                        type="text"
                        name="dni"
                        placeholder={formData.dni}
                        value={formData.dni}
                        onChange={handleChange}
                    />
                    {errors.dni && <p className={styles.error}>{errors.dni}</p>}

                    <button className={styles.button} type="submit" disabled={loading}>
                        {loading ? "Actualizando Jugador..." : "Actualizar Jugador"}
                    </button>

                    {success && (
                        <p className={{ ...styles.message, color: "green" }}>
                            Jugador actaulizado correctamente
                        </p>
                    )}

                    {serverError && (
                        <p className={{ ...styles.message, color: "red" }}>
                            {serverError}
                        </p>
                    )}

                </form>

                <Link to="/jugadores">
                    <button className={styles.loginBackButton}>
                        Volver atras
                    </button>
                </Link>

            </div>
        </>
    )
}

export default UpdateJugador;