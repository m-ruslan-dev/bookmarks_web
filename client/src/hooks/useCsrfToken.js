import { useEffect } from "react";
import { useCookies } from "react-cookie";

const useCsrfToken = () => {
  const [cookies, setCookies] = useCookies(["XSRF-TOKEN"]);

  useEffect(() => {
    // Check if token is present in the cookies
    // If not, fetch the token and update the "cookies" object
    if (!cookies["XSRF-TOKEN"]) {
      const getToken = async () => {
        try {
          await fetch("http://localhost:8080/csrf", {
            credentials: "include",
          });

          setCookies(); // Forces rerender to update "cookies" object according to browser's cookies
        } catch (error) {
          console.error(error);
        }
      };

      getToken();
    }
  }, []);

  const csrfToken = cookies["XSRF-TOKEN"];
  return csrfToken;
};

export default useCsrfToken;
