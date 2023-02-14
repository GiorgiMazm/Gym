export default {
  exist: function (value) {
    if (value) {
      return true;
    }
    return "Field is required!";
  },
};
