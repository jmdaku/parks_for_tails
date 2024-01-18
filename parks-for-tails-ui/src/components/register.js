import React from 'react';
import parksfortailssm from './parksfortailssm.png';
import parkbackgroundimage from './mike-benna-SBiVq9eWEtQ-unsplash.jpg';
import {
  MDBBtn,
  MDBContainer,
  MDBCard,
  MDBCardBody,
  MDBInput,
  }
from 'mdb-react-ui-kit';

export default function Register() {
  return (
    <div className='App'>
    <img src={parksfortailssm} alt="logos" width={1000} height={300} />
    <MDBContainer fluid className='d-flex align-items-center justify-content-center bg-image' img src={parkbackgroundimage}>
      <div className='mask gradient-custom-3'></div>
      <MDBCard className='m-5' style={{maxWidth: '600px'}}>
        <MDBCardBody className='px-5'>
          <h2 className="text-uppercase text-center mb-5">Create an account</h2>
          <MDBInput wrapperClass='mb-4' label='Username' size='lg' id='form1' type='text'/>
          <MDBInput wrapperClass='mb-4' label='Password' size='lg' id='form3' type='password'/>
          <MDBInput wrapperClass='mb-4' label='Re-enter password' size='lg' id='form4' type='password'/>
          
          <MDBBtn className='mb-4 w-100 gradient-custom-4' size='lg'>Register</MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBContainer>
    </div>
  );
}
