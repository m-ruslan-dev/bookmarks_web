import React from "react";

const Login = () => {
  return (
    <section className="login">
      {/* Text info */}
      <div className="login_header">
        <h2>Log in</h2>
        <p>Your bookmarks are just a login away</p>
      </div>

      <form className="login__form">
        {/* Username field */}
        <label for="username">Username</label>
        <input
          type="text"
          className="login__username"
          name="username"
          id="username"
        ></input>

        {/* Password field */}
        <label for="password">Password</label>
        <input
          type="password"
          className="login__password"
          name="password"
          id="password"
        ></input>

        <button type="submit" className="login__submit-btn">
          Log in
        </button>
      </form>

      {/* Link to the registration page */}
      <p className="login__signup-link">
        Don't have an account? <a href="#">Sign up</a>
      </p>
    </section>
  );
};

export default Login;
