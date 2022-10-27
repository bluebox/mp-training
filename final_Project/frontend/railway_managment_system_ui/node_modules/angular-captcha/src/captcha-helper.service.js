"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var http_1 = require("@angular/common/http");
var CaptchaHelperService = /** @class */ (function () {
    function CaptchaHelperService(http, ngZone) {
        this.http = http;
        this.ngZone = ngZone;
    }
    // get script and execute it immediately
    CaptchaHelperService.prototype.getScript = function (url) {
        var _this = this;
        this.http.get(url, { responseType: 'text' })
            .subscribe(function (scriptString) {
            var f = new Function(scriptString);
            _this.ngZone.runOutsideAngular(function () {
                f();
            });
        });
    };
    CaptchaHelperService.prototype.useUserInputBlurValidation = function (userInput) {
        return (userInput.getAttribute('correctCaptcha') !== null);
    };
    // get captcha endpoint handler from configued captchaEndpoint value,
    // the result can be "simple-captcha-endpoint.ashx", "simple-captcha-endpoint",
    // or "simple-botdetect.php"
    CaptchaHelperService.prototype.getCaptchaEndpointHandler = function (captchaEndpoint) {
        var splited = captchaEndpoint.split('/');
        return splited[splited.length - 1];
    };
    // get backend base url from configued captchaEndpoint value
    CaptchaHelperService.prototype.getBackendBaseUrl = function (captchaEndpoint, captchaEndpointHandler) {
        var lastIndex = captchaEndpoint.lastIndexOf(captchaEndpointHandler);
        return captchaEndpoint.substring(0, lastIndex);
    };
    // change relative to absolute urls in captcha html markup
    CaptchaHelperService.prototype.changeRelativeToAbsoluteUrls = function (originCaptchaHtml, captchaEndpoint) {
        var captchaEndpointHandler = this.getCaptchaEndpointHandler(captchaEndpoint);
        var backendUrl = this.getBackendBaseUrl(captchaEndpoint, captchaEndpointHandler);
        originCaptchaHtml = originCaptchaHtml.replace(/<script.*<\/script>/g, '');
        var relativeUrls = originCaptchaHtml.match(/(src|href)=\"([^"]+)\"/g);
        var relativeUrl, relativeUrlPrefixPattern, absoluteUrl, changedCaptchaHtml = originCaptchaHtml;
        for (var i = 0; i < relativeUrls.length; i++) {
            relativeUrl = relativeUrls[i].slice(0, -1).replace(/src=\"|href=\"/, '');
            relativeUrlPrefixPattern = new RegExp(".*" + captchaEndpointHandler);
            absoluteUrl = relativeUrl.replace(relativeUrlPrefixPattern, backendUrl + captchaEndpointHandler);
            changedCaptchaHtml = changedCaptchaHtml.replace(relativeUrl, absoluteUrl);
        }
        return changedCaptchaHtml;
    };
    CaptchaHelperService.decorators = [
        { type: core_1.Injectable },
    ];
    /** @nocollapse */
    CaptchaHelperService.ctorParameters = function () { return [
        { type: http_1.HttpClient, },
        { type: core_1.NgZone, },
    ]; };
    return CaptchaHelperService;
}());
exports.CaptchaHelperService = CaptchaHelperService;
//# sourceMappingURL=captcha-helper.service.js.map