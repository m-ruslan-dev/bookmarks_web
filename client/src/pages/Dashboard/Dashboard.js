import React, { useState } from "react";

import Navigation from "../../components/Navigation/Navigation";
import MainControls from "../../components/MainControls/MainControls";
import Bookmarks from "../../components/Bookmarks/Bookmarks";
import AddBookmarkModal from "../../components/AddBookmarkModal/AddBookmarkModal";

const Dashboard = () => {
  const [modalOpen, setModalOpen] = useState(false);

  return (
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
  );
};

export default Dashboard;
