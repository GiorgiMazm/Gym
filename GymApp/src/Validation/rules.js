export default {
  exist: function (value) {
    if (value) {
      return true;
    }
    return "Field is required!";
  },

  isValidNumber: function (value) {
    if (+value[0] === 0) return "Can not start with 0";
    if (value > 0) {
      return true;
    }
    return "Is not a valid number bigger then 0";
  },
};
