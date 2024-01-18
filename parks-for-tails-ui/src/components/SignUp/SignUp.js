// Import necessary dependencies
import React, { Component } from 'react';
import { Redirect } from 'react-router-dom'
import "./Signup.css"

// Define the SignUp component as a class
class SignUp extends Component {

    // Function to render a Redirect component
    redirect() {
        return <Redirect to="/" />
    }

    // Render method to define the structure of the component
    render() {
        return (
            <div className="SignUp">
                {/* Display heading */}
                <h1>Sign Up</h1>
                {/* Form container with a form */}
                <div className="form-container">
                    <form onSubmit={this.props.signupSubmit}>
                        {/* Email input field */}
                        <p>
                            <label>Email:</label>
                            <input className="signUpInput" type="text" name="email" onChange={this.props.inputChanger}></input>
                        </p>
                        {/* Password input field */}
                        <p>
                            <label>Password:</label>
                            <input className="signUpInput" type="password" name="password" onChange={this.props.inputChanger}></input>
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

// Export the SignUp component as the default export
export default SignUp;
