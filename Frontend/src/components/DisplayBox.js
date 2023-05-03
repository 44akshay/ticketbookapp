import React, { useEffect, useState } from 'react'
import axios from 'axios';
import './displaybox.css'
import Shimmerui from './Shimmerui';
import { useStateValues } from './StateProvider';
import { GETALLUSERS } from './ApiLists';
const DisplayBox = () => {
  
  const [userInfo,setuserInfo]=useState(null);
  const [state,dispatch]=useStateValues();

  useEffect(()=>{
    userApiCall();
  },[])

  useEffect(()=>{
    if(state.isBookingUserUpdate===true){
      userApiCall();
    }
  },[state])
  if(userInfo===null){
     return <div className='shimmerbox shimmerboxloading'><Shimmerui/></div>
  }
  if(state.isBookingUserUpdate===true){
    return <div className='shimmerbox shimmerboxloading'><Shimmerui/></div>
  }

  async function userApiCall(){
      const resdata=await axios.get(GETALLUSERS)
      .then((res)=>{
        setuserInfo(res.data);
        dispatch({type:"RESET_UPDATE_USER"});
      })
      .catch((err)=>{

      });
  }

  return (
    <div>
        <div className='displayboxparent'>
        <div className='displaybox'>
            <h1>BOOKED USERS</h1>
            {userInfo.length===0?<h1>No user has booked this coach</h1>:
            userInfo.map((item,key)=>{
              return (
              <div key={key} className='displayuserinfo'>
                <div>
                  <small>{item.userName} ({item.userPhone}) has <span style={{color:"green",fontWeight:"bold"}}>Booked</span>  </small>
                </div>
                <div>  
                  {
                    item.seatNums.map((seats,index)=>{
                     return <small style={{fontWeight:"bold"}} key={index}>{seats+", "}</small>
                    })
                  }
                </div>
                <hr/>
            </div>);
            })
          }
            
        </div>
        

        </div>
    </div>
  )
}

export default DisplayBox