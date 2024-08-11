import * as React from 'react';
import Box from '@mui/material/Box';
import { DataGrid, GridToolbar } from '@mui/x-data-grid';
import { useMovieData } from '@mui/x-data-grid-generator';
import { useState } from 'react';



export default function QuickFilteringGrid(p) {
    const [VISIBLE_FIELDS,setFields] = useState(['title', 'company', 'director', 'year', 'cinematicUniverse']); //p.fields
  const [data,setData] = useState( useMovieData()) // p.data useMovieData()
  
    
  // Otherwise filter will be applied on fields such as the hidden column id
  const columns = React.useMemo(
    () => data.columns.filter((column) => VISIBLE_FIELDS.includes(column.field)),
    [data.columns],
  );
  console.log(data)

  return (
    <Box sx={{ height: 400, width: 1 }}>
      <DataGrid
        {...data}
        disableColumnFilter
        disableColumnSelector
        disableDensitySelector
        columns={columns}
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