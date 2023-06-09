import React from "react";
import { useNavigate, Link } from "react-router-dom";

import useCsrfToken from "../../hooks/useCsrfToken";
import { handleSubmit } from "./loginHandler";

const Login = () => {
  const csrfToken = useCsrfToken();
  const navigate = useNavigate();

  return (
    <section className="login">
      {/* Text info */}
      <div className="login__header">
        <h2>Log in</h2>
        <p>Your bookmarks are just a login away</p>
      </div>

      <form
        className="login__form"
        onSubmit={(event) => handleSubmit(event, csrfToken, navigate)}
      >
        {/* Username field */}
        <label className="label-text" htmlFor="login-username">
          Username
        </label>
        <input
          type="text"
          className="login__username input-text"
          name="login-username"
          id="login-username"
        ></input>

        {/* Password field */}
        <label className="label-text" htmlFor="login-password">
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
        Don't have an account? <Link to="/auth/register">Sign up</Link>
      </p>
    </section>
  );
};

export default Login;
