export const getMonthName = (monthNumber) => {
  const monthNames = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];
  return monthNames[monthNumber - 1];
};

// Function accepts string with date in format "yyyy-mm-dd"
export const extractYearMonthDay = (dateString) => {
  // Separate year, month, day
  const dateArray = dateString.split("-");
  const year = dateArray[0];
  const month = getMonthName(dateArray[1]);
  const date = dateArray[2];

  return { year, month, date };
};
