import React, { useState } from "react";

import Navigation from "../../components/Navigation/Navigation";
import MainControls from "../../components/MainControls/MainControls";
import Bookmarks from "../../components/Bookmarks/Bookmarks";
import AddBookmarkModal from "../../components/AddBookmarkModal/AddBookmarkModal";

import { BookmarksContext } from "../../contexts/BookmarksContext";
import useGetBookmarks from "../../hooks/useGetBookmarks";

// Renders the dashboard page with navigation, main controls, bookmarks, and add bookmark modal
// Provides BookmarksContext for the components
const Dashboard = () => {
  // Initialize state for controlling the display of AddBookmarkModal
  const [modalOpen, setModalOpen] = useState(false);
  // Fetch bookmarks and handle refreshing
  const { bookmarksFetchStatus, refreshBookmarks } = useGetBookmarks(
    "http://localhost:8080/bookmarks"
  );

  return (
    <BookmarksContext.Provider
      value={{ bookmarksFetchStatus, refreshBookmarks }}
    >
      <div className="dashboard container">
        <aside className="dashboard__aside">
          <Navigation />
        </aside>
        <main className="dashboard__main">
          <MainControls setModalOpen={setModalOpen} />
          <Bookmarks />
          {modalOpen && <AddBookmarkModal setModalOpen={setModalOpen} />}
        </main>
      </div>
    </BookmarksContext.Provider>
  );
};

export default Dashboard;
