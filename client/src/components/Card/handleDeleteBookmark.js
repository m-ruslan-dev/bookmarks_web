const handleDeleteBookmark = async (
  event,
  bookmarkId,
  csrfToken,
  refreshBookmarks
) => {
  event.preventDefault();

  // Send DELETE request to the server
  const response = await fetch("http://localhost:8080/bookmarks", {
    method: "DELETE",
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
      "X-Requested-With": "XMLHttpRequest",
      "X-XSRF-TOKEN": csrfToken,
    },
    body: JSON.stringify({
      id: bookmarkId,
    }),
  });

  // On deletion success, update the bookmarks
  if (response.ok) {
    refreshBookmarks();
  }
};

export default handleDeleteBookmark;
