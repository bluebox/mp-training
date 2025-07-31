import { useContext } from "react";
import Validate from "./Validate";
import { UserContext } from "../components/Contexts/UserContext";

export default function UserUtilHelper() {
    const validate = Validate();
    const {userSessionDetails} = useContext(UserContext);
    const marketingRoles = (validate.isNotEmpty(userSessionDetails) && validate.isNotEmpty(userSessionDetails.roles) && userSessionDetails.moduleName === 'marketing') ? userSessionDetails.roles : [];

    const hasRole=(userRole)=>{
        let isHasRole = false
        if (validate.isEmpty(marketingRoles) || validate.isEmpty(userRole)) {
            return isHasRole;
        }
        isHasRole = marketingRoles.includes(userRole);
        return isHasRole;
    }

    const hasAnyRole=(userRoles)=>{
        let isHasRoles = false;
        if (validate.isEmpty(marketingRoles) || validate.isEmpty(userRoles)) {
            return isHasRoles;
        }
        isHasRoles = userRoles.some(userRole => marketingRoles.includes(userRole));
        return isHasRoles;
    }

    const hasAllRoles = (userRoles) => {
        let isHasRoles = false;
        if (validate.isEmpty(marketingRoles) || validate.isEmpty(userRoles)) {
            return isHasRoles;
        }
        isHasRoles = userRoles.every(userRole => marketingRoles.includes(userRole));
        return isHasRoles;
    }

    return Object.freeze({
        hasRole,
        hasAnyRole,
        hasAllRoles,
    })
}