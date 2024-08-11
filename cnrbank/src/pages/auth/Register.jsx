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

   function Register(){
    const {  accessTok ,refreshTok,username,logged} = useContext(BankContext);
    const navigate = useNavigate();

    const [formValues, setFormValues] = useState({
        username: '',
        password: '',
        email: '',
       });
       const getData = (e) => {
        const { value, name } = e.target;
        setFormValues(() => {
         return {
          ...formValues,
          [name]: value,
         };
        });
       };


       const handleSubmit = async () => {
        
        var res = fetch('http://localhost:9898/api/users/register', {
            method: 'POST',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(
                {
                    "username" : formValues.username,
                    "password" : formValues.password,
                    "email" : formValues.email,
                    "role" : "USER"
                }
            )
        })
            .then(async response => {
                const isJson = response.headers.get('content-type')?.includes('application/json');
                const data = isJson && await response.json();
    
                // check for error response
                if (!response.ok) {
                    // get error message from body or default to response status
                    const error = (data && data.message) || response.status;

                    return Promise.reject(error);
                }
                accessTok.current=data.access_token
                refreshTok.current=data.refresh_token
                username.current=formValues.username
                logged.current="yes"
                localStorage.setItem("cnrAccessTok",accessTok.current);
                localStorage.setItem("cnrRefreshTok",refreshTok.current);
                localStorage.setItem("cnrUsername",username.current);
                localStorage.setItem("cnrLogged",logged.current);
                console.log(data)
                console.log(accessTok.current)
                console.log(refreshTok.current)
                navigate('/dashboard');
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
      };


return(
        <>
   <Container component="main" maxWidth="xs">
    <Box>
     <Typography component="h1" variant="h5">
      Sign In
     </Typography>
     <Box component="form">      
       <TextField
        margin="normal"
        required
        fullWidth
        id="username"
        type="text"
        label="Username"
        name="username"
        autoComplete="username"
        autoFocus
        onChange={getData}
       />
       <TextField
        margin="normal"
        required
        fullWidth
        name="password"
        label="Password"
        type="password"
        id="password"
        autoComplete="current-password"
        onChange={getData}
       />   
       <TextField
        margin="normal"
        required
        fullWidth
        id="email"
        type="email"
        label="Email"
        name="email"
        autoComplete="email"
        autoFocus
        onChange={getData}
       />                     
      <FormControlLabel
       control={<Checkbox defaultChecked  value="remember" color="primary" />}
       label="Remember me"
      />
      <Button
       fullWidth
       variant="contained"
       sx={{ mt: 3, mb: 2 }}
       onClick={handleSubmit}>
       Sign Up
      </Button>  
     </Box>
    </Box>
   </Container>
  </>
        
    );




   }
   export default Register;