import React from 'react';
import parksfortailssm from './parksfortailssm.png';
import {  useState, useContext } from "react";
import { Link, useNavigate } from 'react-router-dom';
import { LoginContext } from './checkLogin.jsx';
import {Login } from './Login.js';
import axios from 'axios';
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
      return;
    }

    try {
      const response = await axios.post('http://localhost:8080/api/users/register', {
        username,
        password,
        verifyPassword,
      });

      console.log('Response Body:', response.data);

      if (response.data.success) {
        localStorage.setItem("userId", response.data.userId);
        navigate('/home');
        setIsLoggedIn(true);

         // Redirect to the login page
        navigate('/login');
      } else {
        alert(response.data.message);
      }
    } catch (error) {
      console.error('Error:', error);

      alert('An error occurred during registration. Please try again.');
    }
  };


  return (
    <div className='App'>
      <img src={parksfortailssm} alt="logos" width={1000} height={300} />
      <MDBContainer fluid className='d-flex align-items-center justify-content-center bg-image'>
        <div className='mask gradient-custom-3'></div>
        <MDBCard className='m-5' style={{ maxWidth: '600px' }}>
          <MDBCardBody className='px-5'>
            <h2 className="text-uppercase text-center mb-5">Create an account</h2>
            <form onSubmit={handleRegister}>
              <MDBInput
                wrapperClass='mb-4'
                label='Username'
                size='lg'
                id='form1'
                type='text'
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
              <MDBInput
                wrapperClass='mb-4'
                label='Password'
                size='lg'
                id='form3'
                type='password'
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
              <MDBInput
                wrapperClass='mb-4'
                label='Re-enter password'
                size='lg'
                id='form4'
                type='password'
                value={verifyPassword}
                onChange={(e) => setVerifyPassword(e.target.value)}
              />

              <MDBBtn className='mb-4 w-100 gradient-custom-4' size='lg' type="submit">Register</MDBBtn>
            </form>
          </MDBCardBody>
        </MDBCard>
      </MDBContainer>
    </div>
  );
}
