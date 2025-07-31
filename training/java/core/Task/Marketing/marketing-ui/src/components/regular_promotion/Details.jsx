import CommonDataGrid from "@medplus/react-common-components/DataGrid";
import { ALERT_TYPE, withFormHoc } from '@medplus/react-common-components/DynamicForm';
import React, { useContext, useEffect, useRef, useState } from 'react';
import { useSelector } from 'react-redux';
import Validate from '../../helpers/Validate';
import { AlertContext } from '../Contexts/UserContext';
import { BodyComponent, HeaderComponent } from '../common/CommonStructure';
import DataGridHelper from '../common/DataGridHelper';
import NextPrevBtn from '../common/NextPrevButtons';
import Uploads from "./Uploads";
import DynamicGridHeight from "../common/DynamicGridHeight";
import { PromotionStatus } from "../../constants/PromotionConstants";

let categoryDetail = []

const Details = (props) => {
    let fileObj = {}
    const headerRef=useRef();
    
    let prevCategoryDetails = new Map();
    if (Validate().isNotEmpty(props.details.categoryDetail)) {
        props.details.categoryDetail.forEach(row => {
            const key = JSON.stringify({
                discCategoryID : null,
                productDiscountCategoryId: String(row.productDiscountCategoryId),
                loyaltyId: String(row.loyaltyId),
                discountTypeId: 1,
                region: row.region,
                slabId: row.slabId
            });
            const value = {
                discountPercentage: row.discountPercentage,
                paybackPercentage: row.paybackPercentage
            };
            prevCategoryDetails.set(key, value);
        });
    }

    const customerFlag = props.customerFlag
    const validate = Validate();
    const [documentTrigger, setDocumentTrigger] = useState(false);

    const allRegionsRef = useRef(null);
    const {setAlertContent} = useContext(AlertContext)
    const [disabled, setDisabled] =  useState(false);
    const [allRegions, setAllRegions] = useState(false);
    const gridRef = useRef(null);
    const  loyaltyTypesRedux = useSelector((state) =>  validate.isNotEmpty(state.loyaltyReducer)? state.loyaltyReducer : undefined);
    const  categoryTypesRedux = useSelector((state) =>  validate.isNotEmpty(state.categoryReducer)? state.categoryReducer : undefined);
    const [dataSet, setDataSet] = useState([]);
    const [gridData,setGridData] = useState(DataGridHelper().regularPromotionDetails(true));

    useEffect (()=>{
        if (props.actionPermissions.isApprover || props.globalPromotionStatus === PromotionStatus.active) {
            setDisabled(true);
            setGridData(DataGridHelper().regularPromotionDetails(false));
        } else {
            setGridData(DataGridHelper().regularPromotionDetails(true));
        }
        updateDataSet(false);
    },[])

    // useEffect(()=>{
    //     if (props.actionPermissions.isApprover || props.globalPromotionStatus === PromotionStatus.active) {
    //         setGridData(DataGridHelper().regularPromotionDetails(false));
    //         updateDataSet(false);
    //     }
    // },[props.actionPermissions.isApprover])

    const handleAllRegionsChange = () => {
        updateDataSet(allRegionsRef.current.checked);
    };

    const updateDataSet = (allRegions) => {
        getDetails(allRegions);
        setAllRegions(allRegions);
    }

    const getDetails  =  (value) => {
        const regObj = props.regObj;
        const categoryLen =regObj.category.length;
        const loyaltyLen =regObj.loyalty.length;
        const regionsLen =(!value ? Object.keys(regObj.regions).length : 1);
        const slabsLen =regObj.slabInfo.slabs.length;

        const len = categoryLen * loyaltyLen * regionsLen * slabsLen;
        let rows=[]
        categoryDetail = []
        for (let i = 0; i < len; i++) {
            let row ={};
            let detail = {};
            const categoryIndex = Math.floor(i / (loyaltyLen * regionsLen * slabsLen)) % categoryLen;
            const loyaltyIndex = Math.floor(i / (regionsLen * slabsLen)) % loyaltyLen;
            const regionIndex = Math.floor(i / slabsLen) % regionsLen;
            const slabIndex = i % slabsLen;
            detail['discCategoryID'] = null

            row['productCategory'] = categoryTypesRedux[regObj.category[categoryIndex]]
            detail['productDiscountCategoryId'] = regObj.category[categoryIndex]

            row['loyaltyType'] = loyaltyTypesRedux[regObj.loyalty[loyaltyIndex]]
            detail['loyaltyId'] = regObj.loyalty[loyaltyIndex]

            row['discountType'] = "SlabBasedDiscount"
            //static get from constants
            detail['discountTypeId'] = 1
            if(!value) {
                row['region'] = Object.keys(regObj.regions)[regionIndex];
                detail['region'] = row['region']
            } 
            else {
                row['region'] = '-';
            }
            row['fromValue'] = regObj.slabInfo.slabs[slabIndex].fromValue
            row['toValue'] = regObj.slabInfo.slabs[slabIndex].toValue
            detail['slabId'] = regObj.slabInfo.slabs[slabIndex].slabId;

            const categoryDetailKey = JSON.stringify(detail)
            if (prevCategoryDetails.has(categoryDetailKey)) {
                row['discountPercentage'] = Validate().isNotEmpty(prevCategoryDetails.get(categoryDetailKey).discountPercentage) ? parseFloat((prevCategoryDetails.get(categoryDetailKey)).discountPercentage).toFixed(2) : "";
                row['paybackPercentage'] = Validate().isNotEmpty(prevCategoryDetails.get(categoryDetailKey).paybackPercentage) ? parseFloat((prevCategoryDetails.get(categoryDetailKey)).paybackPercentage).toFixed(2) : "";
            } else {
                row['discountPercentage'] = ""
                row['paybackPercentage'] = ""
            }
            row['rowIndex'] = i;
            detail['discountPercentage'] = row['discountPercentage']
            detail['paybackPercentage'] = row['paybackPercentage']

            categoryDetail.push(detail)
            rows.push(row);
        }
        setDataSet([...rows]);
    }

    const callBackMapping = () => {
    }   

    const handlePrevBtnClick = () => {
        let dataSetTemp=processDataSet(allRegions);                    
        props.handleCampaignInfoChange({dataSet : allRegions ? dataSetTemp : dataSet , categoryDetail : categoryDetail, allRegions : allRegions },props.tabDetails.currentTab.tabId, props.tabDetails.prevTab.tabId);
    } 

    const handleNextBtnClick = () => {
        if(validateDataSet(dataSet)) {
            if(props.actionPermissions?.isApprover || props.actionPermissions?.isCloser) {
                props.handleCampaignInfoChange({},props.tabDetails?.currentTab?.tabId, props.tabDetails?.currentTab?.tabId+1)
            } else {
                if(customerFlag) {
                    setDocumentTrigger(true);
                } else {
                    let dataSetTemp=processDataSet(allRegions);                    
                    props.handleCampaignInfoChange({dataSet : allRegions ? dataSetTemp : dataSet , categoryDetail : categoryDetail, allRegions : allRegions },props.tabDetails.currentTab.tabId, props.tabDetails.currentTab.tabId+1);
                }
            }
            return true;
        }
        else {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Enter valid decimal value for Discount/Payback percentage'})
            return false;
        }
    }
    const processDataSet = (value) => {
        let dataSetTemp = []
        let i=0
        if(value) {
        dataSet.forEach(row => {
            Object.keys(props.regObj.regions).forEach(region => {
                let newRow = {
                    ...row,
                    rowIndex: i++,
                    region: region
                };
                dataSetTemp.push(newRow);
            });
        });
        let categoryDetailTemp = []
        categoryDetail.forEach(row => {
            Object.keys(props.regObj.regions).forEach(region => {
                let newRow = {
                    ...row,
                    region: region
                };
                categoryDetailTemp.push(newRow);
            });
        });
        categoryDetail = categoryDetailTemp;
    }

        return dataSetTemp;
    }

    const validateDataSet = (value) => {
        return value.every(row => !validate.isEmpty(row.discountPercentage) && !validate.isEmpty(row.paybackPercentage));
    }

    const handleDetailsEdit = (obj) => {
        const currentColIndex = obj.column.idx;
        const cuurentRowIndex = obj.updatedRowIndex;
        const currentRow = obj.updatedRows[cuurentRowIndex];
        const index = 6;
        const colName = (currentColIndex === index) ? 'discountPercentage' : 'paybackPercentage';

        if(currentColIndex === index && !validateDiscountPercentage(currentRow[colName])) {
            currentRow[colName] = "";
            categoryDetail[cuurentRowIndex][colName] = "";
            selectCell(currentColIndex, cuurentRowIndex);
        } else if(currentColIndex === index+1 && !validatePaybackPercentage(currentRow[colName])) {
            currentRow[colName] = "";
            categoryDetail[cuurentRowIndex][colName] = "";
            selectCell(currentColIndex, cuurentRowIndex);
        } else {
            if(Validate().isNotEmpty(currentRow[colName])) {
                const value = parseFloat(currentRow[colName]).toFixed(2);
                obj.updatedRows[cuurentRowIndex][colName] = value;
                categoryDetail[cuurentRowIndex][colName] = value;
            } else {
                categoryDetail[cuurentRowIndex][colName] = "";
            }
            (currentColIndex === index+1) && selectCell(currentColIndex-1,cuurentRowIndex+1);
        }    
        setDataSet(obj.updatedRows)
        return {}
    }

    const selectCell = (currentColIndex , cuurentRowIndex) => {
        setTimeout(() => {
            gridRef.current.selectCell({idx: currentColIndex ,rowIdx: cuurentRowIndex},false);
        },1)
    }
    
    const validateDiscountPercentage = (discountPercentage) => {
        if(parseFloat(discountPercentage) >= 100) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Please enter a valid Discount percentage range in between 0.1 to 99.99'});
            return false;
        }
        return true;
    }

    const validatePaybackPercentage = (paybackPercentage) => {
        if(parseFloat(paybackPercentage) > 200) {
            setAlertContent({alertType:ALERT_TYPE.ERROR, alertMessage:'Payback percentage should be less than or equal to maximum allowed: 200'});
            return false;
        }
        return true;
    }
  
    const noteItems = [
        "Pay back points percentage should not be greater than 200"
    ]

