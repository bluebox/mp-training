import React from "react";
import { NavItem, NavLink } from "reactstrap";

const CustomNavItem = ({currentTabId, activeTabId, setActiveTabId, tabTitle}) => {

    const getClassNameForTab = (tabId) => {
        if(tabId < activeTabId) {
            return "completed";
        }
        return tabId === activeTabId ? "active" : "unCompleted"
    }

  return (
    <>
      <NavItem>
        <NavLink
          className={getClassNameForTab(currentTabId)}
          onClick={() => {
            if (activeTabId < currentTabId) {
              return;
            }
            setActiveTabId(currentTabId);
          }}
        >
        {tabTitle}
        <span class="ms-2">
            <svg xmlns="http://www.w3.org/2000/svg" width="12.162" height="12" viewBox="0 0 12.162 12">
                <g id="tickmark_black_icon_18px" transform="translate(-1457.919 -558)">
                    <rect id="Rectangle_5706" data-name="Rectangle 5706" width="12" height="12" rx="3" transform="translate(1458 558)" fill="none"></rect>
                    <path id="check" d="M13.333,6,6.917,12.417,4,9.5" transform="translate(1455.333 554.667)" fill="rgba(0,0,0,0)"
                    stroke={currentTabId <= activeTabId ? "#fff" : "#6c757d"}
                    stroke-linecap="round" stroke-linejoin="round" stroke-width="2">
                    </path>
                </g>
            </svg>
        </span>
        </NavLink>
      </NavItem>
    </>
  );
};

export default CustomNavItem;
