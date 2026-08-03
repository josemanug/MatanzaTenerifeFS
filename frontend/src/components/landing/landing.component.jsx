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

                    <section className="row text-center mt-5">

                <div className="col-md-4 mb-4">
                    <div className="card shadow-sm h-100">
                        <div className="card-body">
                            <h2 className="card-title">
                                Equipaciones
                            </h2>

                            <p className="card-text">
                                Gestiona la distribución de equipaciones para los jugadores del equipo, asegurando que todos tengan el uniforme adecuado.
                            </p>
                        </div>
                    </div>
                </div>

                <div className="col-md-4 mb-4">
                    <div className="card shadow-sm h-100">
                        <div className="card-body">
                            <h2 className="card-title">
                                Jugadores
                            </h2>

                            <p className="card-text">
                                Gestiona la información de los jugadores del equipo, incluyendo sus datos personales, equipaciones asignadas y categorías.
                            </p>
                        </div>
                    </div>
                </div>
            </section>

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