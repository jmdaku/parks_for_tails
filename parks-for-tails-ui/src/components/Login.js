import React, { useState } from 'react';
import { MDBContainer, MDBInput, MDBBtn } from 'mdb-react-ui-kit';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import parksfortailssm from './parksfortailssm.png';
import petconnectsm from './petconnectsm.png';
import axios from 'axios';

const Login = () => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [id, setId] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/api/users/login', { username, password });
      

      if (response.status === 200) {
        // Login successful
        const id = response.data.id;
        setLoggedIn(true);
        navigate(`/profile/${id}/${username}`);
        
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
    <div className='App'>
      <MDBContainer className="p-3 my-5 d-flex flex-column w-20">
      <img src={petconnectsm} alt="Pet Connect logo" width={800} />

        <div className="d-flex justify-content-between mx-auto" style={{ width: '40%' }}>
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

          </div>
            <div className="text-center">
              <div className="d-flex justify-content-between mx-auto" style={{ width: '40%' }}>
                <p>
                  Not a member? <Link to="/register">Register here</Link>
                </p>
              </div> 
          </div>
    </MDBContainer>
    </div>
  );
};

export default Login;
