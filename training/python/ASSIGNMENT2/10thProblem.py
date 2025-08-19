


class SmartDevice:
    def __init__(self,is_on=False):
        self._is_on=is_on

    def turn_on(self):
        self.__is_on=True
        print("changed to ON mode")
    def turn_off(self):
        self.__is_on=False
        print("changed to OFF mode")


class SmartLight(SmartDevice):
    def __init__(self,brightness,is_on=True):
        self.__brightness=brightness
        super().__init__(is_on)
    @property
    def brightness(self):
        return self.__brightness

    @brightness.setter
    def brightness(self,level):
        if  self._is_on and  level>0 and level<100 :
            self.__brightness=level
        elif not (level>0 and level<100):
            print("brightness level is out of range")

        else:
            print("Device is in off mode")




class SmartThermostat(SmartDevice):
    def __init__(self,tempreature,is_on=True):
        self.__tempreature=tempreature
        super().__init__(is_on)
    @property
    def tempreature(self):
        return  self.__tempreature
    @tempreature.setter
    def temperature(self,temp):
        if self._is_on and temp > 18 and temp < 30:
            self.__tempreature = temp
        elif not (temp > 18 and temp < 30):
            print("tempreature level is out of range")

        else:
            print("Device is in off mode")



light=SmartLight(52)
print(light.brightness)
light.brightness=35
print(light.brightness)
light.brightness=-56
print(light.brightness)
light.turn_off()
light.brightness=56
print(light.brightness)
print("-------------------------------------")
t=SmartThermostat(20)
t.temperature=25
print(t.temperature)
t.temperature=17
print(t.temperature)
