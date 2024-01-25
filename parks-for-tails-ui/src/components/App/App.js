
import React, { Component } from 'react';
import './App.css';
import { Route, Link, Switch } from 'react-router-dom'
import axios from 'axios'
import Home from './Home/Home'
import Create from './Create/Create'
import Show from './Show/Show';
import SignUp from './SignUp/SignUp'
import SignIn from './SignIn/SignIn.js'

// Define the API URL
const url = 'https://maps.googleapis.com/maps/api/js?key='

// Define the main App component
class App extends Component {
  // Constructor method to initialize the component's state
  constructor() {
    super()

    // Initialize state with email, password, and login status
    this.state = {
      email: "",
      password: "",
      isLoggedIn: false
    }

    // Bind event handler methods to the component instance
    this.inputChanger = this.inputChanger.bind(this)
    this.signupSubmit = this.signupSubmit.bind(this)
    this.loginSubmit = this.loginSubmit.bind(this)
    this.logoutSubmit = this.logoutSubmit.bind(this)
  }

  // Event handler to update state when input values change
  inputChanger(e) {
    this.setState({ [e.target.name]: e.target.value })
  }

  // Event handler for user signup
  signupSubmit(e) {
    e.preventDefault()
    // Send a POST request to the signup endpoint with user credentials
    axios.post(url + "/users/signup", {
      email: this.state.email,
      password: this.state.password
    })
      .then(res => {
        // Save the received token to local storage and update login status
        localStorage.token = res.data.token
        this.setState({ isLoggedIn: true })
      })
      .catch(err => console.log(err))
  }

  // Event handler for user login
  loginSubmit(e) {
    e.preventDefault()
    // Send a POST request to the signin endpoint with user credentials
    axios.post(url + "/users/signin", {
      email: this.state.email,
      password: this.state.password
    })
      .then(res => {
        // Save the received token to local storage and update login status
        localStorage.token = res.data.token
        this.setState({
          isLoggedIn: true
        })
      })
      .catch(err => console.log(err))
  }

  // Event handler for user logout
  logoutSubmit() {
    // Reset state and clear token from local storage
    this.setState({
      email: '',
      password: '',
      isLoggedIn: false
    })
    localStorage.clear()
  }

  // Lifecycle method: componentDidMount is called after the component is rendered
  componentDidMount() {
    // Check if a token exists in local storage, update login status accordingly
    if (localStorage.token) {
      this.setState({
        isLoggedIn: true
      })
    }
  }

  // Render method to define the structure of the component
  render() {
    return (
      <div className="App">
        {/* Header with navigation links */}
        <header className="navbar">
          <Link to="/"><h1 className="navbar">Dog Park Finder</h1></Link>
          <nav>
            {/* Conditional rendering of navigation links based on login status */}
            {this.state.isLoggedIn ? <Link to="/create"><h3>New Park</h3></Link> : null}
            {!this.state.isLoggedIn ? <Link to="/signup"><h3>Sign Up</h3></Link> : null}
            {!this.state.isLoggedIn ? <Link to="/signin"><h3>Log In</h3></Link> : null}
            {this.state.isLoggedIn ? <Link to="/" onClick={this.logoutSubmit}><h3>Log Out</h3></Link> : null}
          </nav>
        </header>
        {/* Route handling using React Router Switch and Route components */}
        <Switch>
          <Route path="/create" component={Create} />
          <Route path="/signup" render={() => <SignUp inputChanger={this.inputChanger} signupSubmit={this.signupSubmit} isLoggedIn={this.state.isLoggedIn} />} />
          <Route path="/sigin" render={() => <SignIn inputChanger={this.inputChanger} loginSubmit={this.loginSubmit} isLoggedIn={this.state.isLoggedIn} />} />
          <Route path="/:id" component={Show} />
          <Route path="/" component={Home} />
        </Switch>
      </div>
    );
  }
}

// Export the App component as the default export
export default App;
