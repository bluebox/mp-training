"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var forms_1 = require("@angular/forms");
var captcha_service_1 = require("./captcha.service");
var CorrectCaptchaDirective = /** @class */ (function () {
    function CorrectCaptchaDirective(captchaService) {
        this.captchaService = captchaService;
    }
    CorrectCaptchaDirective.prototype.validate = function (c, onBlur) {
        var _this = this;
        if (c) {
            // cache the control for using on blur
            this.control = c;
        }
        return new Promise(function (resolve) {
            // the control should have incorrect status first
            resolve({ incorrectCaptcha: true });
            // we only validate the captcha on blur
            if (onBlur) {
                var captchaCode = _this.captchaService.botdetectInstance.userInput.value;
                if (captchaCode) {
                    _this.captchaService.validateUnsafe(captchaCode)
                        .subscribe(function (isHuman) {
                        if (!isHuman) {
                            // ui captcha validation failed
                            _this.captchaService.botdetectInstance.reloadImage();
                            _this.control = null;
                        }
                        else {
                            // ui captcha validation passed
                            _this.control.setErrors(null);
                        }
                    }, function (error) {
                        throw new Error(error);
                    });
                }
            }
        });
    };
    CorrectCaptchaDirective.prototype.onBlur = function () {
        this.validate(undefined, true);
    };
    CorrectCaptchaDirective.decorators = [
        { type: core_1.Directive, args: [{
                    selector: '[correctCaptcha][formControlName],[correctCaptcha][formControl],[correctCaptcha][ngModel]',
                    providers: [
                        {
                            provide: forms_1.NG_ASYNC_VALIDATORS,
                            useExisting: core_1.forwardRef(function () { return CorrectCaptchaDirective; }),
                            multi: true
                        }
                    ]
                },] },
    ];
    /** @nocollapse */
    CorrectCaptchaDirective.ctorParameters = function () { return [
        { type: captcha_service_1.CaptchaService, },
    ]; };
    CorrectCaptchaDirective.propDecorators = {
        'onBlur': [{ type: core_1.HostListener, args: ['blur',] },],
    };
    return CorrectCaptchaDirective;
}());
exports.CorrectCaptchaDirective = CorrectCaptchaDirective;
//# sourceMappingURL=correct-captcha.directive.js.map