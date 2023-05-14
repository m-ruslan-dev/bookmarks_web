import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { UsernameContext } from "../../contexts/UsernameContext";

// Checks user authentication
// Redirects to login page if authentication check fails
// Provides user's username to child components via UsernameContext
const WithAuthentication = ({ children }) => {
  const [userStatus, setUserStatus] = useState({
    username: null,
    isAuthenticated: false,
  });
  const navigate = useNavigate();

  useEffect(() => {
    const checkAuthentication = async () => {
      // Send a GET request to the server's "/user" endpoint
      const response = await fetch("http://localhost:8080/user", {
        credentials: "include",
        headers: {
          "X-Requested-With": "XMLHttpRequest",
        },
      });

      // If response is ok, then user is authenticated
      if (response.ok) {
        const data = await response.json();
        setUserStatus({
          username: data.username,
          isAuthenticated: true,
        });
      } else {
        // If user is not authenticated, redirect to login page
        navigate("/auth/login");
      }
    };
    checkAuthentication();
  }, []);

  // If user is not authenticated, nothing is rendered
  if (!userStatus.isAuthenticated) {
    return null;
  } else {
    return (
      // If user is authenticated, provide the child components
      <UsernameContext.Provider value={userStatus.username}>
        {children}
      </UsernameContext.Provider>
    );
  }
};

export default WithAuthentication;
