// login(), logout(), getUserInformation() are returned outside the Immediately Invoked Function Expression (IIFE),
// but they still have access to loggedInUser which is inside the scope of this function.
// Even though the outer function is done executing, still those functions remember the variable loggedInUser =>
    // these returned functions forms a closure over the outer IIFE.

// Even though loggedInUser is not global scoped, still all three functions can access it, because they were also created in same scope.
// JS remembers the scope chaining.
const authModule = (function(){
    let loggedInUser = null;

    function login(username, password){
        loggedInUser = username;
    }

    function logout(){
        loggedInUser = null;
    }

    function getUserInformation(){
        return loggedInUser;
    }
    return{
        login,
        logout,
        getUserInformation
    };
})();

authModule.login("rachitsingh@medplusindia.com", "*********");
console.log(authModule.getUserInformation());
authModule.logout();