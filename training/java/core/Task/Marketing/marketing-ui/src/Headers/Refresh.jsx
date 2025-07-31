import { useContext, useEffect, useState } from 'react';
import Dropdown from 'react-bootstrap/Dropdown';
import Spinner from 'react-bootstrap/Spinner';
import MarketingService from '../services/MarketingService';
import { AlertContext } from '../components/Contexts/UserContext';
import { useDispatch } from 'react-redux';
import { CLEAR_ALL } from '../redux/reducer';

const Refresh = (props) => {
    const [refreshAction,setRefreshAction] = useState(false);
    const {setToastContent} = useContext(AlertContext);
    const dispatch = useDispatch();
    const handleOnClickRefresh = (event) => {
        event.stopPropagation();
        setRefreshAction(true);
        clearCacheEvent();
    }

    useEffect(()=>{
      document.addEventListener('mousedown', handleClickOutside);
      return () => {
        document.removeEventListener('mousedown', handleClickOutside);
      };
    },[]);

    const handleClickOutside = () => {
      setRefreshAction(undefined)
    }

    const clearCacheEvent = async () => {
      dispatch({type:CLEAR_ALL});
      const data = await MarketingService().clearCache();
      setToastContent({toastMessage: data.message , className:'w-100'});
      setRefreshAction(false);
    }

    return(
        props.cronName && <Dropdown>
            <Dropdown.Toggle variant=" " id="dropdown-settings" className="custom-btn-dropdown p-0">
             <div className="dropdown">
                <button className="mx-3 btn btn-link p-1 icon-hover" /* onClick={() => setRefreshAction({refresh:false, isRefreshing:false, refresh: false})} */>
                  <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
                    <path id="noun-settings-5079167-404040" d="M101.019,90a1.952,1.952,0,0,0-1.941,1.941v.717a9.791,9.791,0,0,0-1.637.677l-.506-.506a1.953,1.953,0,0,0-2.745,0L92.819,94.2a1.953,1.953,0,0,0,0,2.745l.506.506a9.789,9.789,0,0,0-.677,1.637h-.717a1.952,1.952,0,0,0-1.941,1.941v1.942a1.952,1.952,0,0,0,1.941,1.941h.717a9.781,9.781,0,0,0,.677,1.637l-.506.506a1.953,1.953,0,0,0,0,2.745l1.372,1.372a1.953,1.953,0,0,0,2.745,0l.506-.506a9.786,9.786,0,0,0,1.637.677v.717A1.952,1.952,0,0,0,101.019,114h1.942a1.952,1.952,0,0,0,1.941-1.941v-.717a9.784,9.784,0,0,0,1.637-.677l.506.506a1.953,1.953,0,0,0,2.745,0l1.372-1.372a1.953,1.953,0,0,0,0-2.745l-.506-.506a9.781,9.781,0,0,0,.677-1.637h.717a1.952,1.952,0,0,0,1.941-1.941v-1.942a1.952,1.952,0,0,0-1.941-1.941h-.713a9.792,9.792,0,0,0-.679-1.639l.5-.5a1.953,1.953,0,0,0,0-2.745l-1.372-1.372a1.953,1.953,0,0,0-2.745,0l-.5.5a9.8,9.8,0,0,0-1.639-.679v-.712A1.952,1.952,0,0,0,102.961,90Zm0,1.412h1.942a.512.512,0,0,1,.529.529v1.246h0a.706.706,0,0,0,.534.685,8.38,8.38,0,0,1,2.276.943h0a.706.706,0,0,0,.862-.106l.881-.881a.513.513,0,0,1,.749,0l1.372,1.372a.513.513,0,0,1,0,.749l-.881.881a.706.706,0,0,0-.106.862,8.378,8.378,0,0,1,.943,2.276.706.706,0,0,0,.685.534h1.246a.512.512,0,0,1,.529.529v1.942a.512.512,0,0,1-.529.529H110.8a.706.706,0,0,0-.685.535,8.381,8.381,0,0,1-.941,2.274.706.706,0,0,0,.106.862l.883.883a.513.513,0,0,1,0,.749l-1.372,1.372a.513.513,0,0,1-.749,0l-.883-.883a.706.706,0,0,0-.862-.106,8.384,8.384,0,0,1-2.274.941h0a.706.706,0,0,0-.535.685v1.25a.512.512,0,0,1-.529.529h-1.942a.512.512,0,0,1-.529-.529v-1.25h0a.706.706,0,0,0-.535-.685,8.384,8.384,0,0,1-2.274-.941h0a.706.706,0,0,0-.862.106l-.883.883a.513.513,0,0,1-.749,0L93.817,108.8a.513.513,0,0,1,0-.749l.883-.883a.706.706,0,0,0,.106-.862,8.38,8.38,0,0,1-.941-2.274.706.706,0,0,0-.685-.535h-1.25a.512.512,0,0,1-.529-.529v-1.942a.512.512,0,0,1,.529-.529h1.25a.706.706,0,0,0,.685-.535,8.382,8.382,0,0,1,.941-2.273h0a.706.706,0,0,0-.106-.862l-.883-.883a.513.513,0,0,1,0-.749l1.372-1.372a.513.513,0,0,1,.749,0l.883.883a.706.706,0,0,0,.862.107,8.38,8.38,0,0,1,2.274-.941h0a.706.706,0,0,0,.535-.685v-1.25a.512.512,0,0,1,.529-.529Zm.971,4.548a6.04,6.04,0,1,0,6.04,6.04A6.051,6.051,0,0,0,101.99,95.96Zm0,1.412A4.628,4.628,0,1,1,97.362,102,4.618,4.618,0,0,1,101.99,97.371Z" transform="translate(-89.99 -90)" fill="#3f3f3f" fill-rule="evenodd" />
                  </svg>
                </button>
              </div>
            </Dropdown.Toggle>
            <Dropdown.Menu className="custom-dropdown custom-dropdown-menu dropdown-menu-start">
              <Dropdown.Item  className="custom-dropdown-item" href="javascript:void(0)">
                <div className="d-flex justify-content-between align-items-center w-100">
                    <p className="mb-0" onClick={handleOnClickRefresh}>{props.cronName}</p>
                    <div className="d-fcronNamelex align-items-center">
                    {refreshAction && <Spinner animation="border" size="sm" />}
                    {refreshAction !== undefined && !refreshAction && <button className="btn btn-sm">
                    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18">
                      <g id="tickmark_black_icon_18px" transform="translate(-1458 -558)">
                      <rect id="Rectangle_5706" data-name="Rectangle 5706" width="18" height="18" rx="3" transform="translate(1458 558)" fill="#fff"/>
                      <path id="check" d="M18,6,8.375,15.625,4,11.25" transform="translate(1456 556)" fill="rgba(0,0,0,0)" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" stroke="green"/>
                      </g>
                    </svg>
                    </button>}
                    </div>
                </div>
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
    )
   
}

export default Refresh;