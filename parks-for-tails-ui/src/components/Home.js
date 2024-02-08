import '../App.css';
import React from 'react';
import { Link } from 'react-router-dom';
import parksfortailssm from './parksfortailssm.png';
import petconnectsm from './petconnectsm.png';
import { MDBContainer } from 'mdb-react-ui-kit';

export default function Home() {
  return (
    <div className='App'>
      <MDBContainer className="p-3 my-5 d-flex flex-column w-40">
      <img src={petconnectsm} alt="Pet Connect logo" width={1000} />
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
      </MDBContainer>
    </div>
  );
}
