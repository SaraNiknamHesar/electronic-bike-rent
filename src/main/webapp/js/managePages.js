const customers_paymentMethods_changeColor = () => {
    const pM_items = document.getElementsByClassName('paymentMethod');

    for (const element of pM_items) {
        let color_toAdd;
        switch (element.innerHTML) {
            case 'Credit Card' :
                color_toAdd = color_CREDIT;
                break;
            case 'Paypal' :
                color_toAdd = color_PAYPAL;
                break;
            case 'VISA Debit' :
                color_toAdd = color_VISA;
                break;
            default :
                color_toAdd = 'gray';
                break;
        }
        element.style.backgroundColor = color_toAdd;
        element.style.color = 'black';
    }
}
const postalCode_nullToSlash = () => {
    const pC_items = document.getElementsByClassName('postalCode');

    for (const element of pC_items) {
        if (element.innerHTML === '') {
            element.innerHTML = '-';
        }
    }
}

const nullToSlash = () => {
    const td_items = document.getElementsByTagName('td');
    for (const element of td_items) {
        let span_item = element.children[0];

        if (span_item.innerHTML === '') {
            span_item.innerHTML = '-';
        }
        if (span_item.classList.contains("rental-delivery") && span_item.innerHTML === '-') {
            span_item.innerHTML = "In delivery";
        }
    }
}

const paymentMethods_paymentMethods_changeColor = () => {
    const pM_items = document.getElementsByClassName('ActiveInactive');

    for (const element of pM_items) {

        let color_toAdd;
        switch (element.innerHTML) {
            case 'Active' :
                color_toAdd = color_Active;
                break;
            case 'Inactive' :
                color_toAdd = color_Inactive;
                break;
            default :
                color_toAdd = 'gray';
                break;
        }
        element.style.backgroundColor = color_toAdd;
        element.style.color = 'black';
    }
}

const color_PAYPAL = '#7ab2fa';
const color_VISA = '#fa7a7a';
const color_CREDIT = '#bee0ab';
const color_Active = '#bee0ab';
const color_Inactive = '#fa7a7a';
customers_paymentMethods_changeColor();
paymentMethods_paymentMethods_changeColor();
nullToSlash();

