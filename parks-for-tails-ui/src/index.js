import React from 'react';
import { createRoot } from 'react-dom/client';
import 'mdb-react-ui-kit/dist/css/mdb.min.css';
import "@fortawesome/fontawesome-free/css/all.min.css";
import './index.css';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Home from './components/Home';
import Profile from './components/Profile.jsx';
import AddPark from './components/AddPark.jsx';
import { LoginContext, LoginProvider } from './CheckLogin.jsx';

const container = document.getElementById('root'); 
const root = createRoot(container);

root.render(
  <LoginProvider>
  <Router>
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/addpark" element={<AddPark />} />
    </Routes>
  </Router>
  </LoginProvider>
);
// Render the App component separately if needed
// root.render(<App />, container);
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
