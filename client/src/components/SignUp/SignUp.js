import React from "react";

const SignUp = () => {
  return (
    <section className="sign-up">
      {/* Text info */}
      <div className="sign-up__header">
        <h2>Sign up</h2>
        <p>Create an account to start saving your favorite bookmarks</p>
      </div>

      <form className="sign-up__form">
        {/* Username field */}
        <label for="sign-up-username">Username</label>
        <input
          type="text"
          classname="sign-up__username"
          name="sign-up-username"
          id="sign-up-username"
        ></input>

        {/* Email field */}
        <label for="sign-up-email">Email</label>
        <input
          type="email"
          classname="sign-up__email"
          name="sign-up-email"
          id="sign-up-email"
        ></input>

        {/* Password field */}
        <label for="sign-up-password">Password</label>
        <input
          type="password"
          classname="sign-up__password"
          name="sign-up-password"
          id="sign-up-password"
        ></input>

        {/* Submit */}
        <button type="submit">Sign up</button>
      </form>

      {/* Link to login page */}
      <p className="sign-up__login-link">
        Alreade have an account? <a href="#">Log in</a>
      </p>
    </section>
  );
};

export default SignUp;
