import React, { useContext } from "react";
import { UsernameContext } from "../../contexts/UsernameContext";

const Navigation = () => {
  const username = useContext(UsernameContext);
  let collections = ["All", "Example 1", "Example 2", "Example 3"];

  return (
    <nav className="nav">
      {/* User section */}
      <div className="nav__user">
        <h2>
          <span>Greetings,</span>
          <br />
          {`${username}`}
        </h2>
      </div>
      <hr />
      {/* Collections section */}
      <h2 className="nav__collections">Collections</h2>
      <ul className="nav__list">
        {collections.map((item, index) => {
          return (
            <li className="nav__item" key={index}>
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
