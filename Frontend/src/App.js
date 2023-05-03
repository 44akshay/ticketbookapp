import BookingBox from "./components/BookingBox";
import DisplayBox from "./components/DisplayBox";
import Seat from "./components/Seat";
import SeatBox from "./components/SeatBox";
import { StateProvider } from "./components/StateProvider";
import './components/layout.css'
import { initialState, reducer } from "./components/reducer";

function App() {
  

  
  return (
    <div className="applayout">
      <StateProvider initialState={initialState} reducer={reducer} >
      <BookingBox/>
      <SeatBox/>
      <DisplayBox/>
      </StateProvider>
    </div>
  );
}

export default App;
