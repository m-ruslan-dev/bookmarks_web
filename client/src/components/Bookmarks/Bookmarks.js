import React from "react";
import Card from "../Card/Card";
import useGetBookmarks from "../../hooks/useGetBookmarks";

const Bookmarks = () => {
  const fetchStatus = useGetBookmarks("http://localhost:8080/bookmarks");
  const bookmarks = fetchStatus.bookmarks;
  return (
    <section className="bookmarks">
      {bookmarks.map((bookmark) => {
        return (
          <Card title={bookmark.title} link={bookmark.link} key={bookmark.id} />
        );
      })}
    </section>
  );
};

export default Bookmarks;
