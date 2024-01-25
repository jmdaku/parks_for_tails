import React, { useState } from 'react';
import { MDBContainer, MDBInput, MDBBtn, MDBIcon } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/api/users/login', { username, password });
  
      if (response.status === 200) {
        // Login successful
        navigate('/profile');
      } else {
        // Login failed
        alert('Login failed. Please check your credentials.');
      }
    } catch (error) {
      console.error('Error during login:', error);
      alert('An error occurred during login. Please try again.');
    }
  };
  return (
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
        <p>or sign up with:</p>

        <div className="d-flex justify-content-between mx-auto" style={{ width: '40%' }}>
          {/* Social media login buttons */}
          <MDBBtn tag="a" color="none" className="m-1" style={{ color: '#1266f1' }}>
            <MDBIcon fab icon="facebook-f" size="sm" />
          </MDBBtn>

          <MDBBtn tag="a" color="none" className="m-1" style={{ color: '#1266f1' }}>
            <MDBIcon fab icon="twitter" size="sm" />
          </MDBBtn>

          <MDBBtn tag="a" color="none" className="m-1" style={{ color: '#1266f1' }}>
            <MDBIcon fab icon="google" size="sm" />
          </MDBBtn>

          <MDBBtn tag="a" color="none" className="m-1" style={{ color: '#1266f1' }}>
            <MDBIcon fab icon="github" size="sm" />
          </MDBBtn>
        </div>
      </div>
    </MDBContainer>
  );
};

export default Login;
