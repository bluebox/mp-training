// function cardpay() {
//     var T = document.getElementById("card");
//     T.style.display = "block";  // <-- Set it to block
// }

$(document).ready(function(){
    $('#card').hide();
    
    
    $('fieldset#bottom').click(function(){
        if($('#acc').is(":checked")){
            $('#card').show();
        }else {
            $('#card').hide();
        }     
    });
    
    
});
