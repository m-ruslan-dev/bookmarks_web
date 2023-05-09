import { useEffect, useState } from "react";

const getCsrfTokenFromCookies = () => {
  const cookiesString = document.cookie;
  const cookiesArray = cookiesString.split("; ");

  // Find the XSRF-TOKEN cookie
  const xsrfCookie = cookiesArray.find((cookie) =>
    cookie.startsWith("XSRF-TOKEN=")
  );

  // If "XSRF-TOKEN" is defined, return its value
  if (xsrfCookie) {
    const token = xsrfCookie.split("=")[1];
    return token;
  } else {
    return null;
  }
};

const useCsrfToken = () => {
  const [csrfToken, setCsrfToken] = useState(getCsrfTokenFromCookies()); // Get CSRF token from cookies

  useEffect(() => {
    // If CSRF token is not defined in the cookies, request one from server
    if (!csrfToken) {
      const getToken = async () => {
        try {
          // The response is setting up a "XSRF-TOKEN" cookie
          await fetch("http://localhost:8080/csrf", {
            credentials: "include",
          });

          // Set new token from the cookies
          setCsrfToken(getCsrfTokenFromCookies());
        } catch (error) {
          console.error(error);
        }
      };
      getToken();
    }
  }, []);

  return csrfToken;
};

export default useCsrfToken;
