// Import necessary dependencies
import React, { Component } from "react";
import { Redirect } from 'react-router-dom'
import axios from "axios";
import './SignIn.css';

// Define the SignIn component as a class
class SignIn extends Component {

    // Function to render a Redirect component
    redirect() {
        return <Redirect to="/" />
    }

    // componentDidMount lifecycle method
    componentDidMount() {
        console.log('hello1');
        // Example API call to Google Maps Geocoding API
        axios.get("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=")
            .then(res => {
                console.log('hello');
                console.log(res);
            })
            .catch(err => console.error(err));
    }

    // Render method to define the structure of the component
    render() {
        return (
            <div className="SignIn">
                {/* Display heading */}
                <h1>Log In</h1>
                {/* Form container with a form */}
                <div className="form-container">
                    <form onSubmit={this.props.loginSubmit}>
                        {/* Email input field */}
                        <p>
                            <label>Email:</label>
                            <input className="loginInput" name="email" type="text" onChange={this.props.inputChanger} />
                        </p>
                        {/* Password input field */}
                        <p>
                            <label>Password:</label>
                            <input className="loginInput" name="password" type="password" onChange={this.props.inputChanger} />
                        </p>
                        {/* Submit button */}
                        <input type="submit" value="Submit"></input>
                    </form>
                </div>
                {/* Render Redirect component conditionally based on isLoggedIn prop */}
                {this.props.isLoggedIn ? this.redirect() : null}
            </div>
        );
    }
}

// Export the SignIn component as the default export
export default SignIn;
