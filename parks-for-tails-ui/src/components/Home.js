import '../App.css';
import React from 'react';
import { Link } from 'react-router-dom';
import parksfortailssm from './parksfortailssm.png';


export default function Home() {
  return (
    <div className='App'>
      <img src={parksfortailssm} alt="logo" width={1400} height={500} />
      <div>
        <p>
        <Link to="/login">Login</Link>
        </p>
        <p>
          Not a member? <Link to="/register">Register here</Link>
        </p>
        <p>
          Need to add a park? <Link to="/addpark">Add a park</Link>
        </p>

      </div>
    </div>
  );
}
