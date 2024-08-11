import { useState } from 'react'
import './App.css'
import React from "react";
import Login from "./pages/auth/Login"
import Copyright from "./pages/footer/Copyright"
import ButtonAppBar from "./pages/header/ButtonAppBar"
import { useParams } from "react-router-dom";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { BankProvider } from './context/BankContext';
import Dashboard from './pages/dashboard/Dashboard';
import Register from './pages/auth/Register';

function App() {
  const {id} = useParams()
  return (
    <BankProvider>
    <BrowserRouter basename='/cnrbank'>
          <ButtonAppBar></ButtonAppBar>
          <Routes>
            <Route path='/' element={<Login></Login>} />
            <Route path='/register' element={<Register></Register>} />
            <Route path='/dashboard' element={<Dashboard></Dashboard>} />
            <Route path="customer">
              <Route path=":id" element={<Login/>}/>
              </Route>
            </Routes>
            <Copyright></Copyright>
        </BrowserRouter>
    </BankProvider>
    
    
  )
}

export default App
