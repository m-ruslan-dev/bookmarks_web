import { useState, useEffect } from "react";

// Fetches bookmarks
// Provides bookmarks loading status
// Provides refresh function
const useGetBookmarks = (url) => {
  // Initialize bookmarksFetchStatus object
  const [bookmarksFetchStatus, setBookmarksFetchStatus] = useState({
    bookmarks: [],
    isLoading: true,
  });
  // Initialize trigger for rerendering
  const [trigger, setTrigger] = useState(false);

  useEffect(() => {
    // Define the request
    const fetchBookmarks = async () => {
      try {
        // Get bookmarks
        const response = await fetch(url, {
          credentials: "include",
          headers: {
            "X-Requested-With": "XMLHttpRequest",
          },
        });
        const bookmarks = await response.json();

        // Change state
        setBookmarksFetchStatus({
          bookmarks,
          isLoading: false,
        });
      } catch (error) {
        console.log(error);
      }
    };

    fetchBookmarks();
  }, [trigger]);

  const refreshBookmarks = () => {
    // Set the opposite of value of trigger to change state
    // State change invokes the useEffect()
    setTrigger(!trigger);
  };

  return { bookmarksFetchStatus, refreshBookmarks };
};

export default useGetBookmarks;
