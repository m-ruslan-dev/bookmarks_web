const handleLogOut = async (event, csrfToken, navigate) => {
  event.preventDefault();

  // Send log out request to the server
  const response = await fetch("http://localhost:8080/logout", {
    method: "POST",
    credentials: "include",
    headers: {
      "X-Requested-With": "XMLHttpRequest",
      "X-XSRF-TOKEN": csrfToken,
    },
  });

  // Redirect to the login page on succesful log out
  if (response.ok) {
    navigate("/auth/login");
  }
};

export default handleLogOut;
