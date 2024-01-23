import React from 'react';
import parksfortailssm from './parksfortailssm.png';
import {  useState, useContext } from "react";
import { Link, useNavigate } from 'react-router-dom';
import { LoginContext } from './checkLogin.jsx';
import {
  MDBBtn,
  MDBContainer,
  MDBCard,
  MDBCardBody,
  MDBInput,
  }
from 'mdb-react-ui-kit';

export default function Register() {
  const { isLoggedIn, setIsLoggedIn } = useContext(LoginContext);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [verifyPassword, setVerifyPassword] = useState('');

  const navigate = useNavigate();

  const handleRegister = async (event) => {
    event.preventDefault(); 
    if (password !== verifyPassword) {
      alert("Passwords do not match");
      //return;
    }

    try {
      const response = await fetch('/api/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          //'Access-Control-Allow-Origin': '*'
          //'http://localhost:8080/register',

        },
        
        body: JSON.stringify({ username, password, verifyPassword }),
        
      });

      console.log(response);
      const data = await response.json();
      //console.log('Response Body:', data);
      console.log('Response Body:', response);


      //currently returning null for data.userId etc- Sprinkles // check this
      if (data.success) {
        localStorage.setItem("userId", data.userId); 
          navigate('/home'); 
          setIsLoggedIn(true);
        
      } else {
        alert(data.message); 
      }
    } catch (error) {
      console.log(error);
    }
  };


  return (
    <div className='App'>
    <img src={parksfortailssm} alt="logos" width={1000} height={300} />
    <MDBContainer fluid className='d-flex align-items-center justify-content-center bg-image'>
      <div className='mask gradient-custom-3'></div>
      <MDBCard className='m-5' style={{maxWidth: '600px'}}>
        <MDBCardBody className='px-5'>
          <h2 className="text-uppercase text-center mb-5">Create an account</h2>
          <form onSubmit={handleRegister}>
          <MDBInput wrapperClass='mb-4' label='Username' size='lg' id='form1' type='text'/>
          <MDBInput wrapperClass='mb-4' label='Password' size='lg' id='form3' type='password'/>
          <MDBInput wrapperClass='mb-4' label='Re-enter password' size='lg' id='form4' type='password'/>
          
          <MDBBtn className='mb-4 w-100 gradient-custom-4' size='lg' type="submit" onClick={handleRegister}>Register</MDBBtn>
          </form>
        </MDBCardBody>
      </MDBCard>
    </MDBContainer>
    </div>
  );
}
