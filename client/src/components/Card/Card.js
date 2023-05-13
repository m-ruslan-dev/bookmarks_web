import React from "react";
import { RiDeleteBin6Line } from "react-icons/ri";

const Card = ({ title, link }) => {
  return (
    <div className="card">
      <div className="card__body">
        <p className="card__date">22 January 2023</p>
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
