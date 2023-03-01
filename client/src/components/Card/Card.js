import React from "react";
import { RiDeleteBin6Line } from "react-icons/ri";

const Card = () => {
  return (
    <div className="card">
      <div className="card__img">
        <img src="https://via.placeholder.com/200x200" width="100"></img>
      </div>
      <div className="card__body">
        <p className="card__date">22 January 2023</p>
        <h4 className="card__title">Amazing website 101 A</h4>
        <a href="#" className="card__url">
          youtube.com
        </a>
      </div>
      <button type="button" className="card__delete">
        <RiDeleteBin6Line />
      </button>
    </div>
  );
};

export default Card;
