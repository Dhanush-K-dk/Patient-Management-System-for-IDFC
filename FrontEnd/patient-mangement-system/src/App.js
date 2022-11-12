import Navbar from "Components/Navbar/Navbar";
import Login from "Pages/Login/Login";
import PatientDashboard from "Pages/PatientDashboard/PatientDashboard";
import SignUp from "./Pages/SignUp/SignUp";
import { substr, lastIndexOf } from '@7urtle/lambda'
import { BrowserRouter } from 'react-router-dom';
import Router from "./Router.js";
import Appointment from "Pages/Appointment/Appointment";

function App() {

  return (
    <div>
      <article>
        <BrowserRouter>
          <header>
            <Navbar />
          </header>
          <Router />
        </BrowserRouter>
      </article>
      {/* <SignUp /> */}
      {/* <Login /> */}
      {/* <Appointment /> */}
    </div>
  );
}

export default App;