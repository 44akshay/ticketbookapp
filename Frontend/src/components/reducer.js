export const initialState={
    isseatUpdate:false,
    isBookingUserUpdate:false,
}
export const reducer=(state,action)=>{
    switch(action.type){
        case "SET_UPDATE_SEAT":
            return {
                ...initialState,
                isseatUpdate:true
            }
        case "RESET_UPDATE_SEAT":
            return {
                ...initialState,
                isseatUpdate:false
            }
        case "SET_UPDATE_USER":
            state.isBookingUserUpdate=true;
        return state
        case "RESET_UPDATE_USER":
            state.isBookingUserUpdate=false;
        return state
        
        
        default:
            return state;
    }
}