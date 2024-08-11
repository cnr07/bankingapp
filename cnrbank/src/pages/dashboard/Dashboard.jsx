import {
    Box,
    Button,
    Checkbox,
    Container,
    FormControlLabel,
    Grid,
    Link,
    Typography,
    TextField
   } from '@mui/material';
   import React, { useState,useEffect } from 'react';
   import { useQuery } from 'react-query';
   import { useContext } from 'react';
   import { BankContext } from '../.././context/BankContext';
   import { useNavigate } from 'react-router-dom';
   import QuickFilteringGrid from '../../mui/QuickFilteringGrid';
   import { useRef } from 'react';


   



function Dashboard(){
    const {  accessTok ,refreshTok,username,logged,accounts} = useContext(BankContext);
    const navigate = useNavigate();
    const accountsKeys = useRef([]);
    const tableData = useRef([]);

    
    const fetchAccounts = async () => {
        var res = fetch('http://localhost:9898/api/accounts', {
            method: 'POST',
            headers:{'Content-Type': 'application/json','Authorization': 'Bearer '+accessTok.current},
            body: JSON.stringify(
                {
                    "username" : username.current
                }
            )
        }).then(async response => {
            const isJson = response.headers.get('content-type')?.includes('application/json');
            const data = isJson && await response.json();
    
            // check for error response
            if (!response.ok) {
                // get error message from body or default to response status
                const error = (data && data.message) || response.status;
                return Promise.reject(error);
            }
            accounts.current=data
            accountsKeys.current=Object.keys(data[0]);
            
            
            console.log(accountsKeys.current)
            console.log(accounts.current)
        })
        .catch(error => {
            console.error('There was an error!', error);
        });
    }

    useEffect(() => {
        fetchAccounts();
       },[])

    

    return(
        <>
        <Box>
            <Typography marginBottom={2} >Accounts</Typography>
            <QuickFilteringGrid  data={tableData.current} fields={accountsKeys.current} ></QuickFilteringGrid>
        </Box>
        </>
    );

}
export default Dashboard;