import React from 'react';
import parksfortailssm from './parksfortailssm.png';
import { Link } from 'react-router-dom';
import Register from './Register';
import {
  MDBContainer,
  MDBInput,
  MDBBtn,
  MDBIcon
}
from 'mdb-react-ui-kit';

export default function Login() {
  return (
    <div className='App'>
      <img src={parksfortailssm} alt="logos" width={1000} height={300} />
      
      <MDBContainer className="p-3 my-5 d-flex flex-column w-50">
        <MDBInput wrapperClass='mb-4' label='Username' id='form1' type='text'/>
        <MDBInput wrapperClass='mb-4' label='Password' id='form2' type='password'/>

        <MDBBtn className="mb-4">Sign in</MDBBtn>

        <div className="text-center">
          Not a member? <Link to="/register">Register here</Link>
                  
          </div>
        
      </MDBContainer>
    </div>
  );
}
