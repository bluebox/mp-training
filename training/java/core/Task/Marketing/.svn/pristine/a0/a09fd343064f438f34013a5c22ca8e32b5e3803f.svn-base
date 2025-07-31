import { ALERT_TYPE, withFormHoc } from '@medplus/react-common-components/DynamicForm';
import React, { useContext, useEffect, useState } from "react";
import { Button } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { UncontrolledTooltip } from "reactstrap";
import { PromotionStatus } from '../../constants/PromotionConstants';
import ResponseHandler from '../../helpers/ResponseHandler';
import Validate from '../../helpers/Validate';
import RemoveIcon from "../../images/Remove_icon.svg";
import { SET_CATEGORIES } from '../../redux/constants';
import MarketingService from '../../services/MarketingService';
import { AlertContext } from '../Contexts/UserContext';
import NextPrevBtn from '../common/NextPrevButtons';

const ProductCategory = (props) => {
  const validate = Validate();
  const setLoading = props.setLoading;
  let categoryInfoData = {};
  const [disableCategories, setDisableCategories] = useState(false);
  const { setAlertContent } = useContext(AlertContext);
  const [selectedCategoryTypes, setSelectedCategoryTypes] = useState({})
  const [newCategoryTypes, setNewCategoryTypes] = useState({});
  const dispatch = useDispatch();
  const  categoryTypesRedux = useSelector((state) =>  validate.isNotEmpty(state.categoryReducer)? state.categoryReducer : undefined);

  const handleNextBtnClick = () => {
      categoryInfoData = Object.keys(selectedCategoryTypes);
      if(Validate().isNotEmpty(categoryInfoData) && categoryInfoData.length > 0) {
        // if (Validate().isNotEmpty(props.category)) {
        //   const categorySet = new Set(props.category);
        //   const newCategories = categoryInfoData.filter(cat => !categorySet.has(cat));
        // }
          props.handleCampaignInfoChange({"categoryType": categoryInfoData}, props.tabDetails.currentTab.tabId, props.tabDetails.nextTab.tabId);
      } else {
          setAlertContent({alertType: ALERT_TYPE.ERROR,alertMessage: "Select atleat one category"})
      }
      return false;
  }

  const handlePrevBtnClick = () => {
      categoryInfoData = Object.keys(selectedCategoryTypes);
      props.handleCampaignInfoChange({"categoryType": categoryInfoData}, props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);
  }


  const addOrRemoveCurrentProcess = (key, value) => {
      if (selectedCategoryTypes[key] === value) {
          delete selectedCategoryTypes[key];
      } else {
          selectedCategoryTypes[key] = value
      }
      setSelectedCategoryTypes({...selectedCategoryTypes});
  };

  const [isChecked, setIsChecked] = useState(false);

  const handleCheckboxChange = (event) => {
      setIsChecked(event.target.checked);
      if (event.target.checked) {
          setSelectedCategoryTypes({...newCategoryTypes});
      } else {
          setSelectedCategoryTypes([])
      }
  };

  const getCategoryTypes = async () => {
      let categoriesObj = categoryTypesRedux;
      console.log('getting categories from redux: ')
      if (validate.isEmpty(categoriesObj) || Object.keys(categoriesObj).length <= 0) {
          console.log('getting categories from server');
          const response = await MarketingService().getInvoiceCategoryTypes().catch(error => {
              setAlertContent({ alertType: ALERT_TYPE.ERROR, alertMessage: 'Unable to fetch categories' });
          });
          ResponseHandler(setAlertContent).handleResponse(response, {}, (data) => {
              categoriesObj = data;
              dispatch({ type: SET_CATEGORIES, payload: data });
          }, (error) => {
              setAlertContent({ alertType: ALERT_TYPE.ERROR, alertMessage: error })
          });
      }
      setNewCategoryTypes(categoriesObj);
      console.log('getting category types: ')

      if (Validate().isNotEmpty(props.category)) {
          let selectedCategories = {}
          props.category.forEach(element => {
              selectedCategories[element] = categoriesObj[Number(element)]
          });
          (Object.keys(categoriesObj).length === Object.keys(selectedCategories).length) ? setIsChecked(true): setIsChecked(false);
          setSelectedCategoryTypes({...selectedCategories})
      }
  }

  useEffect(() => {
      if (Object.keys(newCategoryTypes).length !== Object.keys(selectedCategoryTypes).length) {
          setIsChecked(false);
      }
  }, [newCategoryTypes, selectedCategoryTypes])

  useEffect(() => {
      setLoading(true);
      if (Validate().isEmpty(newCategoryTypes)) {
          getCategoryTypes();
      }
      if(props.actionPermissions.isApprover || props.globalPromotionStatus=== PromotionStatus.active) {
        setDisableCategories(true);
      }
      setLoading(false);
  }, [props.category])


  return (
    <>
      <React.Fragment key={`${props.location.pathname}`}>
   { Object.keys(newCategoryTypes).length >0 && <>
          <div className='overflow-scroll row' style={{height:"calc(100% - 63px)"}}>
            <label className='custom-fieldset mb-2'>Select Category Types</label>
            <div className="d-flex align-items-baseline mb-3">
              <div class="form-check ps-0 me-3">
                <input disabled={disableCategories}
                  class="form-check-input ms-0 me-2 pointer"
                  type="checkbox"
                  checked={isChecked}
                  name="flexRadioDefault1"
                  id={"flexRadioDefault1"}
                  onClick={(event) => handleCheckboxChange(event)}
                />
                <label class="form-check-label small" for="flexRadioDefault1">
                  Select all the Category Types
                </label>
              </div>
            </div>
            <div className="row">
              <div className="d-grid gap-2 col">
              {newCategoryTypes &&
                  Object.entries(newCategoryTypes).map(
                  ([categoryKey, categoryValue]) => {
                  return (
                  <>
                      <Button disabled={disableCategories} variant="light" id={`partner_process_config_processtype_button_${categoryKey}`} key={categoryKey} className="rounded-5 mb-2" value={categoryKey} onClick={() => { addOrRemoveCurrentProcess(Number(categoryKey),categoryValue) }} >
                          <div className="d-flex justify-content-between align-items-center">
                              <span className="text-secondary font-14" value={categoryKey}>{categoryValue}</span>
                              <svg aria-hidden="true" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" value={categoryKey} >
                                  <path id="Icon_material-check-circle" data-name="Icon material-check-circle" d="M15,3A12,12,0,1,0,27,15,12,12,0,0,0,15,3ZM12.6,21l-6-6,1.692-1.692L12.6,17.6,21.708,8.5,23.4,10.2Z" transform="translate(-3 -3)" fill={ Validate().isNotEmpty(selectedCategoryTypes[categoryKey]) ? "#11B094" : "#ced4da"} />
                              </svg>
                          </div>
                      </Button>
                  </>
                  )
              })}
              </div>
              <div className="col">
                {Validate().isNotEmpty(selectedCategoryTypes) &&
                   (
                    <div>
                      <p className="font-14 font-weight-bold title">
                        Current Selected Category Types
                      </p>
                      <div className="d-flex flex-wrap gap-3">
                      {Object.keys(selectedCategoryTypes).map((index, idx) => {
                          let item=selectedCategoryTypes[index]
                          return (
                            <React.Fragment>
                              <Button disabled={disableCategories}
                                variant="light"
                                className="rounded-5"
                              >
                                <span
                                  aria-hidden="true"
                                  className="text-muted font-14"
                                >
                                  {item}
                                </span>
                                <img
                                  src={RemoveIcon}
                                  onClick={() => {
                                    addOrRemoveCurrentProcess(index, item);
                                  }}
                                  id={`current_process_config_${idx}`}
                                  alt={"Remove " + item}
                                  className={"ms-2 align-top"}
                                />
                                <UncontrolledTooltip
                                  placement="bottom"
                                  target={`current_process_config_${idx}`}
                                >
                                  {"Remove " + item}
                                </UncontrolledTooltip>
                              </Button>
                            </React.Fragment>
                          );
                        })}
                      </div>
                    </div>
                  )}
              </div>
            </div>
          </div>
          <NextPrevBtn
            {...props}
            nextTab={props.tabDetails.nextTab.title}
            handlePrevBtnClick={handlePrevBtnClick}
            handleNextBtnClick={handleNextBtnClick}
            PrevTab={props.tabDetails.prevTab.title}
          />
        </> }
      </React.Fragment>
    </>
  );
}
export default withFormHoc(ProductCategory);