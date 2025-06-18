function validateEmailEntry(email) {
  const emailString = String(email);

  const atPosition = emailString.indexOf("@");
  if (atPosition === -1 || atPosition !== emailString.lastIndexOf("@")) {
    return false;
  }

  if (atPosition === 0 || atPosition === emailString.length - 1) {
    return false;
  }

  const dotPosition = emailString.indexOf(".");
  if (dotPosition === -1) {
    return false;
  }

  if (dotPosition === emailString.length - 1) {
    return false;
  }

  const beforeDotPart = emailStr.substring(0, atIndex);
  const afterDotPart = emailStr.substring(atIndex + 1);

  if (beforeDotPart.includes(" ")) {
    return false;
  }

  const afterDotParts = afterDotPart.split(".");
  if (afterDotParts.length < 2) {
    return false;
  }

  for (const part of afterDotParts) {
    if (part.length === 0) {
      return false;
    }
    if (part.includes(" ") || part.includes("@")) {
      return false;
    }
  }
  return true;
}

function captilaize(inputText) {
  return inputText.charAt(0).toUpperCase() + inputString.slice(1).toLowerCase();
}
