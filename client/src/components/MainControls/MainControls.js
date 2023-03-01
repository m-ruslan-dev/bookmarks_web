import React from "react";
import Searchbar from "../Searchbar/Searchbar";

const MainControls = ({ setModalOpen }) => {
  let counter = 0;
  return (
    <div className="controls">
      <h3 className="controls__counter">
        Bookmarks: <span>({`${counter}`})</span>
      </h3>
      <Searchbar />
      <div className="controls__menu">
        <button
          type="button"
          className="controls__btn"
          onClick={() => setModalOpen(true)}
        >
          Add bookmark
        </button>
      </div>
    </div>
  );
};

export default MainControls;
