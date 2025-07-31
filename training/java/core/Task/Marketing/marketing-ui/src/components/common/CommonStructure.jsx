import React , { useState , useEffect, useContext} from "react";
import { CustomSpinners, StackedToasts } from "@medplus/react-common-components/DynamicForm";
import Validate from "../../helpers/Validate";
import { SidebarContext ,AlertContext } from "../Contexts/UserContext";
import { ALERT_TYPE, CustomAlert, CustomToast, TOAST_POSITION } from '@medplus/react-common-components/DynamicForm';
import CloseIcon from '../../images/close_white_icon_24px.svg'

export const HeaderComponent = React.forwardRef((props, ref) => (
    <header ref={ref} {...props}>
    </header>
));


export const FooterComponent = React.forwardRef((props, ref) => (
    <footer ref={ref} {...props}></footer>
    ));
    
    export const BodyComponent = (props) => {
        const {sidebarCollapsedFlag} = useContext(SidebarContext) ;
        const [calculateheight , setCalculateHeight] = useState(0)
    useEffect(() => {
        if(props.allRefs && props.allRefs.headerRef && props.allRefs.headerRef.current){
            setCalculateHeight(props.allRefs.headerRef.current.offsetHeight)
        }
        if(props.allRefs && props.allRefs.footerRef && props.allRefs.footerRef.current){
            setCalculateHeight(props.allRefs.footerRef.current.offsetHeight)
        }
        if(props.allRefs && props.allRefs.headerRef && props.allRefs.headerRef.current && props.isFooterRequired){
            setCalculateHeight(props.allRefs.headerRef.current.offsetHeight + 64)
        }
        if(props.allRefs && props.allRefs.headerRef && props.allRefs.headerRef.current && props.allRefs.footerRef &&  props.allRefs.footerRef.current){
            setCalculateHeight(props.allRefs.headerRef.current.offsetHeight + props.allRefs.footerRef.current.offsetHeight)
        }
      },[props]);

      return (
        <React.Fragment>
            <div style={{ 'height': `calc(100% - ${calculateheight}px )` }} className={props.className}>
                {(props?.showChildren == undefined || props?.showChildren) && props.children}
                {Validate().isNotEmpty(props.loading) && props.loading && <CustomSpinners outerClassName={"align-items-center d-flex custom-spinner flex-column"} innerClass={"custom-spinner-text-width"} animation="border" variant="brand" spinnerText='Please be patient while we prepare your data for loading!' />}
            </div>
        </React.Fragment>
    )
}

export const Wrapper =({showHeader,children, ...props} ) => {
    
    const {alertContent, setAlertContent, toastContent, setToastContent, stackedToastContent, setStackedToastContent} = useContext(AlertContext);
    const validate = Validate();

    const onAlertClose = () =>{
        setAlertContent({isShow: false});
        alertContent.handleCallBack && alertContent?.handleCallBack();

    }

    const onToastClose = () =>{
        setToastContent({isShow:false});
    //    toastContent.handleCallBack && toastContent?.handleCallBack(toastContent.path);
    }

    return (
        <React.Fragment>
            <div className={`custom_gridContainer custom_gridContainer_fullwidth_forms ${validate.isNotEmpty(props.className) && props.className}`}>
            { validate.isNotEmpty(alertContent) && validate.isNotEmpty(alertContent.alertMessage) && <div className="header">
                    <CustomAlert
                        alertType={alertContent.alertType ? alertContent.alertType : ALERT_TYPE.ERROR}
                        isDismissibleRequired={validate.isNotEmpty(alertContent.isDismissibleRequired) ? alertContent.isDismissibleRequired : true}
                        alertMessage={() => alertContent.alertMessage ? alertContent.alertMessage : ""}
                        isAutohide={validate.isNotEmpty(alertContent.isAutohide) ? alertContent.isAutohide : true}
                        delayTime={alertContent.delayTime ? alertContent.delayTime : 6000}
                        isShow={validate.isNotEmpty(alertContent.isShow) ? alertContent.isShow : validate.isNotEmpty(alertContent.alertMessage)}
                        onClose={alertContent.onClose ? alertContent.onClose : onAlertClose}
                        isTransition={validate.isNotEmpty(alertContent.isTransition) ? alertContent.isTransition : true}
                    />
                </div>}
                <div className="forms">
                    {children}
                   {validate.isNotEmpty(toastContent) && validate.isNotEmpty(toastContent.toastMessage) && <CustomToast 
                    position= {toastContent.position ? toastContent.position : TOAST_POSITION.BOTTOM_START}
                    className= {toastContent.className ? toastContent.className : "p-3"}
                    isShow= {validate.isNotEmpty(toastContent.isShow) ?  toastContent.isShow : validate.isNotEmpty(toastContent.toastMessage)}
                    delayTime= {toastContent.delayTime ? toastContent.delayTime : 4000}
                    isAnimationRequired= {validate.isNotEmpty(toastContent.isAnimationRequired) ? toastContent.isAnimationRequired : true}
                    isAutohide = {validate.isNotEmpty(toastContent.isAutohide) ? toastContent.isAutohide : true}
                    backgroundColor = {toastContent.backgroundColor ? toastContent.backgroundColor : "dark"}
                    bodyContent = { () => toastContent.toastMessage ? <div className="d-flex justify-content-between"><p className="mb-0 text-white">{toastContent.toastMessage}</p> <img className="pointer" onClick={onToastClose} src={CloseIcon} alt="Close Icon"/></div> : undefined}
                    onClose =  { toastContent.onClose ? toastContent.onClose : onToastClose}
                />}
                    {validate.isNotEmpty(stackedToastContent) && validate.isNotEmpty(stackedToastContent.toastMessage) &&
                        <StackedToasts
                            setStackedToastContent={setStackedToastContent}
                            position={stackedToastContent.position && stackedToastContent.position != TOAST_POSITION.BOTTOM_START ? stackedToastContent.position : TOAST_POSITION.BOTTOM_START}
                            className={stackedToastContent.className ? stackedToastContent.className : "p-3"}
                            delayTime={stackedToastContent.delayTime ? stackedToastContent.delayTime : 4000}
                            backgroundColor={stackedToastContent.backgroundColor ? stackedToastContent.backgroundColor : "dark"}
                            bodyContent={stackedToastContent.toastMessage.length > 0 ? stackedToastContent.toastMessage : undefined}
                            onClose={stackedToastContent.onClose ? stackedToastContent.onClose : null}
                            showUndo={stackedToastContent.showUndo ? stackedToastContent.showUndo : null}
                            handleUndoClick={stackedToastContent.handleUndoClick ? stackedToastContent.handleUndoClick : null}
                            closeOnUndoClick={stackedToastContent.closeOnUndoClick ? stackedToastContent.closeOnUndoClick : false}
                        />
                    }
                </div>
            </div>
        </React.Fragment>
    )
}


const CommonStructure = () => {
    
    return (
        <React.Fragment>

        </React.Fragment>
    )
}

export default CommonStructure