import { OnInit, ElementRef } from '@angular/core';
import { CaptchaService } from './captcha.service';
import { CaptchaHelperService } from './captcha-helper.service';
export declare class CaptchaComponent implements OnInit {
    private elementRef;
    private captchaService;
    private captchaHelper;
    styleName: string;
    captchaStyleName: string;
    constructor(elementRef: ElementRef, captchaService: CaptchaService, captchaHelper: CaptchaHelperService);
    captchaEndpoint: string;
    readonly captchaId: string;
    readonly captchaCode: string;
    readonly userEnteredCaptchaCode: string;
    ngOnInit(): void;
    getCaptchaStyleName(): string;
    displayHtml(): void;
    reloadImage(): void;
    validateUnsafe(callback: (isHuman: boolean) => void): void;
    loadScriptIncludes(): void;
}
