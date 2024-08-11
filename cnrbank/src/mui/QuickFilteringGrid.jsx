import * as React from 'react';
import Box from '@mui/material/Box';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import { useMovieData } from '@mui/x-data-grid-generator';
import { useState } from 'react';



export default function QuickFilteringGrid({rows,columns}) {
    const [VISIBLE_FIELDS,setFields] = useState(columns); //p.fields [{field:'title'}]
  const [data,setData] = useState( rows) // p.data useMovieData()
  
    
  // Otherwise filter will be applied on fields such as the hidden column id
  /*const columns = React.useMemo(
    () => data.columns.filter((column) => VISIBLE_FIELDS.includes(column.field)),
    [data.columns],
  );*/
  //console.log(data)
  console.log(rows)

  return (
    <Box sx={{ height: 400, width: 1 }}>
      <DataGrid
        rows={data}
        disableColumnFilter
        disableColumnSelector
        disableDensitySelector
        columns={VISIBLE_FIELDS}
        slots={{ toolbar: GridToolbar }}
        slotProps={{
          toolbar: {
            showQuickFilter: true,
          },
        }}
      />
    </Box>
  );
}