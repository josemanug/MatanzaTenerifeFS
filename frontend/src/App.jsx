import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css'
import Landing from './components/landing/landing.component.jsx';
import Login from './components/login/login.component.jsx';

function Home() {
  return (
    <>
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
      </Routes>
    </BrowserRouter>
  )
}

export default App
