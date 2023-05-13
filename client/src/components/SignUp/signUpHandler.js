const signUpHandler = async (event, csrfToken, navigate) => {
  event.preventDefault();

  const formData = new FormData(event.target);
  const username = formData.get("sign-up-username");
  const email = formData.get("sign-up-email");
  const password = formData.get("sign-up-password");

  const userData = {
    username,
    email,
    password,
  };
  try {
    const response = await fetch("http://localhost:8080/user/register", {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
        "X-Requested-With": "XMLHttpRequest",
        "X-XSRF-TOKEN": csrfToken,
      },
      body: JSON.stringify(userData),
    });

    if (response.status === 200) {
      navigate("/auth/login");
    }
  } catch (error) {
    console.error(error);
  }
};

export default signUpHandler;
