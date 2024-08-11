import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';

import { useContext } from 'react';
import { BankContext } from '../.././context/BankContext';

export default function ButtonAppBar() {
  const {  accessTok ,refreshTok,username,logged} = useContext(BankContext);
  return (
    <Box marginBottom={7} sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            
          </IconButton>
          <Typography display={'flex'} marginRight={100} variant="h6" component="div" sx={{ flexGrow: 1 }}>
            CNRBANK
          </Typography>
          {logged.current==="yes"&&<Button color="inherit">Logout</Button>}
        </Toolbar>
      </AppBar>
    </Box>
  );
}