import React, { useEffect, useState } from "react";

function State({ selectedCity, selectedStatePseudo, setSelectedStatePseudo, setSelectedCity }){
    const [states,setStates] = useState([]);
    const [selectedState,setSelectedState] = useState('')
    // const [selectedStatePseudo,setSelectedStatePseudo] = useState('')
    useEffect(()=>{
        fetch("http://192.168.0.73:32114/partner/get-states?countryCode=IN")
        .then(res => res.json())
        .then(d =>{
            let resp = JSON.parse(d["response"])
            setStates(resp)
        })
        .catch(err => console.log("Error in fetching states",err))
    },[])
    return(
        <>
            <label htmlFor="state" className="form"><p>State<sup style={{color:"red"}}>*</sup></p></label>
            <select id="state" name="state" value={selectedStatePseudo} onChange={(e)=>{
                setSelectedState(e.target.value)
                Object.keys(states).forEach((state)=>{
                    if(states[state] === e.target.value)
                    {
                        setSelectedStatePseudo(state);
                    }
                })
                }}>
                <option value="">Select State</option>
                {Object.keys(states).map((state)=>{
                    return <option key={state} value={states[state]}>{state}</option>
                })
            }
            </select>
            {<Cities state={selectedState} selectedCity={selectedCity} setSelectedCity={setSelectedCity}/>}
        </>
    )
}
export default State

function Cities({state, selectedCity, setSelectedCity}){
    const [cities,setCities] = useState([])
    const [loading,setLoading] = useState(true)
    // const [selectedCity, setSelectedCity] = useState("")
    useEffect(()=>{
        setLoading(true);
        console.log(state)
        fetch("http://192.168.0.73:32114/partner/get-cities-for-state?stateCode="+state)
        .then(res=>res.json())
        .then(
            states =>{
                let resp = {}
                try{
                    resp = JSON.parse(states["response"])
                }catch(e){
                    console.error("Error parsing city response", e);
                }
                setCities(resp || {});
                setLoading(false);
            }
        ).catch(err =>{
            console.error("Error fetching cities", err);
            setCities({});
            setLoading(false);
        });
    },[state])
    const isDisabled = loading || !Object.keys(cities).length;
    return(
        <>
            <label htmlFor="city" className="form"><p>City<sup style={{color:"red"}}>*</sup></p></label>
            <select id="city" name="city" disabled={isDisabled} value={selectedCity}  className={`city-dropdown ${isDisabled ? 'disabled' : ''}` } onChange={(e)=>setSelectedCity(e.target.value)}>
                <option value="">Select City</option>
                {cities && Object.keys(cities).map((city)=>{
                    return <option key={city} value={cities[city]}>{city}</option>
                })
            }
            </select>
        </>
    )
}

