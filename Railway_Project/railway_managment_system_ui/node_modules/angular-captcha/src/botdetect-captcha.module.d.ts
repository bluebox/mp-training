import { ModuleWithProviders } from '@angular/core';
import { CaptchaSettings } from './captcha-settings.interface';
export declare class BotDetectCaptchaModule {
    static forRoot(config: CaptchaSettings): ModuleWithProviders;
    static forChild(config: CaptchaSettings): ModuleWithProviders;
}
export declare function provideBotDetectCaptcha(config: CaptchaSettings): any;
