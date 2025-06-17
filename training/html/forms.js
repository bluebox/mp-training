  function validateUsername() {
    const username = document.getElementById("username").value.trim();
    if (username.length < 3) {
      alert("Username must be at least 3 characters long.");
      return false;
    }
    return true;
  }
  