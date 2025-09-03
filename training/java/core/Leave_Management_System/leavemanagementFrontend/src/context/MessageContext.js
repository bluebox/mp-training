import React, { createContext, useContext, useState } from 'react';

const MessageContext = createContext(null);

export const useMessage = () => {
  return useContext(MessageContext);
};

export const MessageProvider = ({ children }) => {
  const [message, setMessage] = useState('');
  const [messageType, setMessageType] = useState(''); 

  const showMessage = (text, type) => {
    setMessage(text);
    setMessageType(type);
    setTimeout(() => {
      setMessage('');
      setMessageType('');
    }, 5000); 
  };

  const value = {
    message,
    messageType,
    showMessage,
  };

  return <MessageContext.Provider value={value}>{children}</MessageContext.Provider>;
};
