
import React, { useContext, useEffect, useState } from "react";
import {Overlay,Tooltip} from "react-bootstrap";
import { UserContext } from "../components/Contexts/UserContext";
import Validate from "./Validate";

const KeyboardShortcuts = (props) => {
    const [showEScTooltip , setShowEscToolTip] = useState(false);
    const [escAction,setEscAction] = useState(null);
    const [customTooltipText, setCustomTooltipText] = useState(props.customTooltipText);
    const userSessionInfo = useContext(UserContext);
    
    useEffect(() => {
        document.addEventListener("keydown", handleUserKeyPress)
        return () => {
            document.removeEventListener("keydown", handleUserKeyPress);
        }
    }, [])
    useEffect(()=> {
        if(props.handleEsc){
            setEscAction("esc")
        }
        if(Validate().isNotEmpty(props.delayTransition))
            setTimeout(() => {
                setShowEscToolTip(true);
            }, props.delayTransition);
        else
            setShowEscToolTip(true);
    },[])
    const CloseToolTip = () => {
        setTimeout(()=>{
            setShowEscToolTip(!showEScTooltip)
        },4000)
    }
    const handleUserKeyPress = (event) => {
        const { key, keyCode, altKey } = event;

        {if (keyCode == 27 && props.handleEsc) {
            props.setOpenModal();
        }}
        if(altKey == true && keyCode == 68 && props.handleAltD){
            event.preventDefault()
            props.opengrid();
        }
    }
    if(userSessionInfo.vertical == "V"){
        return ""
    }

    return (
        <>
            <Overlay
                show={showEScTooltip}
                rootClose onEntered={CloseToolTip} placement={(props.placement) ? props.placement : "bottom"}
                target={() => document.getElementById(props.buttonId)}
            >
                {(props) => (
                    <Tooltip id="overlay-example" {...props}>
                        {Validate().isNotEmpty(customTooltipText) ? customTooltipText : (escAction=="esc") ? "Press ESC to close" : "Press ALT + D to Toggle"}
                    </Tooltip>
                )}
            </Overlay>
        </>
    );
}

export default KeyboardShortcuts