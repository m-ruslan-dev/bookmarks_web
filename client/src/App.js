import React, { useState } from "react";

import "./App.scss";
import Navigation from "./components/Navigation/Navigation";
import MainControls from "./components/MainControls/MainControls";
import Bookmarks from "./components/Bookmarks/Bookmarks";
import AddBookmarkModal from "./components/AddBookmarkModal/AddBookmarkModal";

let App = () => {
  let [modalOpen, setModalOpen] = useState(false);

  return (
    <div className="app container">
      <aside className="app__aside">
        <Navigation />
      </aside>
      <main className="app__main">
        <MainControls setModalOpen={setModalOpen} />
        <Bookmarks />
        {modalOpen && <AddBookmarkModal setModalOpen={setModalOpen} />}
      </main>
    </div>
  );
};

export default App;
