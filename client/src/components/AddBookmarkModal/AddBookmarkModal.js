import React, { useContext, useState } from "react";

// Context
import { BookmarksContext } from "../../contexts/BookmarksContext";

// Hooks
import useCsrfToken from "../../hooks/useCsrfToken";

// Functions
import { newBookmarkHandler } from "./newBookmarkHandler";
import { prefillBookmarkHandler } from "./prefillBookmarkHandler";

const AddBookmarkModal = ({ setModalOpen }) => {
  const csrfToken = useCsrfToken();
  const { refreshBookmarks } = useContext(BookmarksContext);

  // Step management state: step 1 is URL input, step 2 is full form
  const [step, setStep] = useState(1);
  const [url, setUrl] = useState("");
  const [bookmarkData, setBookmarkData] = useState({
    title: "",
    description: "",
    link: "",
    category: "",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Handler for step 1: send URL to server and prefill bookmark data
  const handleNext = async (event) => {
    event.preventDefault();
    setLoading(true);
    setError("");
    try {
      const data = await prefillBookmarkHandler(url, csrfToken);
      setBookmarkData({
        ...bookmarkData,
        title: data.title || "",
        description: data.description || "",
        link: url, // Use the user-provided URL
        category: data.category || ""
      });
      setStep(2);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  // Handler for final submission using newBookmarkHandler
  const handleSubmit = async (event) => {
    await newBookmarkHandler(event, csrfToken, refreshBookmarks);
    setModalOpen(false);
  };

  return (
    <div className="modal">
      <div className="modal__overlay"></div>
      {/* Step 1: show link field and "Next" button */}
      {step === 1 && (
        <form className="modal__container" onSubmit={handleNext}>
          <h2 className="modal__heading">Step 1: Add Bookmark Link</h2>

          {/* Link input fild */}
          <label className="label-text" htmlFor="bookmark_link">
            Link
          </label>
          <input
            type="text"
            id="bookmark_link"
            className="input-text"
            name="bookmark_link"
            placeholder="Enter webpage URL"
            value={url}
            onChange={(e) => setUrl(e.target.value)}
          />
          {error && <p className="error">{error}</p>}
          {/* Step 1 buttons */}
          <div className="modal__buttons">
            <button
              type="button"
              className="modal__close close-btn"
              onClick={() => setModalOpen(false)}
            >
              Close
            </button>
            <button
              type="submit"
              className="modal__submit submit-btn"
              disabled={loading}
            >
              {loading ? "Loading..." : "Next"}
            </button>
          </div>
        </form>
      )}
      {/* Step 2: show all the fields with prefilled data and "Submit" button */}
      {step === 2 && (
        <form className="modal__container" onSubmit={handleSubmit}>
          <h2 className="modal__heading">Step 2: Review & Edit Bookmark</h2>
          <label className="label-text" htmlFor="bookmark_title">
            Title
          </label>
          <input
            type="text"
            id="bookmark_title"
            className="input-text"
            name="bookmark_title"
            placeholder="Title"
            value={bookmarkData.title}
            onChange={(e) =>
              setBookmarkData({ ...bookmarkData, title: e.target.value })
            }
          />

          <label className="label-text" htmlFor="bookmark_description">
            Description
          </label>
          <textarea
            rows="2"
            id="bookmark_description"
            className="input-text input-text__textarea"
            name="bookmark_description"
            placeholder="Description"
            value={bookmarkData.description}
            onChange={(e) =>
              setBookmarkData({ ...bookmarkData, description: e.target.value })
            }
          ></textarea>

          <label className="label-text" htmlFor="bookmark_link">
            Link
          </label>
          <input
            type="text"
            id="bookmark_link"
            className="input-text"
            name="bookmark_link"
            placeholder="Link"
            value={bookmarkData.link}
            onChange={(e) =>
              setBookmarkData({ ...bookmarkData, link: e.target.value })
            }
          />

          <label className="label-text" htmlFor="choose_collection">
            Collection
          </label>
          {/* Select collection */}
          <select
            id="choose_collection"
            value={bookmarkData.collection}
            onChange={(e) =>
              setBookmarkData({ ...bookmarkData, collection: e.target.value })
            }
          >
            <option value="select">Select...</option>
            <option value="example">Example</option>
            <option value="example">Example</option>
            <option value="example">Example</option>
            <option value="example">Example</option>
          </select>

          {/* Create new collection */}
          <label className="label-text" htmlFor="new_collection">
            Create Collection
          </label>
          <input
            type="text"
            id="new_collection"
            className="input-text"
            name="new_collection"
            placeholder="New Collection"
            value={bookmarkData.category}
            onChange={(e) =>
              setBookmarkData({
                ...bookmarkData,
                collection: e.target.value,
              })
            }
          />

          {/* Step 2 buttons */}
          <div className="modal__buttons">
            <button
              type="button"
              className="modal__close close-btn"
              onClick={() => setModalOpen(false)}
            >
              Close
            </button>
            <button type="submit" className="modal__submit submit-btn">
              Submit
            </button>
          </div>
        </form>
      )}
    </div>
  );
};

export default AddBookmarkModal;
