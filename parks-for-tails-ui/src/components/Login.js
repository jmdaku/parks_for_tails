import React from 'react';
import parksfortailssm from './parksfortailssm.png';
import {  useState, useContext } from "react";
import { Link, useNavigate } from 'react-router-dom';
import { LoginContext } from './checkLogin.jsx';
import axios from 'axios';
import {
  MDBContainer,
  MDBInput,
  MDBBtn,
  }
from 'mdb-react-ui-kit';

export default function Login() {

  const { isLoggedIn, setIsLoggedIn } = useContext(LoginContext);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [verifyPassword, setVerifyPassword] = useState('');

  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();
    // if (password !== verifyPassword) {
    //   alert("Passwords do not match");
    //   return;
    // }

    try {
      const response = await axios.post('http://localhost:8080/api/users/login', {
        username,
        password,
      });

      console.log('Response Body:', response.data);

      if (response.data.success) {
        localStorage.setItem("userId", response.data.userId);
        navigate('/home');
        setIsLoggedIn(true);

         // Redirect to the profile page
        navigate('/profile');
      } else {
        alert(response.data.message);
      }
    } catch (error) {
      console.error('Error:', error);

      alert('An error occurred during login. Please try again.');
    }
  };

  return (
    <div className='App'>
      <img src={parksfortailssm} alt="logos" width={1000} height={300} />
      
      <MDBContainer className="p-3 my-5 d-flex flex-column w-50">
        <form onSubmit={handleLogin}>
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
  
          <MDBBtn type="submit" className="mb-4">Sign in</MDBBtn>
        </form>
  
        <div className="text-center">
          Not a member? <Link to="/register">Register here</Link>
        </div>
      </MDBContainer>
    </div>
  );
  
}
