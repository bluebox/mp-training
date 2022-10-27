"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var http_1 = require("@angular/common/http");
var captcha_endpoint_pipe_1 = require("./captcha-endpoint.pipe");
var config_1 = require("./config");
var CaptchaService = /** @class */ (function () {
    function CaptchaService(http, captchaEndpointPipe, config) {
        this.http = http;
        this.captchaEndpointPipe = captchaEndpointPipe;
        this.config = config;
    }
    Object.defineProperty(CaptchaService.prototype, "captchaStyleName", {
        get: function () {
            return this._captchaStyleName;
        },
        set: function (captchaStyleName) {
            this._captchaStyleName = captchaStyleName;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(CaptchaService.prototype, "captchaEndpoint", {
        // the captcha endpoint for botdetect requests.
        get: function () {
            var captchaEndpoint = this._captchaEndpoint;
            if (this.config && this.config.captchaEndpoint) {
                captchaEndpoint = this.config.captchaEndpoint;
            }
            return this.captchaEndpointPipe.transform(captchaEndpoint);
        },
        set: function (captchaEndpoint) {
            this._captchaEndpoint = captchaEndpoint;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(CaptchaService.prototype, "botdetectInstance", {
        // get botdetect instance, which is provided by botdetect script.
        get: function () {
            return BotDetect.getInstanceByStyleName(this.captchaStyleName);
        },
        enumerable: true,
        configurable: true
    });
    // get captcha html markup from botdetect api.
    CaptchaService.prototype.getHtml = function () {
        if (!this.captchaEndpoint) {
            var errorMessage = "captchaEndpoint property is not set!\n    The Angular Captcha Module requires the \"this.captchaComponent.captchaEndpoint\" property to be set.\n    For example: \n    ngOnInit(): void {\n      this.captchaComponent.captchaEndpoint = 'https://your-app-backend-hostname.your-domain.com/simple-captcha-endpoint-path';\n    }\n      ";
            throw new Error(errorMessage);
        }
        var url = this.captchaEndpoint + '?get=html&c=' + this.captchaStyleName;
        return this.http.get(url, { responseType: 'text' });
    };
    // ui validate captcha.
    CaptchaService.prototype.validateUnsafe = function (captchaCode) {
        if (!this.botdetectInstance) {
            throw new Error('BotDetect instance does not exist.');
        }
        var url = this.botdetectInstance.validationUrl + '&i=' + captchaCode;
        return this.http.get(url);
    };
    CaptchaService.decorators = [
        { type: core_1.Injectable },
    ];
    /** @nocollapse */
    CaptchaService.ctorParameters = function () { return [
        { type: http_1.HttpClient, },
        { type: captcha_endpoint_pipe_1.CaptchaEndpointPipe, },
        { type: undefined, decorators: [{ type: core_1.Inject, args: [config_1.CAPTCHA_SETTINGS,] },] },
    ]; };
    return CaptchaService;
}());
exports.CaptchaService = CaptchaService;
//# sourceMappingURL=captcha.service.js.map