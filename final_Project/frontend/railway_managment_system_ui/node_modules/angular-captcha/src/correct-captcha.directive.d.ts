import { AbstractControl, Validator } from '@angular/forms';
import { CaptchaService } from './captcha.service';
export declare class CorrectCaptchaDirective implements Validator {
    private captchaService;
    control: AbstractControl;
    constructor(captchaService: CaptchaService);
    validate(c: AbstractControl, onBlur?: boolean): Promise<{}>;
    onBlur(): void;
}
