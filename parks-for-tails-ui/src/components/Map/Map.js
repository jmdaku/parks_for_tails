import React, { Component } from 'react';
import './Map.css'
import Axios from 'axios';

// Define the Map component
class Map extends Component {
    // Constructor method to initialize the component's state
    constructor() {
        super();

        // Initialize state with initial values
        this.state = {
            isMarkerShown: false,
            latLong: { lat: 10, lng: -10 },
            name: false
        };

        // Bind the initMap method to the component instance
        this.initMap = this.initMap.bind(this);
    }

    // componentDidUpdate lifecycle method is invoked immediately after updating occurs
    componentDidUpdate() {
        // Check if the location name is not set
        if (!this.state.name) {
            // Fetch geocode data from the Google Maps Geocoding API based on the provided name
            Axios.get(`https://maps.googleapis.com/maps/api/geocode/json?address=${this.props.name}&key=`)
                .then(res => {
                    // Update state with the retrieved latitude and longitude
                    this.setState({
                        latLong: res.data.results[0].geometry.location,
                        name: true
                    });
                })
                .then(() => {
                    // Call the renderMap method after setting the state
                    this.renderMap();
                });
        }
    }

    // renderMap method to dynamically load Google Maps script and initialize the map
    renderMap = () => {
        // Load the Google Maps script asynchronously
        loadScript('https://maps.googleapis.com/maps/api/js?key=YOUR_GOOGLE_MAPS_API_KEY&callback=initMap');
        // Set the initMap method as a callback for when the script is loaded
        window.initMap = this.initMap;
    }

    // initMap method to initialize the Google Map with a marker
    initMap = () => {
        // Create a new Google Map instance
        // let map = new window.google.maps.Map(document.getElementById('map'), {
        //     center: this.state.latLong,
        //     zoom: 14,
        // });

        // Create a marker on the map
        // let marker = new window.google.maps.Marker({
        //     position: this.state.latLong,
        //     map: map,
        //     title: 'Hello World!'
        // });
    }

    // Render method to define the structure of the component
    render() {
        return (
            <div id="map"></div>
        );
    }
}

// loadScript function to dynamically load a script in the document head
function loadScript(url) {
    var index = window.document.getElementsByTagName("script")[0];
    var script = window.document.createElement("script");
    script.src = url;
    script.async = true;
    script.defer = true;
    index.parentNode.insertBefore(script, index);
}

// Export the Map component as the default export
export default Map;
