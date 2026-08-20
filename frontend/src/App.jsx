import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import Header from "./components/header/header.component";
import Landing from './components/landing/landing.component.jsx';
import Login from './components/login/login.component.jsx';
import Dashboard from './components/dashboard/dashboard.component.jsx';
import Equipacion from './components/equipacion/equipacion.component.jsx';
import CreateEquipacion from './components/equipacion/crearEquipacion/createEquipacion.component.jsx';
import DetallesEquipacion from './components/equipacion/detallesEquipacion/detalles.component.jsx';
import UpdateEquipacion from './components/equipacion/actualizarEquipacion/updateEquipacion.component.jsx';

import Jugador from "./components/jugador/jugador.component.jsx";

function Home() {
  return (
    <>
      <Header />
      <Landing />
    </>
  )
}

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />

        <Route path="/home" element={<Dashboard />} />

        <Route path="/equipaciones" element={<Equipacion />} />

        <Route path="/equipaciones/create" element={<CreateEquipacion />} />
        <Route path="/equipaciones/:id" element={<DetallesEquipacion />} />
        <Route path="/equipaciones/update/:id" element={<UpdateEquipacion />} />

        <Route path="/jugadores" element={<Jugador />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
