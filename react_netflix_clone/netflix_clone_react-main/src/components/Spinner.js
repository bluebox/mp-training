import React, { useState,useEffect } from 'react'
import Backdrop from '@mui/material/Backdrop';
import CircularProgress from '@mui/material/CircularProgress';

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
export default function SimpleBackdrop() {
const [time,settime] = useState(true);
useEffect(() => {
    async function sleep2(){
        await sleep(1000);
        settime(false);
        return 0;
    }
    sleep2()
})
return(

    <div>
      <Backdrop
        sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={time}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
    </div>
  );
}
