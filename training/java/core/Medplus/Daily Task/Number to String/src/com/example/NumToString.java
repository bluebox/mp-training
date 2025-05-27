package com.example;
import java.util.*;
public class NumToString {
	public static void main(String arg[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = "";
        int x = 0;

        while (n != 0) {
            if (x == 0 || x == 3 || x == 5) {
                if ((n%100) != 0) {
                    if (x == 3) {
                        s = " thousand" + s;
                    } else if (x == 5) {
                        s = " lakh" + s;
                    }

                    if ((n%100) < 20) {
                        switch (n%100) {
                            case 1: {
                                s = " one" + s; 
                                break;
                            }
                            case 2: {
                                s = " two" + s; 
                                break;
                            }
                            case 3: {
                                s = " three" + s;
                                break;
                            }
                            case 4: {
                                s = " four" + s;
                                break;
                            }
                            case 5: {
                                s = " five" + s; 
                                break;
                            }
                            case 6: {
                                s = " six" + s; 
                                break;
                            }
                            case 7: {
                                s = " seven" + s; 
                                break;
                            }                            
                            case 8: {
                                s = " eight" + s; 
                                break;
                            }
                            case 9: {
                                s = " nine" + s; 
                                break;
                            }
                            case 10: {
                                s = " ten" + s; 
                                break;
                            }
                            case 11: {
                                s = " eleven" + s; 
                                break;
                            }
                            case 12: {
                                s = " twelve" + s; 
                                break;
                            }
                            case 13: {
                                s = " thirteen" + s; 
                                break;
                            }
                            case 14: {
                                s = " fourteen" + s; 
                                break;
                            }
                            case 15: {
                                s = " fifteen" + s; 
                                break;
                            }
                            case 16: {
                                s = " sixteen" + s; 
                                break;
                            }
                            case 17: {
                                s = " seventeen" + s; 
                                break;
                            }
                            case 18: {
                                s = " eighteen" + s; 
                                break;
                            }
                            case 19: {
                                s = " nineteen" + s; 
                                break;
                            }
                        }
                        n /= 100;
                        x += 2;
                    } else {
                        switch (n%10) {
                            case 1: {
                                s = " one" + s; 
                                break;
                            }
                            case 2: {
                                s = " two" + s; 
                                break;
                            }
                            case 3: {
                                s = " three" + s; 
                                break;
                            }
                            case 4: {
                                s = " four" + s; 
                                break;
                            }
                            case 5: {
                                s = " five" + s; 
                                break;
                            }
                            case 6: {
                                s = " six" + s; 
                                break;
                            }
                            case 7: {
                                s = " seven" + s; 
                                break;
                            }
                            case 8: {
                                s = " eight" + s; 
                                break;
                            }
                            case 9: {
                                s = " nine" + s; 
                                break;
                            }
                        }
                        n /= 10;
                        x += 1;
                        switch (n%10) {
                            case 2: {
                                s = " twenty" + s; 
                                break;
                            }
                            case 3: {
                                s = " thirty" + s; 
                                break;
                            }
                            case 4: {
                                s = " forty" + s; 
                                break;
                            }
                            case 5: {
                                s = " fifty" + s; 
                                break;
                            }
                            case 6: {
                                s = " sixty" + s; 
                                break;
                            }
                            case 7: {
                                s = " seventy" + s; 
                                break;
                            }
                            case 8: {
                                s = " eighty" + s; 
                                break;
                            }
                            case 9: {
                                s = " ninety" + s; 
                                break;
                            }
                        }
                        n /= 10;
                        x += 1;
                    }
                } else {
                    n /= 100;
                    x += 2;
                }
            } else if (x == 2) {
                int digit = n % 10;
                if (digit != 0) {
                    s = " hundred" + s;
                    switch (digit) {
                        case 1: {
                            s = " one" + s; 
                            break;
                        }
                        case 2: {
                            s = " two" + s; 
                            break;
                        }
                        case 3: {
                            s = " three" + s; 
                            break;
                        }
                        case 4: {
                            s = " four" + s; 
                            break;
                        }
                        case 5: {
                            s = " five" + s; 
                            break;
                        }
                        case 6: {
                            s = " six" + s; 
                            break;
                        }
                        case 7: {
                            s = " seven" + s; 
                            break;
                        }
                        case 8: {
                            s = " eight" + s; 
                            break;
                        }
                        case 9: {
                            s = " nine" + s; 
                            break;
                        }
                    }
                }
                n /= 10;
                x += 1;
            }
        }
        System.out.println(s);
        sc.close();
    }
}
