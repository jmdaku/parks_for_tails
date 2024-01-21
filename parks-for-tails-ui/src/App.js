import './App.css';
import React from 'react';
import { useState, useEffect } from 'react'

import ReactDOM from 'react-dom/client'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
// import { Login } from './components/Login.js';
// import { Register } from './components/Register.js';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';


function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('userId') !== null);  
  //const [courses, setCourses] = useState([]);
  
  //Absolutely not sure if this is the best or most efficient way to do this, but it's what I've got figured out for now! -Sprinkles
  const updateApp = async () => {
      try {
        const response = await fetch(`http://localhost:8080/home`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
          },
        });
    
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
    
        const data = await response.json();
        //setCourses(data);
      } catch (error) {
        console.error('Error:', error);
      }
    };


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


