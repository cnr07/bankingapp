import Link from '@mui/material/Link'
import Typography from "@mui/material/Typography";
import React from "react";



function Copyright() {
    return (
      <Typography variant="body2" color="textSecondary" align="center">
        {"Copyright © "}
        <Link color="inherit" href="https://github.com/cnr07">
          Can Erdoğan
        </Link>{" "}
        {new Date().getFullYear()}
        {"."}
      </Typography>
    );
  }
  export default Copyright;