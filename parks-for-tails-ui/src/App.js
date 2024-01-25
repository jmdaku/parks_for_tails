import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import AddPark from './components/AddPark.jsx';

const App = () => {

  
    return (
      <div>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="/profile" element={<Profile />} />
            <Route path="/addpark" element={<AddPark />} />
          </Routes>
      </div>
    );
}

//ReactDOM.createRoot(document.getElementById('root')).render(<App />);


export default App;
