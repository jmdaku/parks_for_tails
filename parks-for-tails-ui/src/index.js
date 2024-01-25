import React from 'react';
import { createRoot } from 'react-dom/client';
import 'mdb-react-ui-kit/dist/css/mdb.min.css';
import "@fortawesome/fontawesome-free/css/all.min.css";
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { LoginProvider } from './components/checkLogin';

const container = document.getElementById('root'); // Use 'root' as the container ID
const root = createRoot(container);


root.render(
  <LoginProvider> {/* Wrap whole application with LoginProvider */}
    <Router>
      <Routes>
        <Route path="*" element={<App />} />
        {/* Other routes */}
      </Routes>
    </Router>
  </LoginProvider>,
);
