import DynamicForm, { withFormHoc } from '@medplus/react-common-components/DynamicForm'
import React, { useContext, useEffect, useState } from 'react'
import FormHelper from '../../helpers/FormHelper';
import Validate from '../../helpers/Validate';
import { AlertContext } from '../Contexts/UserContext';
import DataGridHelper from '../common/DataGridHelper';
import DynamicGridHeight from '../common/DynamicGridHeight';
import CommonDataGrid, { DeleteIcon, EditIcon } from '@medplus/react-common-components/DataGrid';
import { rangeTypeMap } from '../../constants/MarketingConstant';
import CombinationSelector from './CombinationSelector';

const ParametersForPathLabConfiguration = ({ helpers, ...props }) => {

  const validate = Validate();
  const [selectedParameter, setSelectedParamter] = useState(undefined)
  const parameters = props?.parameters;
  const [minParamValue, setMinParamValue] = useState(undefined)
  const [maxParamValue , setMaxParamValue] = useState(undefined)
  const [paramValue, setParamValue] = useState(undefined)
  const [parameterRange,setParameterRange] = useState();
  const parameterForm = FormHelper().getParametersFormForpathlabConf(parameters, helpers);
  const [selectedRow , setSelectedRow] = useState(undefined)
  const [timeInMonths, setTimeInMonths] = useState(undefined)
  const { setStackedToastContent } = useContext(AlertContext);
  const parameterResultMetaData = DataGridHelper().parameterResultMetaData();
  const [isParameterResultForEdit, setIsParameterResultForEdit] = useState(false)
  const maximumInclusiveParametersSize = props?.maximumInclusiveParametersSize
  const [selectedId, setSelectedId] = useState(selectedRow?.parameterName);

  useEffect(()=>{
    let parameterFormObject = helpers.validateAndCollectValuesForSubmit('getParametersFormForpathlabConf', true, true, true);
    if (!validateFormResult(parameterFormObject)) {
        helpers.disableElement("addParameter")
    }else{
      helpers.enableElement("addParameter")
    }
    if(validate.isEmpty(selectedParameter) && validate.isEmpty(parameterRange) && validate.isEmpty(paramValue) && validate.isEmpty(minParamValue) && validate.isEmpty(maxParamValue) && validate.isEmpty(timeInMonths)){
      helpers.disableElement("clear")
    }else{
      helpers.enableElement("clear")
    }
  },[selectedParameter, parameterRange, paramValue, minParamValue, maxParamValue, timeInMonths])


  useEffect(() => {
    if(isParameterResultForEdit){
      helpers.updateSingleKeyValueIntoField('label',"Update",'addParameter');
      helpers.updateSingleKeyValueIntoField('className',"btn btn-primary px-4 py-2",'addParameter');
      helpers.disableElement("parameterName");
    }else{
      helpers.updateSingleKeyValueIntoField('label',"Add",'addParameter');
      helpers.updateSingleKeyValueIntoField('className',"btn btn-success px-4 py-2",'addParameter');
      if(props.parameterResult.length < maximumInclusiveParametersSize){
        helpers.enableElement("parameterName");
      }else{
        helpers.disableElement("parameterName");
      }
    }
  }, [isParameterResultForEdit, props.parameterResult])

  useEffect(() => {
    let parametersOptions = [];
        Object.entries(parameters).map(([key, value]) => {
            parametersOptions.push(helpers.createOption(key, value, value));
        });
      helpers.updateSingleKeyValueIntoField("values", parametersOptions, "parameterName");
  },[parameters])

  const onParamterSelection = (payload) =>{
    const value = payload[0].target.value
    if(validate.isNotEmpty(value)){
      setSelectedParamter(value)
      helpers.enableElement("rangeType")
    }else{
      setSelectedParamter(undefined)
      helpers.updateValue(null, "rangeType")
      helpers.disableElement("rangeType")
    }
    helpers.disableElement("time")
    helpers.disableElement("absoluteValue")
    helpers.disableElement("minValue")
    helpers.disableElement("maxValue")
    helpers.updateValue(null, "absoluteValue")
    helpers.updateValue(null, "minValue")
    helpers.updateValue(null, "maxValue")
    helpers.updateValue(null, "time")
    setParamValue(undefined)
    setMaxParamValue(undefined)
    setMinParamValue(undefined)
    setTimeInMonths(undefined)
  }

  const onParameterRangeSelection = (payload) => {
    const value = payload[0]
    if (validate.isNotEmpty(value) && validate.isNotEmpty(selectedParameter)) {
      helpers.enableElement('time')
      setParameterRange(value);
      if ("1" == value || "2" == value) {
        helpers.enableElement('minValue');
        helpers.enableElement('maxValue');
        helpers.disableElement('absoluteValue');
        helpers.updateValue(null, "absoluteValue")
        setParamValue(undefined)
      } else {
        helpers.enableElement('absoluteValue');
        helpers.disableElement('minValue');
        helpers.disableElement('maxValue');
        helpers.updateValue(null, 'minValue')
        helpers.updateValue(null, 'maxValue')
        setMinParamValue(undefined)
        setMaxParamValue(undefined)
      }
    }
    else {
      setParameterRange(undefined);
      helpers.disableElement('absoluteValue');
      helpers.disableElement('minValue');
      helpers.disableElement('maxValue');
      helpers.disableElement('time');
      helpers.updateValue('', 'minValue');
      helpers.updateValue('', 'rangeType');
      helpers.updateValue('', 'maxValue');
      helpers.updateValue('', 'absoluteValue');
      helpers.updateValue("", 'time');
      setParamValue(undefined)
      setMaxParamValue(undefined)
      setMinParamValue(undefined)
      setTimeInMonths(undefined)
    }
  }

  const validateMinParamVal = (minParamVal) => {
    if (validate.isEmpty(minParamVal)) {
      helpers.updateValue(null,"minValue")
      setStackedToastContent({ toastMessage: 'Please enter minimum Parameter value' });
      return;
    }
    if(minParamVal == 0){
      return;
    }
    helpers.updateValue(minParamVal,"minValue")
    setMinParamValue(minParamVal)
  }

  const validateParamVal = (paramVal) => {
    if (validate.isEmpty(paramVal)) {
      helpers.updateValue(null,"absoluteValue")
      setStackedToastContent({ toastMessage: 'Please enter the absolute value' });
      return;
    }
    if(paramVal == null){
      return;
    }
    helpers.updateValue(paramVal,"absoluteValue")
    setParamValue(paramVal)
  }

  const validateMaxParamVal = (maxParamVal) => {
    if (validate.isEmpty(maxParamVal)) {
      helpers.updateValue(null,"maxValue")
      setStackedToastContent({ toastMessage: 'Please enter maximum parameter value' });
      return;
    }
    if(maxParamVal == 0){
      return;
    }
    helpers.updateValue(maxParamVal,"maxValue")
    setMaxParamValue(maxParamVal)
  }

  const validateParameterTimeDuartion = (time) => {
    if (validate.isEmpty(time)) {
      setTimeInMonths(undefined)
      setStackedToastContent({ toastMessage: 'Please enter time duration in months' })
    }
    else {
      setTimeInMonths(time)
    }
  }

  const validateFormResult = (parameterFormObject) => {
    if (validate.isEmpty(parameterFormObject)) {
      return false;
    }
    const {
      maxValue,
      minValue,
      rangeType,
      absoluteValue,
      parameterName,
      time
    } = parameterFormObject;

    if(validate.isEmpty(parameterName)){
      return false;
    }

    if(validate.isEmpty(rangeType)){
      return false;
    }

    if((validate.isEmpty(absoluteValue) && (validate.isEmpty(maxValue) || validate.isEmpty(minValue)))){
      return false;
    }

    if(validate.isEmpty(absoluteValue) && validate.isEmpty(maxValue)){
      return false;
    }

    if(validate.isEmpty(absoluteValue) && validate.isEmpty(minValue)){
      return false;
    }

    if (validate.isEmpty(time) || !validate.isNumeric(time[0])) {
      return false;
    }
    
    if (parseFloat(maxValue) < parseFloat(minValue)) {
      setStackedToastContent({ toastMessage: 'Maximum parameter value should never be less than minimum parameter value' });
      return false;
    }

    if (parseFloat(maxValue) == parseFloat(minValue)) {
      setStackedToastContent({ toastMessage: 'Maximum parameter value should never be equal to the minimum parameter value' });
      return false;
    }

    return true;
  };

   const resetParameterResultForm = () =>{
    setIsParameterResultForEdit(false);
    setSelectedRow({});
    helpers.updateValue(null,"parameterName")
    helpers.resetForm("getParametersFormForpathlabConf")
    helpers.disableElement("rangeType");
    helpers.disableElement("minValue");
    helpers.disableElement("maxValue");
    helpers.disableElement("absoluteValue");
    helpers.disableElement("time");
    helpers.disableElement("addParameter");
    setSelectedParamter(undefined)
    setParameterRange(undefined)
    setMinParamValue(undefined)
    setMaxParamValue(undefined)
    setParamValue(undefined)
    setTimeInMonths(undefined)
  } 
  

  const addParameterResult = () => {
    let eachParameterResult = {};
    let parameterFormObject = helpers.validateAndCollectValuesForSubmit('getParametersFormForpathlabConf', true, true, true);
    if (!validateFormResult(parameterFormObject)) {
        return;
    }
    let prameter = helpers.getHtmlElement("parameterName");
    let targetValue = prameter?.value?.[0];
    let selectedRow = prameter.values.find(item => item.value === targetValue);
    eachParameterResult.parameterName = parameterFormObject?.parameterName?.join(',')

    const editingParameterResult = props?.parameterResult?.find((eachParameter) => eachParameter?.parameterName === parameterFormObject?.parameterName?.[0]);
    eachParameterResult.parameterId = isParameterResultForEdit ? editingParameterResult?.parameterId : selectedRow?.id || '';
    eachParameterResult.absoluteValue = validate.isNotEmpty(parameterFormObject.absoluteValue) 
    ? parseFloat(parameterFormObject.absoluteValue) 
    : null;
    eachParameterResult.minValue = validate.isNotEmpty(parameterFormObject.minValue) 
        ? parseFloat(parameterFormObject.minValue) 
        : null;
        
    eachParameterResult.maxValue = validate.isNotEmpty(parameterFormObject.maxValue) 
        ? parseFloat(parameterFormObject.maxValue) 
        : null;

        eachParameterResult.rangeType = parameterFormObject?.rangeType?.join(',');

    eachParameterResult.parameterName = parameterFormObject?.parameterName?.join(',') 

    eachParameterResult.time = validate.isNumeric(parameterFormObject.time) 
        ? (parseInt(parameterFormObject.time?.[0]) *30)
        : null;

    handleAddAndRemoveParameterResult(isParameterResultForEdit?'edit':'add',eachParameterResult);
    resetParameterResultForm();

    const selectedParameter = parameterFormObject?.parameterName?.[0];
    if (selectedParameter) {
        const updatedParameters = { ...props.parameters };

        const keyToRemove = Object.keys(updatedParameters).find(key => updatedParameters[key] === selectedParameter);
        if (keyToRemove) {
            delete updatedParameters[keyToRemove];
            props.setParameters(updatedParameters);
        }
    }
};


  const validateTimeDurationInMonths = (time) => {
    const validDurations = [3, 6, 9, 12, 15, 18, 21, 24];

    if (validate.isEmpty(time) || !validDurations.includes(Number(time))) {
      setTimeInMonths(undefined);
      helpers.updateSingleKeyValueIntoField("message", "Please enter a valid time duration in months", "time");
    } else {
      setTimeInMonths(time);
      helpers.updateSingleKeyValueIntoField("message", "", "time");
    }
  };



  const observersMap = {
    "parameterName": [['select', (payload) => onParamterSelection(payload)]],
    "rangeType": [['select', (payload) => onParameterRangeSelection(payload[0].target.value)]],
    'minValue': [['change', (payload) => validateMinParamVal(payload[0].target.value)]],
    'maxValue': [['change', (payload) => validateMaxParamVal(payload[0].target.value)]],
    'absoluteValue': [['change', (payload) => validateParamVal(payload[0].target.value)]],
    'time': [['select', (payload) => validateParameterTimeDuartion(payload[0].target.value)] , ['blur', (payload) => validateTimeDurationInMonths(payload[0].target.value)], ['change', (payload) => validateTimeDurationInMonths(payload[0].target.value)]],
    "addParameter": [['click',  addParameterResult]],
    'clear':[['click',resetParameterResultForm]],
  };

  const handleAddAndRemoveParameterResult = (action, value) => {
    if (validate.isNotEmpty(value)) {
      let updatedParameterResult = [...props.parameterResult];

      switch (action) {
        case 'add':
          updatedParameterResult.push(value);
          break;

        case 'edit':
          updatedParameterResult = updatedParameterResult.map((eachParameterResult) => eachParameterResult.parameterId === value.parameterId ? value : eachParameterResult);
          break;

        case 'remove':
          updatedParameterResult = updatedParameterResult.filter((eachParameterResult) => eachParameterResult.parameterId !== value.parameterId);
          if (updatedParameterResult.length <= 1) {
            props.setParameterResultCombinationType(undefined);
          }
          const { parameterId, parameterName } = value;
          const updatedParameters = { ...props.parameters };
          updatedParameters[parameterId] = parameterName;

          props.setParameters(updatedParameters);
          break;

        default:
          break;
      }

      if(updatedParameterResult?.length >= maximumInclusiveParametersSize){
        resetParameterResultForm();
        helpers.disableElement("parameterName");
        helpers.disableElement("clear")
      }else{
        helpers.enableElement("getParametersFormForpathlabConf")
        helpers.enableElement("parameterName")
        helpers.enableElement("clear")
      }

      props.setPatameterResult(updatedParameterResult);
    }
  };

  const handleRemoveParameterResult=(payload)=>{
    handleAddAndRemoveParameterResult('remove',payload);
  } 

  const renderActionColumn = (obj) => {
    const presentParameterResult = obj.row;
    const onRowChange = obj.onRowChange;

    const deletePresentParameterResult = (presentParameterResult) => {
      presentParameterResult.forDelete = true;
        handleRemoveParameterResult(presentParameterResult);
      onRowChange({ ...presentParameterResult });
    }  
    
    const handleOnEdit = (payload)=>{
      helpers.enableElement("getParametersFormForpathlabConf")
      helpers.enableElement("parameterName")
      helpers.enableElement("clear")
      setIsParameterResultForEdit(true)
      setSelectedRow(payload);
      setSelectedId(payload?.parameterName);
      prepareParameterResultToPopulate(payload);
    }
    
    const prepareParameterResultToPopulate = (parameterResult) => {
      if (validate.isNotEmpty(parameterResult)) {
        helpers.updateValue([parameterResult.parameterName], 'parameterName');
        setSelectedParamter(parameterResult.parameterName)
        if(validate.isNotEmpty(parameterResult.absoluteValue) && !validate.isNotEmpty(parameterResult.minValue) && !validate.isNotEmpty(parameterResult.maxValue)){
          helpers.updateValue(parameterResult.absoluteValue, 'absoluteValue');
          setParamValue(parameterResult.absoluteValue)
          helpers.enableElement("absoluteValue");
        }else{
          helpers.updateValue(null, 'absoluteValue');
          setParamValue(undefined)
          helpers.disableElement("absoluteValue");
        }
        if(validate.isNotEmpty(parameterResult.minValue) && validate.isNotEmpty(parameterResult.maxValue)){
          helpers.updateValue(parameterResult.minValue, 'minValue');
          helpers.updateValue(parameterResult.maxValue, 'maxValue');
          setMinParamValue(parameterResult.minValue)
          setMaxParamValue(parameterResult.maxValue)
          helpers.enableElement("minValue");
          helpers.enableElement("maxValue");
        }else{
          helpers.updateValue(null, 'minValue');
          helpers.updateValue(null, 'maxValue');
          setMinParamValue(undefined)
          setMaxParamValue(undefined)
          helpers.disableElement("minValue");
          helpers.disableElement("maxValue");
        }

        if (validate.isNotEmpty(parameterResult.parameterName)) {
          helpers.disableElement("parameterName");
        }
    
        if (validate.isNotEmpty(parameterResult.rangeType)) {
          helpers.updateValue([parameterResult.rangeType], 'rangeType');
          setParameterRange(rangeTypeMap[parameterResult.rangeType])
          helpers.enableElement("rangeType");
        } else {
          setParameterRange(undefined)
          helpers.updateValue(null,"rangeType");
          helpers.disableElement("rangeType");
        }
        if (validate.isNotEmpty(parameterResult.time)) {
          setTimeInMonths(parameterResult.time)
          helpers.enableElement("time");
          helpers.updateValue([String(parameterResult?.time)], 'time');
        } else {
          helpers.updateValue(null, 'time');
          setTimeInMonths(undefined)
          helpers.disableElement("time");
        }
      }
  }
  
    
    

    return (<React.Fragment>
      <div className='d-flex'>
      <EditIcon  isDisabled={isParameterResultForEdit && presentParameterResult?.parameterName == selectedRow?.parameterName} tooltip={"Edit"}  id={props?.row?.parameterName} handleOnClick={(e)=>handleOnEdit(presentParameterResult)}/>
      <DeleteIcon isDisabled={isParameterResultForEdit && presentParameterResult?.parameterName == selectedRow?.parameterName} tooltip={"Remove"} id={props?.row?.parameterName} handleOnClick={(e) => { deletePresentParameterResult(presentParameterResult) }} />
      </div>
        
    </React.Fragment>);
}

  const callBackMapping = {
    renderActionColumn,
    "rowClass": (row) => {
      let rowClassName = '';
      if (validate.isNotEmpty(row?.parameterName) && (row?.parameterName == selectedId)) {
        rowClassName = "row-popup-opened";
        setTimeout(() => {
          setSelectedId(undefined);
      }, 2000);
      }

      return rowClassName;
    }
}

const getParameterResultWithTimeInMonth = () => {
  const updatedResults = props.parameterResult.map(result => ({
    ...result, 
    time: result.time / 30 ,
    parameterName: parameters[result.parameterId] || result.parameterName,
    rangeType: getParamRangeById(result?.rangeType) 
  }));
  return updatedResults;
};


function getParamRangeById(rangeId) {
  const rangeValue = Object.keys(rangeTypeMap).find(key => rangeTypeMap[key] == rangeId);
  return rangeValue;
}

const handleCombinationChange = (event) => {
  props.setParameterResultCombinationType(event.target.value);
};

const combinationForParameterResult = () => {
  return (
    <>
      <CombinationSelector
        selectedValue={props.parameterResultCombinationType}
        onChange={handleCombinationChange}
        parameterResult={props.parameterResult}
        orCombinationId = "parameterResultOrCombinationId" 
        andCombinationId = "parameterResultAndCombinationId"
        orCombinationValue = "parameterResultOrCombinationValue"
        andCombinationValue = "parameterResultAndCombinationValue"
      />
    </>
  );
};

  return (
    <>
    <label className='custom-fieldset p-12 pb-0'>Parameter</label>
    <div className='p-12'>
    <DynamicForm formJson={parameterForm} helpers={helpers} observers={observersMap}/>
      {validate.isNotEmpty(parameterResultMetaData)  && validate.isNotEmpty(getParameterResultWithTimeInMonth()) &&
        <React.Fragment>
          <label class="d-block mb-2 font-weight-bold custom-fieldset">Result</label>
          <div className=''>
            <div className={`card mb-3 me-0 `}>
              <DynamicGridHeight id="parameterResult" metaData={parameterResultMetaData} dataSet={[...getParameterResultWithTimeInMonth()]}>
                <CommonDataGrid {...({ ...parameterResultMetaData })} dataSet={[...getParameterResultWithTimeInMonth()]} callBackMap={callBackMapping}/>
              </DynamicGridHeight>
              {props.parameterResult.length>1 &&<div className='card-footer border-top-0 bg-white'>
                {combinationForParameterResult()}
              </div>}
            </div>
          </div>
        </React.Fragment>}

    </div>
    </>
  )
}

export default withFormHoc(ParametersForPathLabConfiguration);