import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import Profile from './components/Profile.js';
import AddPark from './components/AddPark.jsx'

const App = () => {
  return (
    <div>
  
      {/* Other components or layout elements could be here */}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Login" element={<Login />} />
        <Route path="/Register" element={<Register />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/addpark" element={<AddPark />} />
        {/* Additional routes can be added as needed */}
      </Routes>
    </div>
  );
};

export default App;
