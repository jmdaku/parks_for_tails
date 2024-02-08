import React from 'react';
import { Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import Profile from './components/Profile.js';
import AddPark from './components/AddPark.jsx';
import SearchPage from './components/SearchPage';

const App = () => {
  return (
    <div>
  
      {/* Other components or layout elements could be here */}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Login" element={<Login />} />
        <Route path="/Register" element={<Register />} />
        <Route path="/profile/:id/:username" element={<Profile />} />
        <Route path="/addpark" element={<AddPark />} />
        <Route path="/search" element={<SearchPage />} /> 
        {/* Additional routes can be added as needed */}
      </Routes>
    </div>
  );
};

export default App;
