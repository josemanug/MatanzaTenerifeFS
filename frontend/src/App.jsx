import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css';
import Header from "./components/header/header.component";
import Landing from './components/landing/landing.component.jsx';
import Login from './components/login/login.component.jsx';
import Dashboard from './components/dashboard/dashboard.component.jsx';
import Equipacion from './components/equipacion/equipacion.component.jsx';
import CreateEquipacion from './components/equipacion/crearEquipacion/createEquipacion.component.jsx';

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
      </Routes>
    </BrowserRouter>
  )
}

export default App
