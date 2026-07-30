import "./landing.module.css";
import { Link } from "react-router-dom"

function Landing() {
    return (
        <div className="home-container">
            <div className="overlay">
                <div className="container text-center text-white">

                    <h1 className="display-3 fw-bold mb-3">
                        ⚽ Matanza Tenerife FS
                    </h1>

                    <p className="lead mb-4">
                        Bienvenido a la plataforma oficial del equipo.
                        Gestiona jugadores y equipaciones.
                    </p>

                    <div className="d-flex justify-content-center gap-3 mt-4">
                        <Link to="/login" className="btn btn-outline-primary btn-lg">
                            Iniciar sesión
                        </Link>
                    </div>

                </div>
            </div>
        </div>
    );
}

export default Landing;