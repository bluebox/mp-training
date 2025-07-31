import React, { useEffect, useMemo, useState } from "react";
import Validate from "../../helpers/Validate";

//6 is the max limit of rows in a grid
const GRID_MAX_ROWS = 6;

//metaData and dataSet are mandatory props...
const DynamicGridHeight = (props) => {
    const { className, metaData, dataSet, topSummaryRows = [] , bottomSummaryRows = [], activeTabId, gridMaxRows = GRID_MAX_ROWS, gridMinRows = 0, extraRows = 0 } = props;
    const validate = Validate();
   
    const [gridId, setGridId] = useState(`my-grid-container-${props.id}`);
    const [headersHeight, setHeadersHeight] = useState(0);
    const [paginationRowHeight, setPaginationRowHeight] = useState(0);
    const [dowunloadButttonHeight, setDownloadButtonHeight] = useState(0);
    const [customColumnVisibleRowHeight, setCustomColumnVisibleRowHeight] = useState(0);

    const [summaryRowsHeight , setSummaryRowsHeight] = useState(0);
    const [rowsHeight , setRowsHeight] = useState(0);
    const [extraRowsHeight , setExtraRowsHeight] = useState(0);

    useEffect(() => {
        if(props.id) {
            setGridId(`my-grid-container-${props.id}`);     
        }
    }, [props.id]);


    useEffect(() => {

        if(!gridId || !metaData) {
            return;
        }

        setTimeout(() => {
            const parentDiv = document.getElementById(gridId);
        const gridContainer = parentDiv?.getElementsByClassName("dynamic-grid-height")?.[0];
        const totalRows = Number(gridContainer?.ariaRowCount ?? ((gridContainer?.attributes['aria-rowcount']?.value) ?? gridMaxRows));
        const headerRows = parentDiv?.getElementsByClassName("rdg-header-row").length;
        const summaryRows = parentDiv?.getElementsByClassName("rdg-summary-row").length;
        const gridRows = totalRows - headerRows - summaryRows;

        if(gridRows <= gridMaxRows){                             
            gridContainer.style.overflowY = "hidden";
        }
        else if(gridRows > gridMaxRows){
            gridContainer.style.overflowY = "scroll";
        }
        let rowsHeight = 0
        let rowHeight = (metaData.rowHeight ?? 35)
        if(gridRows >= gridMaxRows) {
            rowsHeight = gridMaxRows*rowHeight;
        } else if(gridRows < gridMinRows) {
            rowsHeight = gridMinRows*rowHeight;
        } else {
            rowsHeight = gridRows*rowHeight;
        }
        setRowsHeight(rowsHeight);
        setSummaryRowsHeight(summaryRows*(metaData.filterRowHeight ?? 35));
        setHeadersHeight(headerRows* (metaData.headerRowHeight ?? 35));
        if(validate.isNotEmpty(metaData.paginationInfo) && validate.isNotEmpty(metaData.paginationInfo.isPaginationRequired) && metaData.paginationInfo.isPaginationRequired){
            setPaginationRowHeight(56);
        }

        if(validate.isNotEmpty(metaData.downloadInfoList) && metaData.downloadInfoList.length > 0){
            setDownloadButtonHeight(45);
        }

        if((validate.isNotEmpty(metaData.enableCustomColumnVisibleOption) && metaData.enableCustomColumnVisibleOption) || activeTabId){
            setCustomColumnVisibleRowHeight(45);
        }
        setExtraRowsHeight(extraRows*(metaData.rowHeight ?? 35));

        },10)

    },[gridId,gridMaxRows ,metaData , dataSet ,extraRows , gridMinRows , topSummaryRows , bottomSummaryRows])

    /* useEffect(() => {
        if(validate.isEmpty(metaData)){
            return;
        }

        setGridRows(dataSet.length);
        setGridRowHeight(validate.isNotEmpty(metaData.rowHeight) ? metaData.rowHeight : 35);


        const parentDiv = document.getElementById(gridId).childNodes[0];
        for(const child of parentDiv.childNodes){
            if(child?.classList?.contains("dynamic-grid-height")){
                setTargetDiv(child);
            }
        }
        
        let showFilter = false;

        metaData.columns.forEach((column) => {
            if(column.showFilter){
                showFilter = true;
                return;
            }
        });

        if(validate.isNotEmpty(metaData.headerRowHeight)){
            setHeaderRowHeight(metaData.headerRowHeight);
        }
        else{
            setHeaderRowHeight(35);
        }

        if(showFilter){
            if(validate.isNotEmpty(metaData.filterRowHeight)) 
                setFilterRowHeight(metaData.filterRowHeight);
            else
                setFilterRowHeight(35);
        }
        else{
            setFilterRowHeight(0);
        }        

        if(validate.isNotEmpty(topSummaryRows)){
            if(validate.isNotEmpty(metaData.filterRowHeight)) 
                setTopSummaryRowsHeight(metaData.filterRowHeight * topSummaryRows.length);
            else
                setTopSummaryRowsHeight(35 * topSummaryRows.length);
        }
        else{
            setTopSummaryRowsHeight(0);
        }

        if(validate.isNotEmpty(bottomSummaryRows)){
            if(validate.isNotEmpty(metaData.filterRowHeight)) 
                setTopSummaryRowsHeight(metaData.filterRowHeight * bottomSummaryRows.length);
            else
                setTopSummaryRowsHeight(35 * bottomSummaryRows.length);
        }
        else{
            setBottomSummaryRowsHeight(0);
        }    
        
        if(validate.isNotEmpty(metaData.paginationInfo) && validate.isNotEmpty(metaData.paginationInfo.isPaginationRequired) && metaData.paginationInfo.isPaginationRequired){
            setPaginationRowHeight(56);
        }

        if(validate.isNotEmpty(metaData.downloadInfoList) && metaData.downloadInfoList.length > 0){
            setDownloadButtonHeight(45);
        }

        if((validate.isNotEmpty(metaData.enableCustomColumnVisibleOption) && metaData.enableCustomColumnVisibleOption) || activeTabId){
            setCustomColumnVisibleRowHeight(45);
        }

        if(validate.isNotEmpty(targetDiv)){          
            if(gridRows <= gridMaxRows){                             
                targetDiv.style.overflowY = "hidden";
            }
            else if(gridRows > gridMaxRows){
                targetDiv.style.overflowY = "scroll";
            }
        }
        

    }, [metaData, dataSet, topSummaryRows, bottomSummaryRows, activeTabId, gridMaxRows, gridMinRows, extraRows]); */

    const totalGridHeight = useMemo(() => {

        return (rowsHeight + headersHeight + summaryRowsHeight + paginationRowHeight +dowunloadButttonHeight+ customColumnVisibleRowHeight + extraRowsHeight);
        // if(gridRows > gridMaxRows){
        //    return (gridRowHeight  gridMaxRows) + headerRowHeight + filterRowHeight + topSummaryRowsHeight + bottomSummaryRowsHeight + paginationRowHeight + dowunloadButttonHeight + customColumnVisibleRowHeight + (extraRows  gridRowHeight);
        // }
        // else if(gridRows < gridMinRows){
        //    return (gridRowHeight  gridMinRows) + headerRowHeight + filterRowHeight + topSummaryRowsHeight + bottomSummaryRowsHeight + paginationRowHeight + dowunloadButttonHeight + customColumnVisibleRowHeight + (extraRows  gridRowHeight);
        // }
        // else {
        //     return (gridRowHeight  gridRows) + headerRowHeight + filterRowHeight + topSummaryRowsHeight + bottomSummaryRowsHeight + paginationRowHeight + dowunloadButttonHeight + customColumnVisibleRowHeight + (extraRows  gridRowHeight);
        // }
        
    }, [rowsHeight, summaryRowsHeight, headersHeight,paginationRowHeight,dowunloadButttonHeight,customColumnVisibleRowHeight , extraRowsHeight]);

    return(
        <React.Fragment>
            <div id={gridId} className={className} style={{height:  `${totalGridHeight}px`}} >
                {props.children}
            </div>
        </React.Fragment>
    );
};

export default DynamicGridHeight;