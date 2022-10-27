"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var CaptchaEndpointPipe = /** @class */ (function () {
    function CaptchaEndpointPipe() {
    }
    // strip '/' character from the end of the given path.
    CaptchaEndpointPipe.prototype.transform = function (value) {
        if ((value === undefined) || (value === null)) {
            return value;
        }
        return value.trim().replace(/\/+$/g, '');
    };
    CaptchaEndpointPipe.decorators = [
        { type: core_1.Pipe, args: [{ name: 'captchaEndpoint' },] },
    ];
    /** @nocollapse */
    CaptchaEndpointPipe.ctorParameters = function () { return []; };
    return CaptchaEndpointPipe;
}());
exports.CaptchaEndpointPipe = CaptchaEndpointPipe;
//# sourceMappingURL=captcha-endpoint.pipe.js.map