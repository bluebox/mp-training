import { NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
export declare class CaptchaHelperService {
    private http;
    private ngZone;
    constructor(http: HttpClient, ngZone: NgZone);
    getScript(url: string): void;
    useUserInputBlurValidation(userInput: any): boolean;
    getCaptchaEndpointHandler(captchaEndpoint: string): string;
    getBackendBaseUrl(captchaEndpoint: string, captchaEndpointHandler: string): string;
    changeRelativeToAbsoluteUrls(originCaptchaHtml: string, captchaEndpoint: string): string;
}
