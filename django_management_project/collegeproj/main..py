base_url="https://www.youtube.com/watch?v=dx4Teh-nv3A&list=RDM_Vl5pvLauE&index="
import pyautogui
import time
counter=8

while counter<20:
    time.sleep(5)
    pyautogui.moveTo(864,350)
    pyautogui.click()
    time.sleep(5)
    pyautogui.moveTo(647,350)
    pyautogui.click()
    abc=base_url+str(counter)
    counter=counter+1
    pyautogui.typewrite(abc)
    pyautogui.press("enter")
    time.sleep(5)
    pyautogui.moveTo(601,343)
    pyautogui.click()



