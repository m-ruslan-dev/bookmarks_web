import React from "react";
import { RiDeleteBin6Line } from "react-icons/ri";

import { extractYearMonthDate } from "../../utils/dateHelpers";

const Card = ({ title, link, creationDate }) => {
  const { year, month, date } = extractYearMonthDate(creationDate);

  return (
    <div className="card">
      <div className="card__body">
        <p className="card__date">{`${date} ${month} ${year}`}</p>
        <h4 className="card__title">{title}</h4>
        <a href={link} className="card__url">
          {link}
        </a>
      </div>
      <button type="button" className="card__delete">
        <RiDeleteBin6Line />
      </button>
    </div>
  );
};

export default Card;
