import { createContext, useState,useRef } from 'react';

const BankContext = createContext({
 accessTok: "",
 refreshTok: "",
});

const BankProvider = ({ children }) => {
 const accessTok = useRef("");
 const refreshTok = useRef("");

 return (
   <BankContext.Provider value={{  accessTok, refreshTok}}>
     {children}
   </BankContext.Provider>
 );
};

export { BankContext, BankProvider };