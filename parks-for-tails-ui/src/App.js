import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import Profile from './components/Profile.js';

const App = () => {
  return (
    <div>
      <h1>Welcome to My App</h1>
      {/* Other components or layout elements could be here */}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Login" element={<Login />} />
        <Route path="/Register" element={<Register />} />
        <Route path="/profile" element={<Profile />} />
        {/* Additional routes can be added as needed */}
      </Routes>
    </div>
  );
};

export default App;
