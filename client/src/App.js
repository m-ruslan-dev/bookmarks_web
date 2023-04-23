import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

import "./App.scss";
import Dashboard from "./pages/Dashboard/Dashboard";
import Authentication from "./pages/Authentication/Authentication";

let App = () => {
  return (
    <BrowserRouter>
      <>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/auth/:formType" element={<Authentication />} />
        </Routes>
      </>
    </BrowserRouter>
  );
};

export default App;
