import React from 'react';
import 'mdb-react-ui-kit/dist/css/mdb.min.css';
import "@fortawesome/fontawesome-free/css/all.min.css";
import ReactDOM from 'react-dom/client';
import './index.css';
import root from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import Home from './components/Home';

//const root = ReactDOM.createRoot(document.getElementById('root'));

//root.render(
    ReactDOM.render(
//   <BrowserRouter>
//   <App />
// </BrowserRouter>
<Router>
      <Routes>
          <Route path="/" element={Home} />
          <Route path="/login" element={Login} />
          <Route path="/register" element={Register} />
      </Routes>
  </Router>
);


// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

