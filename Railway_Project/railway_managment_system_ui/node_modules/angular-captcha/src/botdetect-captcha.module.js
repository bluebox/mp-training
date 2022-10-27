"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var http_1 = require("@angular/common/http");
var captcha_component_1 = require("./captcha.component");
var captcha_service_1 = require("./captcha.service");
var captcha_helper_service_1 = require("./captcha-helper.service");
var correct_captcha_directive_1 = require("./correct-captcha.directive");
var captcha_endpoint_pipe_1 = require("./captcha-endpoint.pipe");
var config_1 = require("./config");
var BotDetectCaptchaModule = /** @class */ (function () {
    function BotDetectCaptchaModule() {
    }
    BotDetectCaptchaModule.forRoot = function (config) {
        return {
            ngModule: BotDetectCaptchaModule,
            providers: [provideBotDetectCaptcha(config)]
        };
    };
    BotDetectCaptchaModule.forChild = function (config) {
        return {
            ngModule: BotDetectCaptchaModule,
            providers: [provideBotDetectCaptcha(config)]
        };
    };
    BotDetectCaptchaModule.decorators = [
        { type: core_1.NgModule, args: [{
                    imports: [
                        http_1.HttpClientModule
                    ],
                    declarations: [
                        captcha_endpoint_pipe_1.CaptchaEndpointPipe,
                        captcha_component_1.CaptchaComponent,
                        correct_captcha_directive_1.CorrectCaptchaDirective
                    ],
                    providers: [
                        captcha_service_1.CaptchaService,
                        captcha_helper_service_1.CaptchaHelperService,
                        captcha_endpoint_pipe_1.CaptchaEndpointPipe,
                        {
                            // we need this provide CAPTCHA_SETTINGS declaration
                            // since we have added support for the captchaEndpoint 
                            // setting in component
                            provide: config_1.CAPTCHA_SETTINGS,
                            useValue: null
                        }
                    ],
                    exports: [
                        captcha_component_1.CaptchaComponent,
                        correct_captcha_directive_1.CorrectCaptchaDirective
                    ]
                },] },
    ];
    /** @nocollapse */
    BotDetectCaptchaModule.ctorParameters = function () { return []; };
    return BotDetectCaptchaModule;
}());
exports.BotDetectCaptchaModule = BotDetectCaptchaModule;
function provideBotDetectCaptcha(config) {
    return [
        {
            provide: config_1.CAPTCHA_SETTINGS,
            useValue: config
        }
    ];
}
exports.provideBotDetectCaptcha = provideBotDetectCaptcha;
//# sourceMappingURL=botdetect-captcha.module.js.map