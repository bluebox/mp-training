import React, { useCallback, useContext, useEffect, useMemo, useState } from "react";
import { Button, InputGroup } from "react-bootstrap";
import { Menu, MenuItem, Sidebar, SubMenu, useProSidebar } from "react-pro-sidebar";
import SearchIcon from "../../src/images/Search_icon.svg";
import SearchwithOutBorder_Icon from "../../src/images/SearchwithOutBorder_icon.svg";
import TopArrow from "../../src/images/TopArrow_icon.svg";
import BottomArrow from "../../src/images/bottomArrow_icon.svg";
import RegularPromotionIcn from "../../src/images/regular_promotion_icon.svg";
import PopupIcon from "../../src/images/popup-configure-icn-24.svg";
import LeftArrow from "../../src/images/leftArrow_icon.svg";
import RightArrow from "../../src/images/rightArrow_icon.svg";
import CampaignOffersIcn from "../../src/images/campaign-offers-icn-36.svg"; 
import { SidebarContext, UserContext } from "../components/Contexts/UserContext";
import InputWithOutFlotingPoint from "../components/common/InputWithOutFlotingPoint";
import Validate from "../helpers/Validate";
import { isActiveMenu, MARKETING_NAVIGATION_MENU } from "../services/ServiceConstants";
const Navigationab = (props) => {

    const menuBarItems = MARKETING_NAVIGATION_MENU;
    const [selectedMenu, setSelectedMenu] = useState(props.location.pathname);
    const { setSidebarCollapsedFlag } = useContext(SidebarContext);
    const { collapsed, collapseSidebar } = useProSidebar();
    const [isGettingBySearch, setGettingBySearch] = useState(false);
    const [searchValue, setSearchValue] = useState('');
    const validate = Validate();
    const usercontext = useContext(UserContext);

    const sidebarOperation = () => {
        setSidebarCollapsedFlag(!collapsed);
        collapseSidebar(!collapsed)
    }

    const getAllDisplayNames = (item) => {
        let allDisplayNames = [];
        if (validate.isNotEmpty(item)) {
            if (validate.isNotEmpty(item.subLinks)) {
                item.subLinks.forEach(link => {
                    allDisplayNames.push(...getAllDisplayNames(link))
                });
            } else {
                allDisplayNames.push(item.displayName.toLowerCase());
            }
        }
        return allDisplayNames;
    }

    useEffect(() => {
        setSelectedMenu(props.location.pathname);
    },[props.location.pathname])

    const setSearchedMenuItems = (event) => {
        if (event === "close") {
            setSearchValue('');
            setGettingBySearch(false);
        } else {
            setSearchValue(event.target.value ? event.target.value.toLowerCase() : '');
            setGettingBySearch(event.target.value ? true : false)

        }
    }
    const prepareSearchMap = (searchMap, value) => {
        let allDisplayNames = [...getAllDisplayNames(value)];
        searchMap[value.id] = allDisplayNames.join(" ");
        if (value.subLinks) {
            value.subLinks.map(value => {
                prepareSearchMap(searchMap, value);
            })
        }
    }

    const searchMap = useMemo(() => {
        if (Validate().isEmpty(menuBarItems)) {
            return {};
        }
        let searchMap = {};

        menuBarItems.map(value => {
            prepareSearchMap(searchMap, value);

        })

        return searchMap;

    }, [menuBarItems])
    const filterItem = (item) => {
        let searchItem = searchMap[item.id];
        return !searchValue || ((validate.isNotEmpty(item.subLinks) && searchItem.includes(searchValue)) || (validate.isEmpty(item.subLinks) && item.displayName.toLowerCase().includes(searchValue)));

    }

    const handleOnClickMenu = (e, menuItem) => {
        if (e.type !== 'contextmenu') {
            e.preventDefault();
        }
        props.history.push(`${menuItem.url}`);
        return;
    }

    const renderIcon = (param) => {
        switch(param){
            case "Multi Item Campaign":
                return CampaignOffersIcn;
            case "Regular Promotion":
                return RegularPromotionIcn;
            case "Campaign":
                return CampaignOffersIcn;
            case "Complimentary Gift":
                return CampaignOffersIcn;
            case "Popup Configuration":
                return PopupIcon;
        }
    }

    const getAllSubLinks = (subLinks) => {
        let allSubLinks = [];
        if (validate.isNotEmpty(subLinks)) {
            subLinks.forEach(subLink => {
                allSubLinks.push(...getAllSubLinks(subLink.subLinks));
                allSubLinks.push(subLink);
            })
        }
        return allSubLinks;
    }

    const getNavLinks = useCallback((items, level) => {

        if (validate.isEmpty(items)) {
            return <React.Fragment></React.Fragment>
        }

        return <React.Fragment>
            {items.filter(item => filterItem(item)).map(item => {
                let allSubLinks = [];
                if (validate.isNotEmpty(item.subLinks)) {
                    allSubLinks.push(...getAllSubLinks(item.subLinks));
                }

                return validate.validateRole(item.role)  && validate.isNotEmpty(item.subLinks) ?
                    <SubMenu label={item.displayName}
                        defaultOpen={isGettingBySearch || allSubLinks.find(value => value.url == selectedMenu)}
                        style={level >= 3 ? { padding: `0rem 0.5rem 0rem ${(level - 2) + 0.5}rem` } : { padding: `0rem 0.5rem` }}
                        level={level}
                    >
                        {getNavLinks(item.subLinks, level + 1)}
                    </SubMenu>
                    : validate.validateRole(item.role)  &&
                    <MenuItem
                        active={isActiveMenu(selectedMenu,item.url,props.match?.params)}
                        href={item.url}
                        title={item.displayName}
                        onClick={(e) => {
                            handleOnClickMenu(e, item);
                        }}
                        style={level >= 3 ? { padding: `0rem 0.5rem 0rem ${(level - 2) + 0.5}rem` } : { padding: `0rem 0.5rem` }}
                    >
                        {item.displayName}
                    </MenuItem>
            })}
        </React.Fragment>


    }, [isGettingBySearch, selectedMenu, filterItem]);

    return (
        <React.Fragment>
            {usercontext.isModuleRightsAvailable && <Sidebar transitionDuration={500} className="sidebar shadow-sm">
                <header className={`d-flex justify-content-center ${collapsed ? 'me-3' : ''}`}>
                    {collapsed && <button className="btn btn-link p-2 me-3" onClick={() => sidebarOperation()}>
                        <img src={SearchIcon} alt="Seach Icon" />
                    </button>}
                    {!collapsed && <div className="p-2 search-bar" >
                        <InputGroup size={"sm"}>
                            <InputGroup.Text>
                                <img src={SearchwithOutBorder_Icon} alt="Search Icon" />
                            </InputGroup.Text>
                            <InputWithOutFlotingPoint type="text" size="sm" placeholder="Filter Menu" value={searchValue} onChange={setSearchedMenuItems} clearSearchText={() => { setSearchedMenuItems("close") }} />
                        </InputGroup>
                    </div>}
                </header>
                <Menu iconShape="circle" renderExpandIcon={({ open }) => <span>{open ? <img src={TopArrow} alt="Top Arrow" /> : <img src={BottomArrow} alt="Bottom Arrow" />}</span>}>
                    {validate.isNotEmpty(menuBarItems) && menuBarItems.filter(item => filterItem(item)).map((value) => {
                        let allSubLinks = [];
                        if (validate.isNotEmpty(value.subLinks)) {
                            allSubLinks.push(...getAllSubLinks(value.subLinks));
                        }
                        return <React.Fragment>
                           { validate.validateRole(value.role)  && validate.isNotEmpty(value.subLinks) ?
                                <SubMenu label={value.displayName}
                                    defaultOpen={isGettingBySearch || allSubLinks.find(value => value.url == selectedMenu)}
                                    style={{ padding: '0rem 0.5rem' }}
                                    title={value.displayName}
                                    icon={<img src={renderIcon(value.displayName)} alt={value.displayName} role="img" aria-label={value.displayName}/>}>
                                    {getNavLinks(value.subLinks, 2)}
                                </SubMenu> :
                              validate.validateRole(value.role) &&  
                              <MenuItem
                                    icon={<img src={""} alt={" "} />}
                                    active={selectedMenu === value.url}
                                    style={{ padding: '0rem 0.5rem' }}
                                    href={value.url}
                                    title={value.displayName}
                                    onClick={(e) => {
                                        handleOnClickMenu(e, value);
                                    }}>
                                    {value.displayName}
                                </MenuItem>
                            }
                        </React.Fragment>
                    })}
                </Menu>
                <footer>
                    <Button variant=" " className="menufooter btn" onClick={() => sidebarOperation()}>
                        <img className="icon-hover btn-link" src={!collapsed ? LeftArrow : RightArrow} alt={!collapsed ? "Left Arrow" : "Right Arrow"} />
                    </Button>
                </footer>
            </Sidebar>}
        </React.Fragment>
    )
}

export default Navigationab;