import React from "react";

const handleSubmit = async (event) => {
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
      },
      body: userCredentials,
    });
    if (response.status === 200) {
      console.log("success");
    } else {
      let error = new Error();
      error.message = "Something went wrong";
      console.error(error);
    }
  } catch (error) {
    console.error(error);
  }
};

const Login = () => {
  return (
    <section className="login">
      {/* Text info */}
      <div className="login__header">
        <h2>Log in</h2>
        <p>Your bookmarks are just a login away</p>
      </div>

      <form className="login__form" onSubmit={(event) => handleSubmit(event)}>
        {/* Username field */}
        <label className="label-text" for="login-username">
          Username
        </label>
        <input
          type="text"
          className="login__username input-text"
          name="login-username"
          id="login-username"
        ></input>

        {/* Password field */}
        <label className="label-text" for="login-password">
          Password
        </label>
        <input
          type="password"
          className="login__password input-text"
          name="login-password"
          id="login-password"
        ></input>

        <button type="submit" className="login__submit submit-btn">
          Log in
        </button>
      </form>

      {/* Link to the registration page */}
      <p className="login__sign-up-link">
        Don't have an account? <a href="#">Sign up</a>
      </p>
    </section>
  );
};

export default Login;
