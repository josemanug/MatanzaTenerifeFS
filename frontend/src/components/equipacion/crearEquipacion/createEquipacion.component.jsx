import React, { useEffect, useState } from "react";
import styles from "./createEquipacion.module.css";
import { API_BASE_URL } from "../../../main";
import Header from "../../header/header.component";
import { Link } from "react-router-dom";

const CreateEquipacion = (user) => {

    const [formData, setFormData] = useState({
        codEquipacion: "",
        nombre: "",
        stockPorTalla: {}
    });

    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);
    const [serverError, setServerError] = useState(null);
    const [tallas, setTallas] = useState([]);

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const validate = () => {
        const newErrors = {};

        // Para el código de equipación
        if (!formData.codEquipacion)
            newErrors.codEquipacion = "El código de equipación es obligatorio";

        // Para el nombre
        if (!formData.nombre)
            newErrors.nombre = "El nombre es obligatorio";

        // Para el stock por talla
        if (!formData.stockPorTalla || Object.keys(formData.stockPorTalla).length === 0)
            newErrors.stockPorTalla = "El stock por talla es obligatorio";

        return newErrors;
    }

    const handleStockChange = (talla, cantidad) => {
        setFormData({
            ...formData, stockPorTalla: {
                ...formData.stockPorTalla, [talla]: Number(cantidad)
            }
        });
    };

    useEffect(() => {
        const fetchTallas = async () => {
            const response = await fetch(`${API_BASE_URL}/tallas`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`
                }
            });

            const data = await response.json();
            setTallas(data);
        };

        fetchTallas();
    }, []);

    const handleSubmit = async (e) => {

        e.preventDefault();

        setServerError(null);
        setSuccess(false);

        const validationErrors = validate();
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        setErrors({});
        setLoading(true);

        try {
            const response = await fetch(`${API_BASE_URL}/equipaciones`, {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(formData)
            });

            if (!response.ok) {
                const data = await response.json();
                throw new Error(data.message || "Error al registrar la equipación");
            }

            setSuccess(true);
            setFormData({
                codEquipacion: "",
                nombre: "",
                stockPorTalla: {}
            });
        } catch (error) {
            setServerError("Error al crear la equipación. Por favor, inténtelo de nuevo.");
        } finally {
            setLoading(false);
        }
    }


    return (
        <>
            <Header />

            <div className={styles.container}>
                <h2 className={styles.title}>Nueva Reseña</h2>

                <form onSubmit={handleSubmit} className={styles.form}>

                    <input
                        className={styles.input}
                        type="text"
                        name="codEquipacion"
                        placeholder="codequipacion (CamAzulPor)"
                        value={formData.codEquipacion}
                        onChange={handleChange}
                    />
                    {errors.codEquipacion && <p className={styles.error}>{errors.codEquipacion}</p>}

                    <input
                        className={styles.input}
                        type="text"
                        name="nombre"
                        placeholder="nombre"
                        value={formData.nombre}
                        onChange={handleChange}
                    />
                    {errors.nombre && <p className={styles.error}>{errors.nombre}</p>}

                    {
                        tallas.map((talla) => (
                            <div key={talla} className={styles.talla}>
                                <label>Stock {talla}:</label>

                                <input
                                    className={styles.stockInput}
                                    type="number"
                                    min="0"
                                    value={formData.stockPorTalla[talla] || 0}
                                    onChange={(e) =>
                                        handleStockChange(talla, e.target.value)
                                    }
                                />
                            </div>
                        ))
                    }

                    <button className={styles.button} type="submit" disabled={loading}>
                        {loading ? "Creando nueva Equipación..." : "Crear nueva Equipación"}
                    </button>

                    {success && (
                        <p className={{ ...styles.message, color: "green" }}>
                            Equipación creada correctamente
                        </p>
                    )}

                    {serverError && (
                        <p className={{ ...styles.message, color: "red" }}>
                            {serverError}
                        </p>
                    )}

                </form>

                <Link to="/equipaciones">
                    <button className={styles.loginBackButton}>
                        Volver atras
                    </button>
                </Link>

            </div>
        </>
    )
}

export default CreateEquipacion;