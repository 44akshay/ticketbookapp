import React, { useEffect, useState } from 'react'
import Seat from './Seat'
import './seat.css'
import Shimmerui from './Shimmerui'
import { useStateValues } from './StateProvider'
import { GETALLSEATSTATUS } from './ApiLists'


const SeatBox = () => {

    const [AllSeats,setAllSeats]=useState(null);
    const [state,dispatch]=useStateValues();
    useEffect(()=>{
        getAllSeatApiCall();
        
    },[]);
    useEffect(()=>{
      if(state.isseatUpdate===true){
        getAllSeatApiCall();
        console.log("4");
      }
    },[state])

     
     if(state.isseatUpdate===true){
      return <div className='shimmerseat boxshimmerloading'><Shimmerui/></div>;
     }

    async function getAllSeatApiCall(){
        const resData=await (await fetch(GETALLSEATSTATUS)).json();
        setAllSeats(resData);
        dispatch({type:"RESET_UPDATE_SEAT"});
    }
    if(AllSeats==null){
        return <div className='shimmerseat boxshimmerloading'><Shimmerui/></div>;
    }

  return (
    
    <div className="seat">
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="A")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="B")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="C")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="D")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="E")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="F")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="G")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="H")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="I")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="J")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="K")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>
      <div className="seatcol">
      {AllSeats.map((elem)=>{
        if(elem.seatName.charAt(0)==="L")return <div key={elem.seatId} className="seatcol"><Seat {...elem} /></div>
      })}
      </div>

      </div>
    
  )
}

export default SeatBox