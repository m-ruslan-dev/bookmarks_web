import React, { useContext } from "react";
import { useNavigate } from "react-router-dom";

// Functions
import handleLogOut from "./handleLogOut";

// Hooks
import useCsrfToken from "../../hooks/useCsrfToken";

// Contexts
import { UsernameContext } from "../../contexts/UsernameContext";

// Icons
import { RiLogoutBoxLine } from "react-icons/ri";

const Navigation = () => {
  // Get username from UsernameContext
  const username = useContext(UsernameContext);
  // Get CSRF token and navigate for handleLogOut
  const csrfToken = useCsrfToken();
  const navigate = useNavigate();

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

      {/* Account Actions */}
      <hr />
      <ul className="nav__list">
        <li className="nav__item">
          <a
            href="#"
            className="nav__link"
            onClick={(event) => handleLogOut(event, csrfToken, navigate)}
          >
            <RiLogoutBoxLine className="nav__icon" />
            Log Out
          </a>
        </li>
      </ul>
    </nav>
  );
};

export default Navigation;