//    useEffect(() => {
//         if(!documentTrigger && Validate().isNotEmpty(fileObj))
//             props.handleCampaignInfoChange(allRegions ? {dataSet :dataSetTemp , categoryDetail : categoryDetail, allRegions : allRegions, fileObj: fileObj} : {dataSet : dataSet , categoryDetail : categoryDetail , allRegions : allRegions, fileObj : fileObj}, props.tabDetails.currentTab.tabId, props.tabDetails.nextTab.tabId);
//     }, [documentTrigger])
    
    const setDocumentTriggerFalse = (flag) => {
        setDocumentTrigger(flag);
    }

    const setExcelData = (excelData) => {
        fileObj = excelData;
        let dataSetTemp=processDataSet(allRegions);   
        props.handleCampaignInfoChange({dataSet : allRegions ? dataSetTemp : dataSet , categoryDetail : categoryDetail, allRegions : allRegions,fileObj: fileObj },props.tabDetails.currentTab.tabId, props.tabDetails.currentTab.tabId+1);                 
    }

    return(
        <>
           <React.Fragment>
           
                {customerFlag &&
                    <Uploads {...props} setDocumentTriggerFalse={setDocumentTriggerFalse} setExcelData={setExcelData} documentTrigger={documentTrigger} />
                }
                <div className="p-12 mb-0 border-bottom">
                <input htmlelementtype="CHECKBOX" index="0" type="checkbox" id="allRegions"  
                    value={allRegions} ref={allRegionsRef} onChange={handleAllRegionsChange} disabled={disabled} className="form-check-input"/>
                <label title="" htmlFor="allRegions" className="form-check-label ms-2">Apply to all regions</label>
                </div>
                
                {validate.isNotEmpty(dataSet) &&
                            <div className="card mb-3">
                                <DynamicGridHeight id="detail" metaData={gridData} dataSet={dataSet} gridMaxRows={15}>
                                <CommonDataGrid
                                ref = {gridRef}
                                {...gridData}
                                dataSet={dataSet}
                                callBackMap={callBackMapping} 
                                onEdit = {handleDetailsEdit}
                                />
                                </DynamicGridHeight>
                            </div> 
                }
            <NextPrevBtn {...props} isNoteRequired={true} noteItems= {noteItems} showNote={!(props?.actionPermissions?.isApprover || props?.actionPermissions?.isCloser)} PrevTab={props.tabDetails.prevTab.title}  nextTab={props.tabDetails.nextTab.title}
            changeNxtBtn={true}  handlePrevBtnClick={handlePrevBtnClick} handleNextBtnClick={handleNextBtnClick} 
            />

                
            </React.Fragment>
        </>
    )
};

export default withFormHoc(Details);