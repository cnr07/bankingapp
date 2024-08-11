import { createContext, useState,useRef } from 'react';

const BankContext = createContext({
 accessTok: "",
 refreshTok: "",
 username: "",
 logged: "",
 accounts:[]

});

const BankProvider = ({ children }) => {
 const accessTok = useRef(localStorage.getItem("cnrAccessTok")!==null?localStorage.getItem("cnrAccessTok"):"");
 const refreshTok = useRef(localStorage.getItem("cnrRefreshTok")!==null?localStorage.getItem("cnrRefreshTok"):"");
 const username = useRef(localStorage.getItem("cnrUsername")!==null?localStorage.getItem("cnrUsername"):"");
 const logged = useRef(localStorage.getItem("cnrLogged")!==null?localStorage.getItem("cnrLogged"):"no");
 const accounts = useRef([]);

 return (
   <BankContext.Provider value={{  accessTok, refreshTok,username,logged,accounts}}>
     {children}
   </BankContext.Provider>
 );
};

export { BankContext, BankProvider };