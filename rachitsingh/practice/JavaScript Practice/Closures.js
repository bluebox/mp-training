function z() {
    var a = 123;
    function y(){
        console.log(a);
        var b = 12;
         
    function x(){
        console.log(a,b);
        }
       x();
    }
    y();
}
z();