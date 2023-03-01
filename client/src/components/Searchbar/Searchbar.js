import React from "react";

const Searchbar = () => {
  return (
    <div className="searchbar" role="search">
      <input type="text" placeholder="Search" className="input-text"></input>
      <button type="submit">Go</button>
    </div>
  );
};

export default Searchbar;
