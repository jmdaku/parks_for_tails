import React, { useState } from 'react';
import { MDBContainer, MDBInput, MDBBtn, MDBIcon } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';
import parksfortailssm from './parksfortailssm.png';
import axios from 'axios';

const Login = () => {
  // State hooks
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  // Login handler function
  const handleLogin = async (event) => {
    event.preventDefault();

    try {
      // Sending a POST request to login
      const response = await axios.post('http://localhost:8080/api/users/login', { username, password });
  
      if (response.status === 200) {
        // Login successful, navigate to the profile page
        navigate('/profile');
      } else {
        // Login failed
        alert('Login failed. Please check your credentials.');
      }
    } catch (error) {
      // Error handling during login
      console.error('Error during login:', error);
      alert('An error occurred during login. Please try again.');
    }
  };
  // UI rendering
  return (
    <div className='App'>
    <img src={parksfortailssm} alt="logos" width={1000} height={300} />
    <MDBContainer className="p-3 my-5 d-flex flex-column w-50">
      <form onSubmit={handleLogin}>
        <MDBInput
          wrapperClass="mb-4"
          label="Username"
          id="form1"
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <MDBInput
          wrapperClass="mb-4"
          label="Password"
          id="form2"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <MDBBtn className="mb-4" size="lg" type="submit">
          Sign in
        </MDBBtn>
      </form>
      
      <div className="text-center">
        <p>
          Not a member? <a href="/register">Register</a>
        </p>
                
        <div className="d-flex justify-content-between mx-auto" style={{ width: '40%' }}>
                  </div>
      </div>
    </MDBContainer>
    </div>
  );
};

export default Login;
