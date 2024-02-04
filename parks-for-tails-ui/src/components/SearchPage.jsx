import React, { useState, useEffect } from 'react';

const SearchPage = () => {
    // State to hold the search location
    const [searchLocation, setSearchLocation] = useState('');
    // State to hold search results
    const [searchResults, setSearchResults] = useState([]);

    // Function to handle location input change
    const handleLocationChange = (event) => {
        setSearchLocation(event.target.value);
    };

    // Function to handle form submission (search)
    const handleFormSubmit = (event) => {
        event.preventDefault();
        // Perform search functionality using your Spring Boot backend
        searchParks(searchLocation);
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
            })
            .catch(error => {
                console.error('Error fetching parks:', error);
                // Handle errors, such as displaying an error message to the user
            });
    };

     // Initialize the Google Maps API
    useEffect(() => {
        // Access the Google Maps API
        const map = new window.google.maps.Map(document.getElementById('map'), {
            center: { lat: -34.397, lng: 150.644 },
            zoom: 8
        });

        // Additional Google Maps API functionalities can be used here
    }, []);

    return (
        <div>
            <h1>Search Parks</h1>
            <form onSubmit={handleFormSubmit}>
                <div>
                    <label htmlFor="location">Location:</label>
                    <input
                        type="text"
                        id="location"
                        value={searchLocation}
                        onChange={handleLocationChange}
                        placeholder="Enter park name or location"
                    />
                </div>
                <button type="submit">Search</button>
            </form>
            {/* Display search results */}
            <div>
                <h2>Search Results</h2>
                <ul>
                    {searchResults.map((result, index) => (
                        <li key={index}>{result.name}</li>
                    ))}
                </ul>
            </div>
            <div id="map" style={{ width: '100%', height: '400px' }}></div>
        </div>
    );
};

export default SearchPage;
