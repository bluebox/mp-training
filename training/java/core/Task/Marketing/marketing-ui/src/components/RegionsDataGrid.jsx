import CommonDataGrid, { CellTextEditor, ChangeType, SelectFilter } from "@medplus/react-common-components/DataGrid";
import { ALERT_TYPE } from "@medplus/react-common-components/DynamicForm";
import React, { useContext, useEffect, useState } from "react";
import { UncontrolledTooltip } from "reactstrap";
import { PromotionStatus } from "../constants/PromotionConstants";
import Validate from "../helpers/Validate";
import RemoveIconRed from '../images/delete-icn-16.svg';
import MarketingService from "../services/MarketingService";
import { AlertContext } from "./Contexts/UserContext";
import { downloadUrl, getRegionsName, regionFilterMapWithOutStores, regionFilterMapWithStores } from "../services/ServiceConstants";
const marketingService = MarketingService();

const validate = Validate();
const RegionsDataGrid = (props) => {
    const [regionsMap, setRegionsMap] = useState()
    const [selectedRegionFilter, setSelectedRegionFilter] = useState("")
    const filterRegionsMap = props.showStoreLevel ? regionFilterMapWithStores : regionFilterMapWithOutStores;
  const [dataGridStructure, setDataGridStructure] = useState(undefined);
  const [dataGridData, setDataGridData] = useState([]);
    const {setAlertContent} = useContext(AlertContext)
  const setDataGrid = async () => {
    const editableFeild = !(props?.globalPromotionStatus === PromotionStatus.active || props?.actionPermissions?.isApprover)
    const data = await marketingService
      .getRegionsDataGrid({ 'editableFeild': !props.disableFields, 'isStoreLevel' : false})
      .catch((error) => {
        setAlertContent({ alertType: ALERT_TYPE.ERROR, alertMessage: error });
      });

    if (props.isNotEligibleRegionsRequired) {
      setDataGridStructure(data.responseData);
    } else {
      const updatedData = data.responseData;
      const columnIndexToRemove = updatedData.columns.findIndex(
        (column) => column.columnName === "Not Eligible Regions"
      );
      if (columnIndexToRemove !== -1) {
        updatedData.columns.splice(columnIndexToRemove, 1);
      }
      setDataGridStructure(updatedData);
    }
  };

  useEffect(() => {
    
    if (Validate().isEmpty(dataGridStructure)) {
      setDataGrid();
    }
    updateValues();
  }, []);

  useEffect(() => {
    let gridData = [];
    setRegionsMap({...props.regions})
    const regionsMap = props.regions;
    if (regionsMap && Object.keys(regionsMap).length > 0) {
      Object.keys(regionsMap).forEach((key) => {
        let row = {};
        row["region"] = key;
        row["notEligibleRegions"] = regionsMap[key]
          ? regionsMap[key]["ner"]
          : "";
        gridData.push(row);
      });
    }
    setDataGridData(gridData);
  }, [props.regions]);

  const updateValues = () => {
    let regions = props.regions;
    if (validate.isNotEmpty(regions)) {
      let sRegions = [];
      Object.keys(regions).map((key) => {
        sRegions.push(key);
      });
    }
  };

  const getRegionsCount = (regionLength) => {
    return dataGridData.map(detail => detail.region).filter(region => region.length == regionLength).length;
  };

  const customText=()=>{
    return(
      <React.Fragment>
        {selectedRegionFilter!="" &&
              <p className="mt-2 mb-0 d-flex align-items-center ">{getRegionsName(selectedRegionFilter)} - {getRegionsCount(selectedRegionFilter)}</p>
         }
      </React.Fragment>
    )
  }

  const handleNEREdit = (obj) => {
    let row = obj.updatedRows[obj.updatedRowIndex];
    obj.updatedRows[obj.updatedRowIndex].notEligibleRegions = obj.updatedRows[obj.updatedRowIndex]?.notEligibleRegions?.toUpperCase();
    if(handleNERBlur(row.region, row.notEligibleRegions)) {
        handleNERChange(row.region, row.notEligibleRegions);
    } 
    return {}
}

const handleNERBlur = (region,value) => {
    return props?.validateNER(region, value);
}
  const handleNERChange = (region,value) => {
    regionsMap[region]['ner']= value;
    setRegionsMap({...regionsMap});
}
  const deleteRegion = (row) => {
    props.handleRegionRemove(row.region);
}

  const callBackMapping = {
    "selectFilter" : (props) => {
      const {handleFilterChange , ...rest} = props;
      const handleFilerChaner = (filters) => {
         setSelectedRegionFilter(filters.region.value)
         handleFilterChange(filters)
      }
      return <SelectFilter handleFilterChange={handleFilerChaner} {...rest}  tooltip={true} isClearable itemsMap={filterRegionsMap}/>
   },
    "renderActionColumn" : (rowObject) => {
        return <React.Fragment>
            {props?.disableFields ?
                  <p>-</p> : <button className="btn btn-light p-0" onClick={()=>deleteRegion(rowObject.row)}><img  src={RemoveIconRed}  alt={"Remove"} className={" "} /></button>
            }
        </React.Fragment>
    },
    "notEligibleCellEditor" : ({row , column , onRowChange , onClose}) => {
        if(row['region'] && row['region'].length == 12) {
            return <React.Fragment></React.Fragment>
        }
        return <CellTextEditor selectedValue = {row[column.key]} onChange = {(e) => onRowChange({...row,[column.key] : e.target.value})} />
    },
    "regionColumn" : ({row , column , onRowChange , onClose}) => {
        return  <>
        <span  id={`tooltip_${row[column.key]}_${column.idx}`}>
            {row['region']}
            {   validate.isNotEmpty(regionsMap) &&
                validate.isNotEmpty(regionsMap[row['region']]) &&
                validate.isNotEmpty(regionsMap[row['region']]['name']) &&
                row['region'].length === 12 &&
            <UncontrolledTooltip placement="bottom" target={`tooltip_${row[column.key]}_${column.idx}`}>
                {regionsMap[row['region']]['name']}
            </UncontrolledTooltip>
            }
        </span>
        </>
    }
    
}
  return (
    <React.Fragment>
      <div className="h-50">
        <label className="custom-fieldset mb-2 col-3">Selected Regions - {dataGridData.length}</label> 
        <div className="card w-100 h-100">
          {Validate().isNotEmpty(dataGridStructure) && (
            <CommonDataGrid
              {...dataGridStructure}
              dataSet={dataGridData}
              // remoteDataFunction = {remoteDataFunction}
              callBackMap={callBackMapping}
              onEdit={handleNEREdit}
              customGridToolbar={{component:customText}}
            />
          )}
        </div>
      </div>
    </React.Fragment>
  );
};

export default RegionsDataGrid;
