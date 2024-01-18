import '../App.css';
import React from 'react';
import { Link } from 'react-router-dom';
import parksfortailssm from './parksfortailssm.png';
import Login from './Login.js';
import Register from './Register.js';

export default function Home() {
  return (
    <div className='App'>
      <img src={parksfortailssm} alt="logo" width={1400} height={500} />
      <div>
        <Link to="/login">Login</Link>
        <p>
          Not a member? <Link to="/register">Register here</Link>
        </p>
      </div>
    </div>
  );
}
