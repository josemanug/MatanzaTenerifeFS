import React, { useEffect, useState } from "react";
import styles from "./updateEquipacion.module.css";
import { API_BASE_URL } from "../../../main";
import Header from "../../header/header.component";
import { Link, useParams } from "react-router-dom";

const UpdateEquipacion = (user) => {

    const [formData, setFormData] = useState({
        codEquipacion: "",
        nombre: "",
        stockPorTalla: {}
    });

    const { id } = useParams();
    const [errors, setErrors] = useState({});
    const [loading, setLoading] = useState(false);
    const [success, setSuccess] = useState(false);
    const [serverError, setServerError] = useState(null);
    const [tallas, setTallas] = useState([]);
    const [data, setData] = useState([]);

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
    const nuevoTotal = Number(cantidad);

    setFormData(prev => {
        const stockActual = prev.stockPorTalla[talla];

        const totalAnterior = stockActual?.cantidadTotal ?? 0;
        const disponibleAnterior = stockActual?.cantidadDisponible ?? 0;

        const diferencia = nuevoTotal - totalAnterior;

        let nuevaDisponible = disponibleAnterior;

        // Si aumenta el total, las nuevas unidades entran
        // directamente en el almacén.
        if (diferencia > 0) {
            nuevaDisponible = disponibleAnterior + diferencia;
        }

        // Si disminuye el total, no modificamos disponible.
        return {
            ...prev,
            stockPorTalla: {
                ...prev.stockPorTalla,
                [talla]: {
                    cantidadTotal: nuevoTotal,
                    cantidadDisponible: nuevaDisponible
                }
            }
        };
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

    useEffect(() => {
        const fetchData = async () => {
            const response = await fetch(`${API_BASE_URL}/equipaciones/${id}`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                    "Content-Type": "application/json",
                },
            });

            if (!response.ok) {
                throw new Error("Error al obtener la equipación")
            }

            const data = await response.json();
            setData(data);

            setFormData({
                codEquipacion: data.codEquipacion || "",
                nombre: data.nombre || "",
                stockPorTalla: data.stockPorTalla || {}
            })
        };
        fetchData();
    }, [id]);

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
            const response = await fetch(`${API_BASE_URL}/equipaciones/${id}`, {
                method: "PUT",
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
                <h2 className={styles.title}>Actualizar Equipación</h2>

                <form onSubmit={handleSubmit} className={styles.form}>

                    <input
                        className={styles.input}
                        type="text"
                        name="codEquipacion"
                        placeholder={`codEquipacion(${data.codEquipacion})`}
                        value={formData.codEquipacion}
                        onChange={handleChange}
                        disabled
                    />
                    {errors.codEquipacion && <p className={styles.error}>{errors.codEquipacion}</p>}

                    <input
                        className={styles.input}
                        type="text"
                        name="nombre"
                        placeholder={data.nombre}
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
                                    value={formData.stockPorTalla[talla]?.cantidadTotal ?? 0}
                                    onChange={(e) =>
                                        handleStockChange(talla, e.target.value)
                                    }
                                />
                            </div>
                        ))
                    }


                    <button className={styles.button} type="submit" disabled={loading}>
                        {loading ? "Actualizando Equipación..." : "Actualizar Equipación"}
                    </button>

                    {success && (
                        <p className={{ ...styles.message, color: "green" }}>
                            Equipación actualizada correctamente
                        </p>
                    )}

                    {serverError && (
                        <p className={{ ...styles.message, color: "red" }}>
                            {serverError}
                        </p>
                    )}

                </form>

                <Link to={`/equipaciones/${id}`}>
                    <button className={styles.loginBackButton}>
                        Volver atras
                    </button>
                </Link>

            </div>
        </>
    )
}

export default UpdateEquipacion;