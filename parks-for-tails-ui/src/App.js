
import './App.css';
import loginForm from './components/login.js';
import registrationForm from './components/register.js';

function App() {
  return (
    <div className="App">
   <div>
      <div className="menu">
         <loginForm />
         <registrationForm />
      </div>
   </div>
 </div>
  );
}

export default App;
