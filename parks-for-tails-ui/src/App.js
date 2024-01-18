import './App.css';
import React from 'react';
//import ReactDOM from 'react-dom';
import ReactDOM from 'react-dom/client'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
// import { Login } from './components/Login.js';
// import { Register } from './components/Register.js';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </Router>
  );
}

ReactDOM.createRoot(document.getElementById('root')).render(<App />);
// const rootContainer = document.getElementById('root');
// const root = ReactDOM.createRoot(rootContainer);
// root.render(<App />);

export default App;


