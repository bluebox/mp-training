from core.devices.SmartDevice import SmartDevice


class SmartSpeaker(SmartDevice):
    def __init__(self,device_id):
        super().__init__(device_id)
        self.volume=0
