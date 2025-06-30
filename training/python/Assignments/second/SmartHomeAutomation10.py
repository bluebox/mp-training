class SmartDevice:
    def __init__(self,is_on):
        self.__is_on=is_on
    def turn_on(self):
        print("ON")
        # return True
    def turn_off(self):
        print("OFF")
        # return False
    @property
    def is_on(self):
        return self.__is_on
class SmartLight(SmartDevice):
    def __init__(self, is_on,brightness):
        super().__init__(is_on)
        self.__brightness=brightness
    def set_brightness(self,level):
        if SmartDevice.is_on and 0<level<100:
            self.__brightness=level
    @property
    def brightness(self):
        return self.__brightness
    @brightness.setter
    def brightness(self,level):
        self.__brightness=level
class SmartThermostat(SmartDevice):
    def __init__(self, is_on,temperature):
        super().__init__(is_on)
        self.__temperature=temperature
    def set_temperature(self,temp):
        if SmartDevice.is_on and 18<=temp<=30:
            self.__temperature=temp
    @property
    def temperature(self):
        return self.__temperature
    @temperature.setter
    def temperature(self,temp):
        self.__temperature=temp


    
