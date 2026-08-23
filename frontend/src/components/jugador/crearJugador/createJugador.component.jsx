import { useEffect, useState } from "react";
import styles from "./createJugador.module.css";
import { API_BASE_URL } from "../../../main";
import Header from "../../header/header.component";
import { Link } from "react-router-dom";

const CreateJugador = (user) => {

    const [formData, setFormData] = useState({
        nombre: "",
        dorsal: "",
        telefono: "",
        categoria: "",
        dni: ""
    });

    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);
    const [serverError, setServerError] = useState(null);
    const [categorias, setCategorias] = useState([])

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

    const handleSubmit = async (e) => {

        e.preventDefault();

        setServerError(null);
        setSuccess(false);
        setErrors({});
        setLoading(true);

        try {
            const response = await fetch(`${API_BASE_URL}/jugadores`, {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                const data = await response.json();
                throw new Error(data.message || "Error al registrar al jugador");
            }

            setSuccess(true);
            setFormData({
                nombre: "",
                dorsal: "",
                telefono: "",
                categoria: "",
                dni: ""
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
                <h2 className={styles.title}>Nuevo Jugador</h2>

                <form onSubmit={handleSubmit} className={styles.form}>

                    <input
                        className={styles.input}
                        type="text"
                        name="nombre"
                        placeholder="Nombre del jugador"
                        value={formData.nombre}
                        onChange={handleChange}
                    />
                    {errors.nombre && <p className={styles.error}>{errors.nombre}</p>}

                    <input
                        className={styles.input}
                        type="number"
                        name="dorsal"
                        placeholder="Dorsal"
                        value={formData.dorsal}
                        onChange={handleChange}
                    />
                    {errors.dorsal && <p className={styles.error}>{errors.dorsal}</p>}

                    <input
                        className={styles.input}
                        type="tel"
                        name="telefono"
                        placeholder="Teléfono"
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
                        <option value="">Selecciona una categoría</option>

                        {categorias.map((categoria) => (
                            <option>
                                {categoria}
                            </option>
                        ))}
                    </select>
                    {errors.categoria && <p className={styles.error}>{errors.categoria}</p>}

                    <input
                        className={styles.input}
                        type="text"
                        name="dni"
                        placeholder="DNI"
                        value={formData.dni}
                        onChange={handleChange}
                    />
                    {errors.dni && <p className={styles.error}>{errors.dni}</p>}

                    <button className={styles.button} type="submit" disabled={loading}>
                        {loading ? "Creando nuevo Jugador..." : "Añadir Jugador"}
                    </button>

                    {success && (
                        <p className={{ ...styles.message, color: "green" }}>
                            Jugador creado correctamente
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

export default CreateJugador;