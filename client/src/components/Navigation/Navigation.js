import React from "react";

const Navigation = () => {
  let user = "Username";
  let collections = ["All", "example"];
  return (
    <nav className="nav">
      {/* User section */}
      <div className="nav__user">
        <h2>
          <span>Greetings,</span>
          <br />
          {`${user}`}
        </h2>
      </div>
      <hr />
      {/* Collections section */}
      <h2 className="nav__collections">Collections</h2>
      <ul className="nav__list">
        {collections.map((item, index) => {
          return (
            <li className="nav__item" id={index}>
              <a href="#" className="nav__link">
                {item}
              </a>
            </li>
          );
        })}
      </ul>
    </nav>
  );
};

export default Navigation;
