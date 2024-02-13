import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate hook

const SearchPage = () => {
    // State to hold the search location
    const [searchLocation, setSearchLocation] = useState('Dog Parks');
    // State to hold search results
    const [searchResults, setSearchResults] = useState([]);
    const navigate = useNavigate(); // Access the navigate function

    // Function to handle clicks on search results and navigate to ParkPage
    const handleParkClick = (park) => {
        console.log('Park clicked:', park); // Log the clicked park object
        navigate('/park', { state: { park } }); // Pass park details to ParkPage
    };

    // Function to search for parks using your Spring Boot backend
    const searchParks = (location) => {
        // Update the URL to point to your Spring Boot backend
        const backendUrl = 'http://localhost:8080'; // Change this URL if your backend is hosted elsewhere

        // Construct the URL for the backend endpoint
        const url = `${backendUrl}/searchParks?query=${encodeURIComponent(location)}&apiKey=AIzaSyCVKpvs8veumFKcba1Cb75fb6nljJkRyU0`;

        // Make a request to the backend
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Handle the data returned from the backend
                console.log('Search results:', data);
                // Update search results state
                setSearchResults(data.results);

                // Update map with search results
                updateMap(data.results);
            })
            .catch(error => {
                console.error('Error fetching parks:', error);
                // Handle errors, such as displaying an error message to the user
            });
    };

    useEffect(() => {
        // Automatically perform the search for dog parks when the component mounts
        searchParks('Dog Parks');
    }, []);

    // Function to update the map with search results
    const updateMap = (results) => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                position => {
                    const userLocation = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude
                    };
    
                    // Access the Google Maps API
                    const map = new window.google.maps.Map(document.getElementById('map'), {
                        center: userLocation,
                        zoom: 8
                    });
    
                    // Add marker for user's current location
                    const userMarker = new window.google.maps.Marker({
                        position: userLocation,
                        map: map,
                        icon: {
                            url: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png' // Blue dot icon
                        },
                        title: 'Your Location'
                    });
    
                    // Add markers for search results
                    results.forEach(result => {
                        const marker = new window.google.maps.Marker({
                            position: {
                                lat: result.geometry.location.lat,
                                lng: result.geometry.location.lng
                            },
                            map: map,
                            title: result.name // Add park name as marker title
                            // You can customize marker icons, animations, and other properties here
                        });
    
                        // Add click listener to marker to handle ParkPage navigation
                        marker.addListener('click', () => {
                            handleParkClick(result);
                        });
                    });
                },
                error => {
                    console.error('Error getting user location:', error);
                }
            );
        } else {
            console.error('Geolocation is not supported by your browser.');
        }
    };

    return (
        <div>
            <h1>Search Parks</h1>
            <form onSubmit={(event) => { event.preventDefault(); }}>
                <div>
                    <label htmlFor="location">Location:</label>
                    <input
                        type="text"
                        id="location"
                        value={searchLocation}
                        onChange={(event) => setSearchLocation(event.target.value)}
                        placeholder="Enter park name or location"
                        disabled // Disable the input field
                    />
                </div>
                {/* Button to trigger automatic search */}
                <button type="submit" onClick={() => searchParks(searchLocation)}>Search</button>
            </form>
            {/* Display search results */}
            <div>
                <h2>Search Results</h2>
                <ul>
                    {searchResults.map((result, index) => (
                        <li key={index} onClick={() => handleParkClick(result)}>
                            {result.name}
                        </li>
                    ))}
                </ul>
            </div>
            <div id="map" style={{ width: '100%', height: '400px' }}></div>
        </div>
    );
};

export default SearchPage;
