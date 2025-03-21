import React, { useContext } from "react";
import Card from "../Card/Card";
import { BookmarksContext } from "../../contexts/BookmarksContext";

const Bookmarks = () => {
  const { bookmarksFetchStatus } = useContext(BookmarksContext);
  const bookmarks = bookmarksFetchStatus.bookmarks;
  return (
    <section className="bookmarks">
      {bookmarks.map((bookmark) => {
        return (
          <Card
            title={bookmark.title}
            description={bookmark.description}
            link={bookmark.link}
            creationDate={bookmark.created}
            bookmarkId={bookmark.id}
            key={bookmark.id}
          />
        );
      })}
    </section>
  );
};

export default Bookmarks;
