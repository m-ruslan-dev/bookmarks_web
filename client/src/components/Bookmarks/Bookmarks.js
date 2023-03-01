import React from "react";
import Card from "../Card/Card";

const Bookmarks = () => {
  let bookmarks = [1, 2];
  return (
    <section className="bookmarks">
      {bookmarks.map((item, index) => {
        return <Card />;
      })}
    </section>
  );
};

export default Bookmarks;
