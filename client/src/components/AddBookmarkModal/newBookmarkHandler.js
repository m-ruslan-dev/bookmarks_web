export const newBookmarkHandler = async (
  event,
  csrfToken,
  refreshBookmarks
) => {
  event.preventDefault();

  // Get input values
  const formData = new FormData(event.target);
  const title = formData.get("bookmark_title");
  const link = formData.get("bookmark_link");
  const collection = formData.get("new_collection");

  const bookmarkData = {
    title,
    link,
    collection,
  };
  // Send bookmark data
  try {
    await fetch("http://localhost:8080/bookmarks", {
      method: "POST",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
        "X-XSRF-TOKEN": csrfToken,
      },
      body: JSON.stringify(bookmarkData),
    });

    refreshBookmarks();
  } catch (error) {
    console.log(error);
  }
};
