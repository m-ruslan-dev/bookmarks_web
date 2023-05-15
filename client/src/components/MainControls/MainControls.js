import React, { useContext } from "react";

// Components
import Searchbar from "../Searchbar/Searchbar";

// Contexts
import { BookmarksContext } from "../../contexts/BookmarksContext";

const MainControls = ({ setModalOpen }) => {
  // Get bookmarks from the BookmarksContext
  const { bookmarksFetchStatus } = useContext(BookmarksContext);
  // Get the number of bookmarks in the array
  const bookmarksCount = bookmarksFetchStatus.bookmarks.length;
  return (
    <div className="controls">
      <h3 className="controls__counter">
        Bookmarks: <span>({`${bookmarksCount}`})</span>
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
