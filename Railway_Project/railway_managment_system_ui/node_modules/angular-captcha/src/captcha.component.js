"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var captcha_service_1 = require("./captcha.service");
var captcha_helper_service_1 = require("./captcha-helper.service");
var CaptchaComponent = /** @class */ (function () {
    function CaptchaComponent(elementRef, captchaService, captchaHelper) {
        this.elementRef = elementRef;
        this.captchaService = captchaService;
        this.captchaHelper = captchaHelper;
    }
    Object.defineProperty(CaptchaComponent.prototype, "captchaEndpoint", {
        // provide captchaEndpoint for getting captcha challenge.
        set: function (captchaEndpoint) {
            this.captchaService.captchaEndpoint = captchaEndpoint;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(CaptchaComponent.prototype, "captchaId", {
        // the current captcha id, which will be used for validation purpose.
        get: function () {
            return this.captchaService.botdetectInstance.captchaId;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(CaptchaComponent.prototype, "captchaCode", {
        // the user entered captcha code value.
        // keep this method for backward compatibility
        get: function () {
            return this.captchaService.botdetectInstance.userInput.value;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(CaptchaComponent.prototype, "userEnteredCaptchaCode", {
        get: function () {
            return this.captchaCode;
        },
        enumerable: true,
        configurable: true
    });
    // display captcha html markup on component initialization.
    CaptchaComponent.prototype.ngOnInit = function () {
        this.captchaStyleName = this.getCaptchaStyleName();
        // set captcha style name to CaptchaService for creating BotDetect object
        this.captchaService.captchaStyleName = this.captchaStyleName;
        // display captcha html markup on view
        this.displayHtml();
    };
    // get captcha style name.
    CaptchaComponent.prototype.getCaptchaStyleName = function () {
        var styleName;
        styleName = this.captchaStyleName;
        if (styleName) {
            return styleName;
        }
        // backward compatible
        styleName = this.styleName;
        if (styleName) {
            return styleName;
        }
        throw new Error('The captchaStyleName attribute is not found or its value is not set.');
    };
    // display captcha html markup in the <botdetect-captcha> tag.
    CaptchaComponent.prototype.displayHtml = function () {
        var _this = this;
        this.captchaService.getHtml()
            .subscribe(function (captchaHtml) {
            // display captcha html markup
            captchaHtml = _this.captchaHelper.changeRelativeToAbsoluteUrls(captchaHtml, _this.captchaService.captchaEndpoint);
            _this.elementRef.nativeElement.innerHTML = captchaHtml;
            // load botdetect scripts
            _this.loadScriptIncludes();
        }, function (error) {
            throw new Error(error);
        });
    };
    // reload a new captcha image.
    CaptchaComponent.prototype.reloadImage = function () {
        this.captchaService.botdetectInstance.reloadImage();
    };
    // validate captcha on client-side and execute user callback function on ajax success
    CaptchaComponent.prototype.validateUnsafe = function (callback) {
        var _this = this;
        var userInput = this.captchaService.botdetectInstance.userInput;
        var captchaCode = userInput.value;
        if (captchaCode.length !== 0) {
            this.captchaService.validateUnsafe(captchaCode)
                .subscribe(function (isHuman) {
                callback(isHuman);
                if (!_this.captchaHelper.useUserInputBlurValidation(userInput) && !isHuman) {
                    _this.reloadImage();
                }
            }, function (error) {
                throw new Error(error);
            });
        }
        else {
            var isHuman = false;
            callback(isHuman);
        }
    };
    // load botdetect scripts.
    CaptchaComponent.prototype.loadScriptIncludes = function () {
        var captchaId = this.elementRef.nativeElement.querySelector('#BDC_VCID_' + this.captchaStyleName).value;
        var scriptIncludeUrl = this.captchaService.captchaEndpoint + '?get=script-include&c=' + this.captchaStyleName + '&t=' + captchaId + '&cs=201';
        this.captchaHelper.getScript(scriptIncludeUrl);
    };
    CaptchaComponent.decorators = [
        { type: core_1.Component, args: [{
                    selector: 'botdetect-captcha',
                    template: ''
                },] },
    ];
    /** @nocollapse */
    CaptchaComponent.ctorParameters = function () { return [
        { type: core_1.ElementRef, },
        { type: captcha_service_1.CaptchaService, },
        { type: captcha_helper_service_1.CaptchaHelperService, },
    ]; };
    CaptchaComponent.propDecorators = {
        'styleName': [{ type: core_1.Input },],
        'captchaStyleName': [{ type: core_1.Input },],
    };
    return CaptchaComponent;
}());
exports.CaptchaComponent = CaptchaComponent;
//# sourceMappingURL=captcha.component.js.map