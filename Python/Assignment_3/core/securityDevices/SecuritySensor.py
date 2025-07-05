from core.devices.SmartDevice import SmartDevice

class SecuritySensor(SmartDevice):
    def __init__(self, device_id):
        super().__init__(device_id)
        self._armed = False

    async def perform_action(self, action, value=None):
        if action == "arm":
            self._armed = True
            print(f"{self._device_id} armed.")
        elif action == "disarm":
            self._armed = False
            print(f"{self._device_id} disarmed.")
        else:
            print(f"Invalid action '{action}' for SecuritySensor")

    def is_armed(self):
        return self._armed

    def get_supported_actions(self):
        return ["arm", "disarm"]
