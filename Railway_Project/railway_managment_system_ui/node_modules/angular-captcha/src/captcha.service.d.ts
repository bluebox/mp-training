import { HttpClient } from '@angular/common/http';
import { CaptchaEndpointPipe } from './captcha-endpoint.pipe';
import { CaptchaSettings } from './captcha-settings.interface';
export declare class CaptchaService {
    private http;
    private captchaEndpointPipe;
    private config;
    private _captchaStyleName;
    private _captchaEndpoint;
    constructor(http: HttpClient, captchaEndpointPipe: CaptchaEndpointPipe, config: CaptchaSettings);
    captchaStyleName: string;
    captchaEndpoint: string;
    readonly botdetectInstance: any;
    getHtml(): any;
    validateUnsafe(captchaCode: string): any;
}
