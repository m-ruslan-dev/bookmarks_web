export const handleSubmit = async (event, csrfToken, navigate) => {
  event.preventDefault();

  // Get data from form fields
  const formData = new FormData(event.target);
  const username = formData.get("login-username");
  const password = formData.get("login-password");

  // Create URL encoded object
  const userCredentials = new URLSearchParams({ username, password });

  // Send user credentials
  try {
    const response = await fetch("http://localhost:8080/login", {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "X-XSRF-TOKEN": csrfToken,
      },
      body: userCredentials,
    });

    if (response.status === 200) {
      navigate("/");
    }
  } catch (error) {
    console.error(error);
  }
};
