const DataGridHelper = () => {

    const multiItemCampaignData = (promotionType) => {
        let campaignData = {
            'idProperty': 'rowIndex', 
            "rowDataKey":"rowClass",
            "rowClass" :{
                type: "FUNCTION",
                returnType: "STRING",
                name: 'rowClass' 
            }, 
            'paginationInfo' : {
                'loadOnScroll' : true,
                'limit' : 100
            },
            'initialFilters' : {
                'campaignId' : {
                    'filterType':"NUMBER",
                    'selectedOperator':'EQUALS_NUMERIC',
                },
                'campaignName' : {
                    'filterType':"STRING",
                    'selectedOperator':'CONTAINS_STRING',
                },
                'status' : {
                    'filterType':"STRING",
                    'selectedOperator':'CONTAINS_STRING',
                },
                'createdBy' : {
                    'filterType':"STRING",
                    'selectedOperator':'CONTAINS_STRING',
                },
                'approvedBy' : {
                    'filterType':"STRING",
                    'selectedOperator':'CONTAINS_STRING',
                },
                'cloneReferenceId' : {
                    'filterType':"NUMBER",
                    'selectedOperator':'EQUALS_NUMERIC',
                },
                'applicableType': {
                    'filterType':"STRING",
                    'selectedOperator':'CONTAINS_STRING',
                },
                'channels' : {
                    'filterType':"STRING",
                    'selectedOperator':'CONTAINS_STRING',
                },
            },
            'columns': [
                {
                    "columnName": promotionType,
                    "rowDataKey": "campaign",
                    "headerCellClassName":"text-center",
                    "isFrozen":true,  
                    "isGroup": true,                                                                              
                    "childColumns": [
                        {
                            "columnName": "ID",
                            "rowDataKey": "campaignId",
                            "resizable": "true",
                            "showFilter": true,
                            "isSortable": true,
                            "columnType" : "NUMBER",
                            "customRowRenderingFunction":{
                                type: "FUNCTION",
                                returnType: "REACT_NODE",
                                name: 'campaignId' 
                            },
                        },
                        {
                            'columnName': "Name",
                            'rowDataKey': 'campaignName',
                            "resizable": "true",
                            "showFilter": true,
                            "isSortable": true,
                            "columnType" : "STRING"
                        },
                        {
                            'columnName': 'Status',
                            "resizable": "true",
                            "width":"100px",
                            'rowDataKey': 'status',
                            "showFilter": true,
                            "isSortable": true,
                            "columnType" : "STRING",
                            "customRowRenderingFunction":{
                                type: "FUNCTION",
                                returnType: "REACT_NODE",
                                name: 'status' 
                            }
                        }
                    ],
                },
                {
                    'columnName': 'From Date',
                    "resizable": "true",
                    'rowDataKey': 'fromDate',
                },
                {
                    'columnName': 'Effective Date',
                    "resizable": "true",
                    'cellClassName':"text-center",
                    'rowDataKey': 'effectiveDate',
                },
                {
                    'columnName': 'To Date',
                    'cellClassName':"text-center",
                    "resizable": "true",
                    'rowDataKey': 'toDate',
                },
                {
                    'columnName': 'Created By',
                    "resizable": "true",
                    'rowDataKey': 'createdBy',
                    "showFilter": true,
                    "isSortable": true,
                    "columnType" : "STRING"
                },
                {
                    'columnName': 'Date Created',
                    "resizable": "true",
                    'rowDataKey': 'dateCreated',
                },
                {
                    'columnName': 'Approved / Rejected By',
                    "resizable": "true",
                    'rowDataKey': 'approvedBy',
                    "showFilter": true,
                    "isSortable": true,
                    "columnType" : "STRING"
                },
                {
                    'columnName': 'Date Approved / Rejected',
                    "resizable": "true",
                    'cellClassName':"text-center",
                    'rowDataKey': 'dateApproved',
                },
                {
                    'columnName': 'Clone Reference ID',
                    "resizable": "true",
                    'rowDataKey': 'cloneReferenceId',
                    'cellClassName':"text-center",
                    "showFilter": true,
                    "isSortable": true,
                    "columnType" : "NUMBER"
                },
                {
                    'columnName': 'Appicable Type',
                    "resizable": "true",
                    'rowDataKey': 'applicableType',
                    "showFilter": true,
                    "isSortable": true,
                    "columnType" : "STRING"
                },
                {
                    'columnName': 'Channels',
                    "resizable": "true",
                    'rowDataKey': 'channels',
                    "showFilter": true,
                    "isSortable": true,
                    "columnType" : "STRING"
                },
                {
                    "columnName": "Action",
                    "resizable": "true",
                    "rowDataKey": "Action",
                    "frozenColumnPosition":"RIGHT",
                    "isFrozen":true,
                    "customRowRenderingFunction": {
                        type: "FUNCTION",
                        returnType: "REACT_NODE",
                        name: 'renderActionColumn'
                    }
                }
            ]
        }
        return campaignData;
    }
   
    const regularPromotionData = (promotionType) => {
        let regPromoData = multiItemCampaignData(promotionType)
        regPromoData.initialFilters['promotionLevel'] = {
                'filterType':"STRING",
                'selectedOperator':'CONTAINS_STRING'
        }
        regPromoData.columns.splice(3, 0, {
            'columnName': 'Promotion Level',
            "resizable": "true",
            'rowDataKey': 'promotionLevel',
            "showFilter": true,
            "isSortable": true,
            "columnType" : "STRING"
        });
        return regPromoData
    }

    const regularPromotionDetails = (isEditable) => {
        let regularPromotionData = {
            'idProperty': 'rowIndex', 
            "rowDataKey":"rowClass",
            "rowClass" :{
                type: "FUNCTION",
                returnType: "STRING",
                name: 'rowClass' 
            }, 
            'paginationInfo' : {
                'loadOnScroll' : true,
                'limit' : 100
            },
            'initialFilters' : {
                'productCategory' : {
                    'filterType':"STRING",
                    'selectedOperator':'CONTAINS_STRING',
                },
                'loyaltyType' : {
                    'filterType':"STRING",
                    'selectedOperator':'CONTAINS_STRING',
                },
                'region' : {
                    'filterType':"STRING",
                    'selectedOperator':'CONTAINS_STRING',
                },
                'fromValue' : {
                    'filterType':"NUMBER",
                    'selectedOperator':'EQUALS_NUMERIC',
                },
                'toValue': {
                    'filterType':"NUMBER",
                    'selectedOperator':'EQUALS_NUMERIC',
                },
            },
            'columns': [
                {
                    "columnName": "Product Category",
                    "rowDataKey":"productCategory",
                    "resizable": "true",
                    "showFilter": true,
                },
                {
                    "columnName": "Loyalty Type",
                    "rowDataKey":"loyaltyType",
                    "resizable": "true",
                    "showFilter": true,
                },
                {
                    "columnName": "Discount Type",
                    "rowDataKey":"discountType",
                    "resizable": "true",
                    "showFilter": true,
                },
                {
                    "columnName": "Region",
                    "rowDataKey":"region",
                    "resizable": "true",
                    "showFilter": true,
                },
                {
                    "columnName": "From Value",
                    "rowDataKey":"fromValue",
                    "resizable": "true",
                    "showFilter": true,
                    "columnType" : "NUMBER",

                },
                {
                    "columnName": "To Value",
                    "rowDataKey":"toValue",
                    "resizable": "true",
                    "showFilter": true,
                    "columnType" : "NUMBER",
                },
                {
                    "columnName": "Discount %",
                    "rowDataKey":"discountPercentage",
                    "columnHeaderIcon": "EDIT_ICON",
                    "isEditable":isEditable,
                    "defaultColumnEditorProps": {"placeholder":"Discount %", "pattern": "^\\d+(\\.?(\\d?)+)$"},
                    "editorOptions": {
                        "editOnFocus":true
                    },
                    "columnType" : "NUMBER",
                    "isFrozen":true,
                    "frozenColumnPosition":"RIGHT",
                },
                {
                    "columnName": "Payback Points %",
                    "rowDataKey":"paybackPercentage",
                    "columnHeaderIcon": "EDIT_ICON",
                    "isEditable": isEditable,
                    "defaultColumnEditorProps": {"placeholder":"Max Payback Points", "pattern": "^\\d+(\\.?(\\d?)+)$"},
                    "editorOptions": {
                        "editOnFocus":true
                    },
                    "columnType" : "NUMBER",
                    "isFrozen":true,
                    "frozenColumnPosition":"RIGHT",
                }
            ]
        }

        return regularPromotionData;
    }

    const parameterResultMetaData=()=>{
        let parameterResultMetaData ={
            "idProperty": "paramterValue",
            "rowClass" :{
                type: "FUNCTION",
                returnType: "STRING",
                name: 'rowClass' 
            },
            "columns" : [
                {
                    "columnName": "Parameter",
                    "rowDataKey": "parameterName",
                    "resizable": true,
                },
                {
                    "columnName": "Regular Expression",
                    "rowDataKey": "rangeType",
                    "resizable": false,
                },
                {
                    "columnName": "Minimum value",
                    "rowDataKey": "minValue",
                    "resizable": false,
                },
                {
                    "columnName": "Maximum value",
                    "rowDataKey": "maxValue",
                    "resizable": false,
                },
                {
                    "columnName": "Absolute value",
                    "rowDataKey": "absoluteValue",
                    "resizable": false,
                },
                {
                    "columnName": "Time (in Months)",
                    "rowDataKey": "time",
                    "resizable": false,
                    "columnType" : "NUMBER"
                },
                {
                    "columnName": "Action",
                    "rowDataKey": "action",
                    "resizable": true,
                    "cellClassName" : "border-end-0",
                    "customRowRenderingFunction":{
                        "type": "FUNCTION",
                        "returnType": "REACT_NODE",
                        "name": "renderActionColumn"
                    }
                }
            ],
        }
        return parameterResultMetaData;
    }

    const exclusionParameterResultMetaData=()=>{
        let exclusionParameterResultMetaData ={
            "idProperty": "parameterName",
            "rowClass" :{
                type: "FUNCTION",
                returnType: "STRING",
                name: 'rowClass' 
            },
            "columns" : [
                {
                    "columnName": "Trigger Name",
                    "rowDataKey": "parameterName",
                    "resizable": true,
                },
                {
                    "columnName": "Number of days",
                    "rowDataKey": "time",
                    "resizable": false,
                    "columnType" : "NUMBER"
                },
                {
                    "columnName": "Action",
                    "rowDataKey": "action",
                    "resizable": true,
                    "cellClassName" : "border-end-0",
                    "customRowRenderingFunction":{
                        "type": "FUNCTION",
                        "returnType": "REACT_NODE",
                        "name": "renderExclusionActionColumn"
                    }
                }
            ],
        }
        return exclusionParameterResultMetaData;
    }

    const parameterResultData=()=>{
        let parameterResultData ={
            "idProperty": "parameterResult",
            "columns" : [
                {
                    "columnName": "Parameter ID",
                    "rowDataKey": "parameterId",
                    "resizable": true,
                },
                {
                    "columnName": "Parameter Name",
                    "rowDataKey": "parameterName",
                    "resizable": true,
                },
                {
                    "columnName": "Regular Expression",
                    "rowDataKey": "rangeType",
                    "resizable": false,
                },
                {
                    "columnName": "Minimum value",
                    "rowDataKey": "minValue",
                    "resizable": false,
                },
                {
                    "columnName": "Maximum value",
                    "rowDataKey": "maxValue",
                    "resizable": false,
                },
                {
                    "columnName": "Absolute value",
                    "rowDataKey": "absoluteValue",
                    "resizable": false,
                },
                {
                    "columnName": "Time (in Months)",
                    "rowDataKey": "time",
                    "resizable": false,
                    "columnType" : "NUMBER"
                },
            ],
        }
        return parameterResultData;
    }

    const excludeParameterResultData=()=>{
        let excludeParameterResultData ={
            "idProperty": "excludeParameterResult",
            "columns" : [
                {
                    "columnName": "Excluded Trigger ID",
                    "rowDataKey": "parameterId",
                    "resizable": true,
                },
                {
                    "columnName": "Trigger Name",
                    "rowDataKey": "parameterName",
                    "resizable": true,
                },
                {
                    "columnName": "Trigger Type",
                    "rowDataKey": "parameterType",
                    "resizable": false,
                },
                {
                    "columnName": "Time (in Days)",
                    "rowDataKey": "time",
                    "resizable": false,
                    "columnType" : "NUMBER"
                },
            ],
        }
        return excludeParameterResultData;
    }

    const getBadgeIcon=(applicableType)=>{
        switch (applicableType) {
            case 'Pharmacy':
                return 'badge-pending';
            case 'Path Labs':
                return 'badge-rejected';
            default:
                break;
        }
    }

    return Object.freeze({
        multiItemCampaignData, regularPromotionDetails, regularPromotionData, parameterResultMetaData, exclusionParameterResultMetaData,parameterResultData,excludeParameterResultData, getBadgeIcon
    })
  
}
export default DataGridHelper;