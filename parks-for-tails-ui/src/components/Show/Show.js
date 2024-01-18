// Import necessary dependencies
import React, { Component } from 'react'
import { Redirect } from 'react-router-dom'
import axios from 'axios'
import './Show.css'
import Map from '../Map/Map'

// Define the API URL for parks
const url = 'https://dog-park-finder.herokuapp.com/parks/'

// Define the Show component as a class
class Show extends Component {
    constructor() {
        super()
        this.state = {
            park: {},        // Initialize park state as an empty object
            redirect: false,  // Initialize redirect state as false
            notAuth: false    // Initialize notAuth state as false
        }
        this.deleteHandler = this.deleteHandler.bind(this)
    }

    // componentDidMount lifecycle method
    componentDidMount() {
        // Make a GET request to fetch park details based on the provided ID
        axios.get(url + this.props.match.params.id)
            .then(park => {
                // Update the state with the received park data
                this.setState({
                    park: park.data
                })
            })
            .catch(err => {
                console.log(err)
            })
    }

    // deleteHandler method for handling park deletion
    deleteHandler() {
        // Make a DELETE request to delete the park
        axios.delete(url + this.props.match.params.id, {
            data: { token: localStorage.token }
        })
            .then(res => {
                // Check if the user is not authorized
                if (res.data === "not authorized") {
                    this.setState({
                        notAuth: true
                    })
                } else {
                    // If deletion is successful, set the redirect state to true
                    this.setState({
                        redirect: true
                    })
                }
            })
            .catch((err) => {
                console.log(err);
            })
    }

    // Function to render a Redirect component
    redirect() {
        return <Redirect to="/" />
    }

    // Render method to define the structure of the component
    render() {
        console.log(this.state.park)
        return (
            <div>
                {/* Display park details */}
                <div className="park" key={this.state.park._id}>
                    <h1>{this.state.park.name}</h1>
                    <h3>Amenities:</h3>
                    <ul>
                        <li>Size: {this.state.park.size}</li>
                        <li>Bathrooms: {this.state.park.bathrooms ? <span>Yes!</span> : <span>None</span>}</li>
                        <li>Parking: {this.state.park.parking ? <span>Yes!</span> : <span>None</span>}</li>
                        <li>Other Notes: {this.state.park.misc}</li>
                    </ul>
                    <h3>{this.state.park.voteValue}</h3>
                    {/* Button to trigger the deleteHandler */}
                    <button onClick={this.deleteHandler} name={this.state.park._id}>Delete</button>
                    {/* Conditional rendering of Redirect and notAuth messages */}
                    {this.state.redirect ? this.redirect() : null}
                    {this.state.notAuth ? <h1>Sorry, not your park!</h1> : null}
                </div>
                {/* Render Map component with the park name */}
                <div>
                    <Map name={this.state.park.name} />
                </div>
            </div>
        )
    }
}

// Export the Show component as the default export
export default Show;
