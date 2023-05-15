import React, { useContext } from "react";

// Hooks
import useCsrfToken from "../../hooks/useCsrfToken";
// Functions
import { extractYearMonthDate } from "../../utils/dateHelpers";
import handleDeleteBookmark from "./handleDeleteBookmark";
// Context
import { BookmarksContext } from "../../contexts/BookmarksContext";
// Icon
import { RiDeleteBin6Line } from "react-icons/ri";

const Card = ({ title, link, bookmarkId, creationDate }) => {
  const csrfToken = useCsrfToken();
  // Get creation date for the provided bookmark
  const { year, month, date } = extractYearMonthDate(creationDate);
  // Get the refreshBookmarks function form the BookmarksContext
  const { refreshBookmarks } = useContext(BookmarksContext);

  return (
    <div className="card">
      <div className="card__body">
        <p className="card__date">{`${date} ${month} ${year}`}</p>
        <h4 className="card__title">{title}</h4>
        <a href={link} className="card__url">
          {link}
        </a>
      </div>
      <button
        type="button"
        className="card__delete"
        onClick={(event) =>
          handleDeleteBookmark(event, bookmarkId, csrfToken, refreshBookmarks)
        }
      >
        <RiDeleteBin6Line />
      </button>
    </div>
  );
};

export default Card;
