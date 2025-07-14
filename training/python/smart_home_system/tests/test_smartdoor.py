import unittest
from unittest.mock import patch
from core.devicess import SmartDoorLock
from core.exceptions import (
    DeviceOfflineError,
    InvalidParameterError,
    AuthenticationError
)

class TestSmartDoorLock(unittest.IsolatedAsyncioTestCase):

    def setUp(self):
        self.valid_code = "Secure123"
        self.invalid_code = "!!!"
        self.device_id = "SD22"

    async def asyncSetUp(self):
        self.door = SmartDoorLock(self.device_id, code=self.valid_code)
        await self.door.turn_on()

    async def test_lock_unlock_sequence(self):
        await self.door.unlock()
        self.assertFalse(self.door.get_status_report().endswith("Locked: True"))

        await self.door.lock()
        self.assertTrue(self.door.get_status_report().endswith("Locked: True"))


    async def test_unlock_when_offline_raises(self):
        door = SmartDoorLock("SD3", code="Test123")
        with self.assertRaises(DeviceOfflineError):
            await door.unlock()

    async def test_perform_action_lock(self):
        await self.door.unlock()
        await self.door.perform_action("lock")
        self.assertTrue(self.door.get_status_report().endswith("Locked: True"))

    async def test_perform_action_unlock_success(self):
        await self.door.perform_action("unlock")
        self.assertFalse(self.door.get_status_report().endswith("Locked: True"))

    async def test_perform_action_invalid_action(self):
        with self.assertRaises(InvalidParameterError):
            await self.door.perform_action("destroy")

    def test_password_validation_on_init_success(self):
        door = SmartDoorLock("SD4", code="Good123")
        self.assertIsInstance(door, SmartDoorLock)

    # def test_password_attempt_limit_exceeded(self, mock_input):
    #     with self.assertRaises(InvalidParameterError):
    #         SmartDoorLock("SD5", code="@@@")

    async def test_unlock_invalid_password(self):
        # Try unlocking with invalid password (using decorator)
        # Set valid password first, then test with wrong one
        door = SmartDoorLock("SD6", code="Pass123")
        await door.turn_on()
        with patch("builtins.input", return_value="wrongpass"):
            with self.assertRaises(AuthenticationError):
                await door.unlock()  # Triggers password check

if __name__ == "__main__":
    unittest.main()