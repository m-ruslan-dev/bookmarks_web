import React from "react";
import { useMatch } from "react-router-dom";

import Login from "../../components/Login/Login";
import SignUp from "../../components/SignUp/SignUp";

const Authentication = () => {
  // The page is only available by "/auth/login" and "/auth/register" paths.
  // If the URL matches "/auth/register", then render <SignUp /> component. Else, render <Login />.

  const isRegister = useMatch("/auth/register");

  return <>{isRegister ? <SignUp /> : <Login />}</>;
};

export default Authentication;
