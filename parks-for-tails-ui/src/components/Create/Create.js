// Import necessary dependencies
import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import axios from 'axios';
import jwtDecode from 'jwt-decode';

// Define the API URL for parks
const url = 'https://dog-park-finder.herokuapp.com/parks';

// Define the Create component as a class
class Create extends Component {
    constructor() {
        super();

        // Initialize the component state with initial values
        this.state = {
            name: null,
            location: null,
            size: null,
            sizeOptions: [
                { name: 'Small' },
                { name: 'Medium' },
                { name: 'Large' },
            ],
            bathrooms: null,
            bathroomOptions: [
                { short: 'true', name: 'Yes' },
                { short: 'false', name: 'No' },
            ],
            parking: null,
            parkingOptions: [
                { short: 'true', name: 'Yes' },
                { short: 'false', name: 'No' },
            ],
            misc: null,
            author: null,
            redirect: false,
        };
    }

    // Handle input change for the park name
    handleNameInput(e) {
        this.setState({
            name: e.target.value,
        });
    }

    // Handle input change for the park location
    handleLocationInput(e) {
        this.setState({
            location: e.target.value,
        });
    }

    // Handle input change for the park size
    handleSizeInput(e) {
        this.setState({
            size: e.target.value,
        });
    }

    // Handle input change for the park bathroom availability
    handleBathroomInput(e) {
        this.setState({
            bathrooms: e.target.value,
        });
    }

    // Handle input change for the park parking availability
    handleParkingInput(e) {
        this.setState({
            parking: e.target.value,
        });
    }

    // Handle input change for additional comments
    handleCommentsInput(e) {
        this.setState({
            misc: e.target.value,
        });
    }

    // Handle form submission to create a new park
    handleSearchSubmit(e) {
        e.preventDefault();
        // Make a POST request to create a new park
        axios.post(url, { ...this.state })
            .then(() => {
                // Update the state to trigger a redirect
                this.setState({
                    redirect: true,
                });
            })
            .catch((err) => {
                console.log(err);
            });
    }

    // componentDidMount lifecycle method
    componentDidMount() {
        // Decode the JWT token to get user information
        let decoded = jwtDecode(localStorage.token);

        // Update the state with the user's email as the author
        this.setState({
            author: decoded.email,
        });
    }

    // Helper method to render a redirect component
    redirect() {
        return <Redirect to="/" />;
    }

    // Render method to define the structure of the component
    render() {
        // Map through size options to create option elements
        let sizeOptions = this.state.sizeOptions.map((size, index) => {
            return (
                <option key={index + 1}>{size.name}</option>
            );
        });
        sizeOptions.unshift(
            <option key="0">Please Select a Size</option>
        );

        // Map through bathroom options to create option elements
        let bathroomOptions = this.state.bathroomOptions.map((bathroom, index) => {
            return (
                <option key={index + 1} value={bathroom.short}>{bathroom.name}</option>
            );
        });
        bathroomOptions.unshift(
            <option key="0">Please Select Yes or No</option>
        );

        // Map through parking options to create option elements
        let parkingOptions = this.state.parkingOptions.map((parking, index) => {
            return (
                <option key={index + 1} value={parking.short}>{parking.name}</option>
            );
        });
        parkingOptions.unshift(
            <option key="0">Please Select Yes or No</option>
        );

        // Render the component with the form for creating a new park
        return (
            <div className="new-park">
                <form className="new-park-form" onSubmit={(e) => this.handleSearchSubmit(e)}>
                    <h3>Please enter all fields to let the community know about a Dog Park you'd like to share!</h3>
                    <p>
                        <label>Name:</label>
                        <textarea onChange={(e) => this.handleNameInput(e)}></textarea>
                    </p>
                    <p>
                        <label>Location: </label>
                        <textarea onChange={(e) => this.handleLocationInput(e)}>
                        </textarea>
                    </p>
                    <p>
                        <label>Size: </label>
                        <select onChange={(e) => this.handleSizeInput(e)}>
                            {sizeOptions}
                        </select>
                    </p>
                    <p>
                        <label>Bathroom: </label>
                        <select onChange={(e) => this.handleBathroomInput(e)}>
                            {bathroomOptions}
                        </select>
                    </p>
                    <p>
                        <label>Parking: </label>
                        <select onChange={(e) => this.handleParkingInput(e)}>
                            {parkingOptions}
                        </select>
                    </p>
                    <p>
                        <label>Comments: </label>
                        <textarea onChange={(e) => this.handleCommentsInput(e)}>
                        </textarea>
                    </p>
                    <input type="submit" value="Submit" />
                </form>
                {this.state.redirect ? this.redirect() : null}
            </div>
        );
    }
}

// Export the Create component as the default export
export default Create;
