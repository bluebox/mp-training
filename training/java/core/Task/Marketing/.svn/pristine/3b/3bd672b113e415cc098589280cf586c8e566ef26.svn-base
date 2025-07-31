import DynamicForm, { withFormHoc } from '@medplus/react-common-components/DynamicForm';
import React, {useContext, useEffect, useState } from 'react'
import DynamicGridHeight from '../common/DynamicGridHeight';
import CommonDataGrid, { DeleteIcon, EditIcon } from '@medplus/react-common-components/DataGrid';
import Validate from '../../helpers/Validate';
import DataGridHelper from '../common/DataGridHelper';
import FormHelper from '../../helpers/FormHelper';
import CombinationSelector from './CombinationSelector';
import { maxDaysAllowedForExclusionParameterResult } from '../../constants/MarketingConstant';
import { AlertContext } from '../Contexts/UserContext';

const ExcludeParameterForPathLabConfForm = ({ helpers, ...props }) => {
    const validate = Validate();
    const [selectedParameter, setSelectedParamter] = useState(undefined)
    const parameters = props?.parameters;
    const parameterForm = FormHelper().geExcludetParametersFormForpathlabConf(parameters, helpers);
    const [noOfDays, setNumberOfDays] = useState(undefined)
    const parameterResultMetaData = DataGridHelper().exclusionParameterResultMetaData();
    const [selectedRow , setSelectedRow] = useState(undefined)
    const [isParameterResultForEdit, setIsParameterResultForEdit] = useState(false)
    const maximumExclusiveParametersSize = props?.maximumExclusiveParametersSize
    const [selectedId, setSelectedId] = useState(selectedRow?.parameterName);
    const { setStackedToastContent } = useContext(AlertContext);
    const defaultPreviousChoice = {excludeParameterResult:[],hasSelectedRadiologyTests:null};
    let previousChoiceExcludeParameterResult = defaultPreviousChoice;

    useEffect(()=>{
      let parameterFormObject = helpers.validateAndCollectValuesForSubmit('geExcludetParametersFormForpathlabConf', true, true, true);
      if (!validateFormResult(parameterFormObject)) {
          helpers.disableElement("exclusionAddParameter")
      }else{
        helpers.enableElement("exclusionAddParameter")
      }
      if(validate.isEmpty(selectedParameter) && validate.isEmpty(noOfDays)){
        helpers.disableElement("exclusionClear")
      }else{
        helpers.enableElement("exclusionClear")
      }
    },[selectedParameter, noOfDays ])

    useEffect(() => {
      let parametersOptions = [];
          Object.entries(parameters).map(([key, value]) => {
              parametersOptions.push(helpers.createOption(key, value, value));
          });
        helpers.updateSingleKeyValueIntoField("values", parametersOptions,  "parameterName");
        if(props.hasSelectedRadiology!=null){
          helpers.updateValue(props.hasSelectedRadiology ? 'T' : 'P','triggerParameterTestType');
          showOrHideExclusionParametersFields(true);
        }
        else{
          helpers.updateValue(null,'triggerParameterTestType');
          showOrHideExclusionParametersFields(false);
        }
    },[parameters,props.hasSelectedRadiology])

    useEffect(() => {
      if(isParameterResultForEdit){
        helpers.updateSingleKeyValueIntoField('label',"Update",'exclusionAddParameter');
        helpers.updateSingleKeyValueIntoField('className',"btn btn-primary  px-4 py-2",'exclusionAddParameter');
        helpers.disableElement("parameterName");
      }else{
        helpers.updateSingleKeyValueIntoField('label',"Add",'exclusionAddParameter');
        helpers.updateSingleKeyValueIntoField('className',"btn btn-success  px-4 py-2",'exclusionAddParameter');
        if(props.excludeParameterResult.length< maximumExclusiveParametersSize){
          helpers.enableElement("parameterName");
        }else{
          helpers.disableElement("parameterName");
        }
      }
    }, [isParameterResultForEdit, props.excludeParameterResult])
  
    const onExcludeParamterSelection = (payload) =>{
      const value = payload[0].target.value
      if(validate.isNotEmpty(value)){
        setSelectedParamter(value)
        helpers.enableElement("time")
      }else{
        setSelectedParamter(undefined) 
        helpers.disableElement("time")
      }
      helpers.updateValue(null, "time")
      setNumberOfDays(undefined)
    }
    
    const validateFormResult = (parameterFormObject) => {
      if (validate.isEmpty(parameterFormObject)) {
        return false;
      }
      const timeInDays = parameterFormObject?.time
      if (validate.isEmpty(timeInDays)) {
        return false;
      }
      return true;
    };

  const handleAddAndRemoveParameterResult = (action, value) => {
    if (validate.isNotEmpty(value)) {
      let updatedParameterResult = [...props.excludeParameterResult];

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
            props.setExcludeParameterResultCombinationType("");
          }
          const { parameterId, parameterName } = value;
          const updatedParameters = { ...props.parameters };
          updatedParameters[parameterId] = parameterName;

          props.setParameters(updatedParameters);
          break;

        default:
          break;
      }

      if(updatedParameterResult?.length >= maximumExclusiveParametersSize){
        resetParameterResultForm();
        helpers.disableElement("parameterName");
        helpers.disableElement("exclusionClear")
      }else{
        helpers.enableElement("geExcludetParametersFormForpathlabConf")
        helpers.enableElement("parameterName")
        helpers.enableElement("exclusionClear")
      }
      previousChoiceExcludeParameterResult = defaultPreviousChoice;
      props.setExcludeParameterResult(updatedParameterResult);
    }
  };

    const handleRemoveParameterResult=(payload)=>{
      handleAddAndRemoveParameterResult('remove',payload);
    } 

    const resetParameterResultForm = () =>{
      setIsParameterResultForEdit(false);
      setSelectedRow({});
      ['time','parameterName',''].map(id=>helpers.updateValue(null,id,false));
      helpers.disableElement("time");
      helpers.disableElement("exclusionAddParameter");
      setSelectedParamter(undefined)
      setNumberOfDays(undefined)
    } 

    const addParameterResult = () => {
      let eachParameterResult = {};
      let parameterFormObject = helpers.validateAndCollectValuesForSubmit('geExcludetParametersFormForpathlabConf', true, true, true);
      if (!validateFormResult(parameterFormObject)) {
          return;
      }
      let prameter = helpers.getHtmlElement("parameterName");
      let targetValue = prameter?.value?.[0];
      let selectedRow = prameter.values.find(item => item.value === targetValue);
      eachParameterResult.parameterName = parameterFormObject?.parameterName?.join(',')
      const editingParameterResult = props?.excludeParameterResult?.find((eachParameter) => eachParameter?.parameterName === parameterFormObject?.parameterName?.[0]);
      eachParameterResult.parameterId = isParameterResultForEdit ? editingParameterResult?.parameterId : selectedRow?.id || '';
      eachParameterResult.parameterType = parameterFormObject?.triggerParameterTestType;
      eachParameterResult.time = validate.isNumeric(parameterFormObject.time) 
        ? (parseInt(parameterFormObject.time, 10))
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

    const validateParameterTimeDuartion = (payload) =>{
      const value = payload[0].target.value;
      if(validate.isNumeric(value) &&  value < maxDaysAllowedForExclusionParameterResult){
        helpers.updateValue(value,"time")
        setNumberOfDays(value)
      }else{
        setNumberOfDays(undefined)
      }
    }

    const renderExclusionActionColumn = (obj) => {
      const presentParameterResult = obj.row;
      const onRowChange = obj.onRowChange;
      const deletePresentParameterResult = (presentParameterResult) => {
        presentParameterResult.forDelete = true;
        handleRemoveParameterResult(presentParameterResult);
        onRowChange({ ...presentParameterResult });
      }  
      
      const handleOnEdit = (payload)=>{
        helpers.enableElement("geExcludetParametersFormForpathlabConf")
        helpers.enableElement("parameterName")
        helpers.enableElement("exclusionClear")
        setIsParameterResultForEdit(true)
        setSelectedRow(payload);
        setSelectedId(payload?.parameterName);
        prepareExcludeParameterResultToPopulate(payload);
      }
      
      const prepareExcludeParameterResultToPopulate = (parameterResult) => {
        if (validate.isNotEmpty(parameterResult)) {
          setSelectedParamter(validate.isNotEmpty(parameterResult.parameterName) ? parameterResult.parameterName : undefined)
          setNumberOfDays(parameterResult.time)
          helpers.updateValue([parameterResult.parameterName], 'parameterName');
          if (validate.isNotEmpty(parameterResult.parameterName)) {
            helpers.disableElement("parameterName");
          }
          if (validate.isNotEmpty(parameterResult.time)) {
            helpers.updateValue([parameterResult.time], 'time');
            helpers.enableElement("time");
          } else {
            helpers.updateValue(null, 'time');
            helpers.disableElement("time");
          }
        }
      }
      
      
  
      return (<React.Fragment>
        <div className='d-flex'>
        <EditIcon isDisabled={isParameterResultForEdit && presentParameterResult?.parameterName == selectedRow?.parameterName} tooltip={"Edit"}  id={props?.row?.paramterValue} handleOnClick={(e)=>handleOnEdit(presentParameterResult)}/>
        <DeleteIcon isDisabled={isParameterResultForEdit && presentParameterResult?.parameterName == selectedRow?.parameterName} tooltip={"Remove"} id={props?.row?.parameterName} handleOnClick={(e) => { deletePresentParameterResult(presentParameterResult) }} />
        </div>
          
      </React.Fragment>);
  }

    const callBackMapping = {
      renderExclusionActionColumn,
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

  const showOrHideExclusionParametersFields = (show)=> {
    ['parameterName','time','exclusionClear','exclusionAddParameter'].map(id=>show ?  helpers.showElement(id) : helpers.hideElement(id));
  } 

  const prepareHasSelectedRadiologyTests = (value) => {
    let hasSelectedRadiologyTests = null;
    if(value=='T'){
      hasSelectedRadiologyTests =  true;
      }
      else if (value =='P'){
        hasSelectedRadiologyTests =  false;
      }
      else{
        hasSelectedRadiologyTests =  null;
      }
      return hasSelectedRadiologyTests;
  }

  const handleCategorySelection = (payload)=> {
    const value = payload[0].target.value;
    let hasSelectedRadiologyTests = prepareHasSelectedRadiologyTests(value);
    props.setHasSelectedRadiologyTests(hasSelectedRadiologyTests);
    previousChoiceExcludeParameterResult = {excludeParameterResult:props.excludeParameterResult,hasSelectedRadiologyTests : !hasSelectedRadiologyTests };
    if(validate.isNotEmpty(props.excludeParameterResult)){
      setStackedToastContent({ toastMessage: `The exclusion ${hasSelectedRadiologyTests ? 'Pathology' : 'Radiology'} triggers you recently added will be deleted.`, showUndo : true, handleUndoClick : handleUndoAction, closeOnUndoClick : true });
    }
    props.setExcludeParameterResult([]);
    showOrHideExclusionParametersFields(value!=null);
    resetParameterResultForm();
  }

  const handleUndoAction = () => {
    props.setExcludeParameterResult(previousChoiceExcludeParameterResult?.excludeParameterResult);
    props.setHasSelectedRadiologyTests(previousChoiceExcludeParameterResult?.hasSelectedRadiologyTests);
  }

    const observersMap = {
      "parameterName": [['select', (payload) => onExcludeParamterSelection(payload)]],
      "time" : [['change',(payload)=> validateParameterTimeDuartion(payload)]],
      "exclusionAddParameter": [['click',  addParameterResult]],
      'exclusionClear':[['click',resetParameterResultForm]],
      'triggerParameterTestType' : [['change',handleCategorySelection]]
    };

    const handleCombinationChange = (event) => {
      props.setExcludeParameterResultCombinationType(event.target.value);
    };
    
    const combinationForExclusionParameterResult = () => {
      return (
        <>
          <CombinationSelector
            selectedValue={props.excludeParameterResultCombinationType}
            onChange={handleCombinationChange}
            parameterResult={props.excludeParameterResult} 
            orCombinationId="excludeParameterResultOrCombinationId"
            andCombinationId="excludeParameterResultAndCombinationId"
            orCombinationValue="excludeParameterResultOrCombinationValue"
            andCombinationValue="excludeParameterResultAndCombinationValue"
            />
        </>
      );
    };
  
    return (
      <>
      <label className='custom-fieldset p-12 pb-0'>Exclusion Trigger</label>
      <div className='p-12'>
      <DynamicForm formJson={parameterForm} helpers={helpers} observers={observersMap}/>
        {validate.isNotEmpty(parameterResultMetaData)  && validate.isNotEmpty(props.excludeParameterResult) &&
          <React.Fragment>
            <label class="d-block mb-2 font-weight-bold custom-fieldset">Result</label>
            <div className=''>
              <div className={`card mb-3 me-0 `}>
                <DynamicGridHeight id="excludeParameterResult" metaData={parameterResultMetaData} dataSet={[...props.excludeParameterResult]}>
                  <CommonDataGrid {...({ ...parameterResultMetaData })} dataSet={[...props.excludeParameterResult]} callBackMap={callBackMapping} />
                </DynamicGridHeight>
                {props.excludeParameterResult.length>1 && <div className='card-footer border-top-0 bg-white'>
                {  combinationForExclusionParameterResult()}
                </div>}
              </div>
            </div>
          </React.Fragment>}
      </div>
      </>
    )
}

export default withFormHoc(ExcludeParameterForPathLabConfForm);