import React, { useState } from 'react'
import './bookingbox.css'
import axios from 'axios';
import { useStateValues } from './StateProvider';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { BOOKINGREQUEST, RESET } from './ApiLists';


const BookingBox = () => {
    const [phoneNum,setphoneNum]=useState("");
    const [userName,setUsername]=useState("");
    const [seatCount,setseatCount]=useState("1");
    const [errOcc,seterrOcc]=useState(null);
    
    const [state,dispatch]=useStateValues();
    

    const resetApicall=async()=>{
        await axios.get(RESET).then(
          (res)=>{
            window.location.reload()
          }
          ).catch((err)=>{
            
          });
    }
    
    const userSeatRequest= async (e)=>{
        e.preventDefault();
        const userreq={phoneNumber:phoneNum,userName:userName,noOfSeats:seatCount}
        await axios.post(BOOKINGREQUEST,userreq)
        .then((res)=>{
          dispatch({type:"SET_UPDATE_SEAT"})
          dispatch({type:"SET_UPDATE_USER"})
          
        }).catch((err)=>{
          console.log(err);
          if(err.response.data.Booking==="false"){
            toast(<h1 style={{color:"white"}}>
           Hey! Seats cannot be booked as per request, because of unavailability 
          </h1>);  
          }else{  
          toast(<h1 style={{color:"white"}}>
            Phone number should be 10 digits
            and
            UserName shoould be more than 2 character 
          </h1>);
          }
        });

        
    }
  return (
    <div>
    <div className='bookingbox'>
        <form method='post'>
            <div className='bookingboxphone'>
            <span>Enter your phone number</span>
            <input type='number' value={phoneNum} onChange={(e)=>setphoneNum(e.target.value)} placeholder='please enter your 10 digit phone number'/>
            </div>
            <div className='bookingboxname'>
            <span>Enter your Name</span>
            <input type='text' value={userName} onChange={(e)=>setUsername(e.target.value)} placeholder='please enter your Name'/>
            </div>
            <div className='bookingboxselect'>
            <span>Number of Seats</span>
            <select value={seatCount} onChange={(e)=>setseatCount(e.target.value)}  >
                <option value='1'>1</option>
                <option value='2'>2</option>
                <option value='3'>3</option>
                <option value='4'>4</option>
                <option value='5'>5</option>
                <option value='6'>6</option>
                <option value='7'>7</option>
            </select>
            </div>
            <button type='submit' onClick={(e)=>userSeatRequest(e)}>BOOK NOW</button>
            </form>
    </div>
    <ToastContainer autoClose={2000} toastStyle={{ backgroundColor: "red" }} position='bottom-left' className="toast" />
    <div className='bookingboxreset'>
      <h3>If you want to reset the coach seats, as well as users registered CLICK HERE! </h3>
      <small style={{fontSize:"10px",color:"red"}}>(REMEBER THIS CANNOT BE UNDONE)</small>
      <button onClick={()=>resetApicall()}>RESET</button>
    </div>
    </div>
  )
}

export default BookingBox