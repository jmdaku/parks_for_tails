import React from 'react';
import { useLocation } from 'react-router-dom';
import ParkInfo from './ParkInfo'; // Import the ParkInfo component

const ParkPage = () => {
    const location = useLocation();
    const selectedPark = location.state.park;

    return (
        <div>
            <h1>Park Page</h1>
            {selectedPark ? (
                <div>
                    {/* Render ParkInfo component to display park details */}
                    <ParkInfo selectedPark={selectedPark} />
                    {/* Add a button or trigger for directions */}
                    <button onClick={() => console.log('Show directions')}>Get Directions</button>
                </div>
            ) : (
                <p>No park selected</p>
            )}
        </div>
    );
};

export default ParkPage;
