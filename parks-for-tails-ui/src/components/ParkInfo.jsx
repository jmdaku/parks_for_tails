import React, { useEffect, useState } from 'react';

const ParkInfo = ({ selectedPark }) => {
    const [directions, setDirections] = useState(null);

    useEffect(() => {
        // Fetch directions automatically when the component is rendered
        fetchDirections();
    }, [selectedPark]); // Fetch directions whenever the selected park changes

    const fetchDirections = () => {
        const userLocation = 'current_location';
        const url = `/directions?origin=${userLocation}&destination=${selectedPark.name}&apiKey=AIzaSyCVKpvs8veumFKcba1Cb75fb6nljJkRyU0`;

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Network response was not ok, status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                // Handle the directions data
                console.log('Directions:', data);
                setDirections(data);
            })
            .catch(error => {
                // Handle errors
                console.error('Error fetching directions:', error);
                
                // Log detailed information about the error
                console.log('Error status:', error.status);
                console.log('Error message:', error.message);

                // Log the entire response content if available
                error.response && error.response.text().then(text => console.log('Error response:', text));

                // Handle errors gracefully, e.g., display a message to the user
            });
    };

    return (
        <div>
            {/* Render park information */}
            <h2>{selectedPark.name}</h2>
            <p>Address: {selectedPark.formatted_address}</p>
            <p>Business Status: {selectedPark.business_status}</p>
            {/* Render directions if available */}
            {directions && (
                <div>
                    <h3>Directions:</h3>
                    {/* Render directions data here */}
                </div>
            )}
        </div>
    );
};

export default ParkInfo;
