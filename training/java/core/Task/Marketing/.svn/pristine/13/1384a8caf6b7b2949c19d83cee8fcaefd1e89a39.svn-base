import React, { useEffect, useState } from "react";
import Validate from "../../helpers/Validate";

const CurrencyFormatter = (props) => {

    const { data, decimalPlaces } = props;
    const [fromattedAmount, setFormattedAmount] = useState(null);
    const validate = Validate();

    const fotmattedValue = () => {
        let amount = "";
        let positiveValue = data;
        if (data < 0) {
            positiveValue = data * -1;
        }

        if (validate.isNotEmpty(data) && !isNaN(data)) {
            let formatterObject = {};

            if (!validate.isNotEmpty(decimalPlaces)) {
                formatterObject = { minimumFractionDigits: 2, maximumFractionDigits: 2 };
            }
            else if (decimalPlaces >= 0 && decimalPlaces <= 20) {
                formatterObject = { minimumFractionDigits: decimalPlaces, maximumFractionDigits: decimalPlaces };
            }
            else {   //To Show the number as it is without rounding off or appending zeroes 
                if (Number.isInteger(Number(positiveValue))) {                  //If number is integer
                    formatterObject = { minimumFractionDigits: 0, maximumFractionDigits: 0 };
                }
                else if (Number.isInteger(Number(positiveValue) * 10)) {      //If number has only one decimal place
                    formatterObject = { minimumFractionDigits: 1, maximumFractionDigits: 1 };
                }
                else {
                    formatterObject = { maximumFractionDigits: 20 };
                }
            }
            amount = new Intl.NumberFormat('en-IN', formatterObject).format(positiveValue);
        }
        else {
            amount = "-";
        }

        return amount;
    }


    useEffect(() => {
        setFormattedAmount(fotmattedValue());
    }, [props]);

    return (validate.isNotEmpty(data) && !isNaN(data) ? <React.Fragment>
        {data < 0 ? "-" : ""}
        <svg xmlns="http://www.w3.org/2000/svg" width="9" height="11" viewBox="0 0 9 11">
            <g id="rupee_symbol" data-name="Rupee Symbol" transform="translate(-399 -235)">
                <rect id="Rectangle_13777" data-name="Rectangle 13777" width="9" height="11" transform="translate(399 235)" fill="none"/>
                <path id="Subtraction_172" data-name="Subtraction 172" d="M18284.426-4686.752a.554.554,0,0,1-.385-.157l-3.621-3.5a.567.567,0,0,1-.131-.605.558.558,0,0,1,.512-.352l1.475.017h.021a1.6,1.6,0,0,0,1.248-.584l.4-.483-3.125-.03a.546.546,0,0,1-.389-.165.545.545,0,0,1-.162-.394.557.557,0,0,1,.561-.551l3.137.035-.395-.492a.791.791,0,0,0-.055-.063l-.021-.025a.163.163,0,0,0-.012-.014.046.046,0,0,0-.014-.015l0,0a1.648,1.648,0,0,0-1.156-.487l-1.465-.017a.559.559,0,0,1-.395-.165.58.58,0,0,1-.158-.394.556.556,0,0,1,.557-.555l5.125.06a.535.535,0,0,1,.393.165.515.515,0,0,1,.158.39.559.559,0,0,1-.557.559l-1.4-.021.207.432a2.941,2.941,0,0,1,.168.445l.064.211.949.013a.554.554,0,0,1,.549.559.555.555,0,0,1-.559.551l-.953-.013-.066.212a2.781,2.781,0,0,1-.787,1.25,2.811,2.811,0,0,1-1.318.661l-.1.017.02.246-.158.157,2.229,2.155a.56.56,0,0,1,.176.381.575.575,0,0,1-.162.4A.529.529,0,0,1,18284.426-4686.752Z" transform="translate(-17880.125 4930.875)" fill={props.color ? props.color : "#343a40"} />
            </g>
        </svg>
        {fromattedAmount}
    </React.Fragment>
        : <React.Fragment>
            {fromattedAmount}
        </React.Fragment>

    );

}

export default CurrencyFormatter;