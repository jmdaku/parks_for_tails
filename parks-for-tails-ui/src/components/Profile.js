import React, { useState, useEffect, useContext } from 'react';
import parksfortailssm from './parksfortailssm.png';
import { LoginContext } from './checkLogin.jsx';
import { Link, useNavigate } from 'react-router-dom';
import { MDBCol, MDBContainer, MDBRow, MDBCard, MDBCardText, MDBCardBody, MDBCardImage, MDBBtn, MDBTypography } from 'mdb-react-ui-kit';

export default function Profile() {
    // State variables
    const [loading, setLoading] = useState(true);
    const [editingPassword, setEditingPassword] = useState(false);
    const [userToDelete, setUserToDelete] = useState(null);
    const user = parseInt(localStorage.getItem('userId'));
    const { isLoggedIn, setIsLoggedIn } = useContext(LoginContext);
    const navigate = useNavigate();
    const data = { 'userId': user };

    const handleSearchClick = () => {
        console.log('Search button clicked'); // Log message for debugging
        // Redirect to the search page
        window.location.href = '/search'; // Assuming '/search' is the route for the search page
    };

    useEffect(() => {
        console.log('isLoggedIn:', isLoggedIn); // Log message for debugging
    }, [isLoggedIn]);

    return (
        <div className="gradient-custom-2" style={{ backgroundColor: '#9DE2FF' }}>
            <MDBContainer className="py-5 h-100">
                <MDBRow className="justify-content-center align-items-center h-100">
                    <MDBCol lg="9" xl="7">
                        <MDBCard>
                            <div className="rounded-top text-white d-flex flex-row" style={{ backgroundColor: '#000', height: '200px' }}>
                                <div className="ms-4 mt-5 d-flex flex-column" style={{ width: '150px' }}>
                                </div>
                                <div className="ms-3" style={{ marginTop: '130px' }}>
                                    <MDBTypography tag="h1">Username</MDBTypography>
                                </div>
                            </div>
                            <div className="p-4 text-black" style={{ backgroundColor: '#F8F9FA' }}>
                            </div>
                            <MDBCardBody className="text-black p-4">
                                <div className="d-flex justify-content-between align-items-center mb-4">
                                    <MDBCardText className="lead fw-normal mb-0">Favorite Parks</MDBCardText>
                                    <MDBCardText className="mb-0"><a href="#!" className="text-muted">Show all</a></MDBCardText>
                                </div>
                                <MDBRow>
                                    <MDBCol className="mb-2">
                                        <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(112).webp"
                                            alt="image 1" className="w-100 rounded-3" />
                                    </MDBCol>
                                    <MDBCol className="mb-2">
                                        <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(107).webp"
                                            alt="image 1" className="w-100 rounded-3" />
                                    </MDBCol>
                                </MDBRow>
                                <MDBRow className="g-2">
                                    <MDBCol className="mb-2">
                                        <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(108).webp"
                                            alt="image 1" className="w-100 rounded-3" />
                                    </MDBCol>
                                    <MDBCol className="mb-2">
                                        <MDBCardImage src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(114).webp"
                                            alt="image 1" className="w-100 rounded-3" />
                                    </MDBCol>
                                </MDBRow>
                                {/* Search Parks Button */}
                                {isLoggedIn && (
                                    <MDBBtn onClick={handleSearchClick}>Search Parks</MDBBtn>
                                )}
                            </MDBCardBody>
                        </MDBCard>
                    </MDBCol>
                </MDBRow>
            </MDBContainer>
        </div>
    )
}
