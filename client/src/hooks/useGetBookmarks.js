import { useState, useEffect } from "react";

const useGetBookmarks = (url) => {
  const [fetchStatus, setFetchStatus] = useState({
    bookmarks: [],
    isLoading: true,
  });

  useEffect(() => {
    // Define the request
    const fetchBookmarks = async () => {
      try {
        // Get bookmarks
        const response = await fetch(url);
        const bookmarks = await response.json();

        // Change state
        setFetchStatus({
          bookmarks,
          isLoading: false,
        });
      } catch (error) {
        console.log(error);
      }
    };

    // Invoke the request
    fetchBookmarks();
  }, [url]);

  return fetchStatus;
};

export default useGetBookmarks;
