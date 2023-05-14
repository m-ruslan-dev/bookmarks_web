import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import "./App.scss";
import Dashboard from "./pages/Dashboard/Dashboard";
import Authentication from "./pages/Authentication/Authentication";
import WithAuthentication from "./HOC/WithAuthentication/WithAuthentication";

let App = () => {
  return (
    <BrowserRouter>
      <>
        <Routes>
          <Route
            path="/"
            element={
              <WithAuthentication>
                <Dashboard />
              </WithAuthentication>
            }
          />
          <Route path="/auth/:formType" element={<Authentication />} />
        </Routes>
      </>
    </BrowserRouter>
  );
};

export default App;
