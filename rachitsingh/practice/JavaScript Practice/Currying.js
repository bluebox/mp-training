const calculatePrice = (taxRate) => (price) => 
    price + price*(taxRate/100);

const calculateTax = calculatePrice(10);
const calculateTaxedPrice = calculateTax(280);
console.log(calculateTaxedPrice);
