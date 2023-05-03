import React from 'react'
import EventSeatOutlinedIcon from '@mui/icons-material/EventSeatOutlined';
import EventSeatIcon from '@mui/icons-material/EventSeat';
import ChairOutlinedIcon from '@mui/icons-material/ChairOutlined';
import ChairIcon from '@mui/icons-material/Chair';
import './seat.css'
import Tooltip from '@mui/material/Tooltip';


const Seat = ({seatId,seatName,isBooked}) => {
    
  return (
    <div >
        <div>
          <Tooltip title={isBooked?"Booked":"Available"}>
            <div className='seatrow'>
            {isBooked?<ChairIcon  className="seaticon" fontSize='small'/>:<ChairOutlinedIcon 
            className='seat_icon_outline' fontSize='small'/>}
            <small className='seatnotext'>{seatName}</small>
            </div>
            </Tooltip>
         </div>    
    </div>
      )
}

export default Seat