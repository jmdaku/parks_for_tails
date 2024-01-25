import React from 'react';
import parksfortailssm from './parksfortailssm.png';
import {  useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { LoginContext } from './CheckLogin.jsx';
import Login from './Login.jsx';
import axios from 'axios';
import {
  MDBBtn,
  MDBContainer,
  MDBCard,
  MDBCardBody,
  MDBInput,
  }
from 'mdb-react-ui-kit';

export default function AddPark() {
    const [parkName, setParkName] = useState('');
    const [parkAddress, setParkAddress] = useState('');
    const [parkLatitude, setParkLatitude] = useState('');
    const [parkLongitude, setParkLongitude] = useState('');
    const navigate = useNavigate();
  const handleRegister = async (event) => {
    event.preventDefault();
    
    try {
      console.log('Before park form API call');
      const response = await axios.post('http://localhost:8080/api/users/addpark', {
        parkName,
        parkAddress,
        parkLatitude,
        parkLongitude,
      });
      console.log('After park form API call');
      console.log('Response Body:', response.data);
      console.log('Response Status:', response.status); // Log status property
      if (response.status === 201) {
        // Registration successful
        console.log('Registration successful');
        localStorage.setItem("parkId", response.data.parkId);
        // Redirect to the home page
        console.log('Before navigation to /home');
        navigate('/home');
        console.log('After navigation to /home');
      } else {
        // Adding park failed
        console.log('Park not added');
        alert(response.data.message);
      }
    } catch (error) {
      console.error('Error:', error);
      alert('An error occurred during adding this park. Please try again.');
    }
  };

  return (
    <div className='App'>
      <img src={parksfortailssm} alt="logos" width={1000} height={300} />
      <MDBContainer fluid className='d-flex align-items-center justify-content-center bg-image'>
        <div className='mask gradient-custom-3'></div>
        <MDBCard className='m-5' style={{ maxWidth: '600px' }}>
          <MDBCardBody className='px-5'>
            <h2 className="text-uppercase text-center mb-5">Add A Park</h2>
            <form onSubmit={handleRegister}>
              <MDBInput
                wrapperClass='mb-4'
                label='Name'
                size='lg'
                id='form1'
                type='text'
                value={parkName}
                onChange={(e) => setParkName(e.target.value)}
              />
              <MDBInput
                wrapperClass='mb-4'
                label='Address'
                size='lg'
                id='form1'
                type='text'
                value={parkAddress}
                onChange={(e) => setParkAddress(e.target.value)}
              />
              <MDBInput
                wrapperClass='mb-4'
                label='Latitude'
                size='lg'
                id='form1'
                type='number'
                value={parkLatitude}
                onChange={(e) => setParkLatitude(e.target.value)}
              />
              <MDBInput
                wrapperClass='mb-4'
                label='Longitude'
                size='lg'
                id='form1'
                type='number'
                value={parkLongitude}
                onChange={(e) => setParkLongitude(e.target.value)}
              />

              <MDBBtn className='mb-4 w-100 gradient-custom-4' size='lg' type="submit">Add Park</MDBBtn>
            </form>
          </MDBCardBody>
        </MDBCard>
      </MDBContainer>
    </div>
  );
}