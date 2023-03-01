import React from "react";

const AddBookmarkModal = ({ setModalOpen }) => {
  return (
    <div className="modal">
      <div className="modal__overlay"></div>
      <form className="modal__container">
        <h2 className="modal__heading">New Bookmark</h2>

        {/* Input link */}
        <label for="bookmark_link">Link</label>
        <input type="text" id="bookmark_link" className="input-text"></input>

        {/* Select collection */}
        <label for="choose_collection">Collection</label>
        <select id="choose_collection" placeholder="Select...">
          <option value="select">Select...</option>
          <option value="example">Example</option>
          <option value="example">Example</option>
          <option value="example">Example</option>
          <option value="example">Example</option>
        </select>

        {/* Create new collection */}
        <label for="new_collection">Create collection</label>
        <input type="text" id="new_collection" className="input-text"></input>

        {/* Buttons */}
        <div className="modal__buttons">
          <button
            type="button"
            className="modal__cancel"
            onClick={() => setModalOpen(false)}
          >
            Cancel
          </button>
          <button type="submit" className="modal__submit">
            Submit
          </button>
        </div>
      </form>
    </div>
  );
};

export default AddBookmarkModal;
